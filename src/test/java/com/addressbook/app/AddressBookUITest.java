package com.addressbook.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressBookUITest {

    private AddressBook addressBook;
    private ContactValidator contactValidator;
    private Scanner scan;
    private AddressBookUI addressBookUI;

    @BeforeEach
    void setUp() {
        addressBook = spy(new AddressBook());
        contactValidator = mock(ContactValidator.class);
        scan = mock(Scanner.class);
        addressBookUI = new AddressBookUI(addressBook, contactValidator, scan);
    }

    @Nested
    @DisplayName("Add Contact Tests")
    class AddContactTests {
        @Test
        @DisplayName("Add valid contact")
        void testAddValidContact() {
            // Arrange
            when(scan.nextLine()).thenReturn("Nico Robin", "1234567890", "nico.robin@example.com");
            when(contactValidator.isValidName("Nico Robin")).thenReturn(true);
            when(contactValidator.isValidNumber("1234567890", addressBook.getContacts())).thenReturn(true);
            when(contactValidator.isValidEmail("nico.robin@example.com", addressBook.getContacts())).thenReturn(true);

            // Act
            addressBookUI.addContact();

            // Assert
            assertEquals(1, addressBook.getContacts().size());
            assertTrue(addressBook.getContacts().stream().anyMatch(contact ->
                    "Nico Robin".equals(contact.getName()) &&
                            "1234567890".equals(contact.getNumber()) &&
                            "nico.robin@example.com".equals(contact.getEmail())
            ));
        }

        @Test
        @DisplayName("Add invalid Contact - Number")
        void testAddInvalidContactNumber() {
            // Arrange
            when(scan.nextLine()).thenReturn("Nico Robin", "invalidStr", "nico.robin@example.com");
            when(contactValidator.isValidName("Nico Robin")).thenReturn(true);
            when(contactValidator.isValidNumber("invalidStr", addressBook.getContacts())).thenReturn(false);
            when(contactValidator.isValidEmail("nico.robin@example.com", addressBook.getContacts())).thenReturn(true);

            // Act
            addressBookUI.addContact();

            // Assert
            assertEquals(0, addressBook.getContacts().size());
        }

        @Test
        @DisplayName("Add invalid Contact - Number")
        void testAddInvalidContactEmail() {
            // Arrange
            when(scan.nextLine()).thenReturn("Nico Robin", "1234567890", "nico.robin.com");
            when(contactValidator.isValidName("Nico Robin")).thenReturn(true);
            when(contactValidator.isValidNumber("1234567890", addressBook.getContacts())).thenReturn(true);
            when(contactValidator.isValidEmail("nico.robin.com", addressBook.getContacts())).thenReturn(false);

            // Act
            addressBookUI.addContact();

            // Assert
            assertEquals(0, addressBook.getContacts().size());
        }


    }

    @Nested
    @DisplayName("Search Contact Tests")
    class searchContactTests{
        @Test
        @DisplayName("Contacts Found")
        void testSearchContactFound() {
            // Arrange
            when(scan.nextLine()).thenReturn("Nico Robin");
            Contact contact1 = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            Contact contact2 = new Contact("Nico Robin", "0987654321", "robin.nico@example.com");
            Contact contact3 = new Contact("Donquixote Doflamingo", "1234554321", "doflamingo@gmail.com");

            addressBook.addContact(contact1);
            addressBook.addContact(contact2);
            addressBook.addContact(contact3);

            // Act
            ArrayList<Contact> result = addressBookUI.searchContact();

            // Assert
            assertTrue(result.contains(contact1));
            assertTrue(result.contains(contact2));
            assertFalse(result.contains(contact3));
        }

        @Test
        @DisplayName("Contacts Not Found")
        void testSearchContactNotFound() {
            // Arrange
            when(scan.nextLine()).thenReturn("Unknown");

            // Act
            addressBookUI.searchContact();

            // Assert
            assertTrue(addressBook.getContacts().isEmpty());
        }
    }

    @Nested
    @DisplayName("Remove Contact Tests")
    class removeContactTests{
        @Test
        @DisplayName("Remove existing contact")
        void testRemoveExistingContact() {
            // Arrange
            when(scan.nextLine()).thenReturn("1234567890");
            Contact contact1 = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            addressBook.addContact(contact1);


            // Act
            addressBookUI.removeContact();

            // Assert
            assertFalse(addressBook.getContacts().contains(contact1));
            assertEquals(0, addressBook.getContacts().size());
        }

        @Test
        @DisplayName("Remove non existing contact")
        void testRemoveNonExistingContact() {
            // Arrange
            when(scan.nextLine()).thenReturn("1234567890");
            Contact contact1 = new Contact("Nico Robin", "0987654321", "nico.robin@example.com");
            addressBook.addContact(contact1);

            // Act
            addressBookUI.removeContact();

            // Assert
            assertTrue(addressBook.getContacts().contains(contact1));
            assertEquals(1, addressBook.getContacts().size());
        }
    }

    @Nested
    @DisplayName("Edit Contact Tests")
    class editContactTests{
        @Test
        @DisplayName("Edit Valid Contact")
        void testEditExistingContact() {
            // Arrange
            when(scan.nextLine()).thenReturn("1234567890", "Nico Robin", "0987654321", "nico.robin@yahoo.com");
            Contact contact1 = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");

            addressBook.addContact(contact1);

            when(contactValidator.isValidName("Nico Robin")).thenReturn(true);
            when(contactValidator.isValidNumber("0987654321", addressBook.getContacts())).thenReturn(true);
            when(contactValidator.isValidEmail("nico.robin@yahoo.com", addressBook.getContacts())).thenReturn(true);

            // Act
            addressBookUI.editContact();
            Contact actualContact = addressBook.searchContactByNumber("0987654321");

            // Assert
            assertEquals("Nico Robin", actualContact.getName());
            assertEquals("0987654321", actualContact.getNumber());
            assertEquals("nico.robin@yahoo.com", actualContact.getEmail());
        }

        @Test
        @DisplayName("Edit invalid Contact")
        void testEditNonExistingContact() {
            // Arrange
            when(scan.nextLine()).thenReturn("1234567890");

            // Act
            addressBookUI.editContact();

            // Assert
            assertTrue(addressBook.getContacts().isEmpty());
        }

        @Test
        @DisplayName("Edit contact with invalid number")
        void testEditContactInvalidDetails() {
            // Arrange
            when(scan.nextLine()).thenReturn("1234567890", "Nico Robin", "invalidStr", "nico.robin@hotmail.com");
            Contact contact1 = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            addressBook.addContact(contact1);
            when(contactValidator.isValidName("Nico Robin")).thenReturn(true);
            when(contactValidator.isValidNumber("invalidStr", addressBook.getContacts())).thenReturn(false);
            when(contactValidator.isValidEmail("nico.robin@newexample.com", addressBook.getContacts())).thenReturn(true);

            // Act
            addressBookUI.editContact();
            Contact actualContact = addressBook.searchContactByNumber("1234567890");

            // Assert
            assertEquals("Nico Robin", actualContact.getName());
            assertEquals("1234567890", actualContact.getNumber());
            assertEquals("nico.robin@example.com", actualContact.getEmail());
        }
    }

    @Nested
    @DisplayName("View All Contatcts Tests")
    class ViewContactsTests{
        @Test
        @DisplayName("View all contacts")
        void testViewAllContacts() {
            // Arrange
            Contact contact1 = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            Contact contact2 = new Contact("Roronoa Zoro", "0987654321", "roronoa.zoro@example.com");
            Contact contact3 = new Contact("Donquixote Doflamingo", "1234554321", "doflamingo@gmail.com");

            addressBook.addContact(contact1);
            addressBook.addContact(contact2);
            addressBook.addContact(contact3);

            // Act
            addressBookUI.viewAllContacts();

            // Assert
            assertEquals(3, addressBook.getContacts().size());
        }

        @Test
        @DisplayName("View empty contacts")
        void testViewEmptyContacts() {
            // Arrange

            // Act
            addressBookUI.viewAllContacts();

            // Assert
            assertTrue(addressBook.getContacts().isEmpty());
        }
    }

}
