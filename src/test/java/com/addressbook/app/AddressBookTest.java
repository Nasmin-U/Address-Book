package com.addressbook.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressBookTest {

    private AddressBook addressBook;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    private Contact contact4;

    @BeforeEach
    void setUp() {
        addressBook = new AddressBook();
        contact1 = Mockito.mock(Contact.class);
        contact2 = Mockito.mock(Contact.class);
        contact3 = Mockito.mock(Contact.class);
        contact4 = Mockito.mock(Contact.class);

        when(contact1.getName()).thenReturn("Roronoa Zoro");
        when(contact2.getName()).thenReturn("Nico Robin");
        when(contact3.getName()).thenReturn("Robin Nico");
        when(contact4.getName()).thenReturn("nico Robin");

        when(contact1.getNumber()).thenReturn("1234567890");
        when(contact2.getNumber()).thenReturn("0987654321");
        when(contact3.getNumber()).thenReturn("1122334455");
        when(contact4.getNumber()).thenReturn("1232343456");

        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        addressBook.addContact(contact3);
        addressBook.addContact(contact4);
    }
    @Nested
    @DisplayName("Add Contact Tests")
    class AddContactTests {

        @Test
        @DisplayName("Add contact to address book")
        void testAddContact() {
            // Arrange
            AddressBook addressBook = new AddressBook();
            Contact contact = Mockito.mock(Contact.class);;

            // Act
            addressBook.addContact(contact);

            // Assert
            assertEquals(1, addressBook.getContacts().size());
            assertTrue(addressBook.getContacts().contains(contact));
        }
    }

    @Nested
    @DisplayName("Search Contact Tests")
    class SearchContactTests {

        @Test
        @DisplayName("Search contact by name")
        void testSearchContactByName() {
            // Arrange

            // Act
            ArrayList<Contact> result = addressBook.searchContactByName("nico robin");

            // Assert
            assertEquals(2, result.size());
        }
    }

    @Nested
    @DisplayName("Remove Contact Tests")
    class RemoveContactTests {

        @Test
        @DisplayName("Remove contact by number")
        void testRemoveContact() {
            // Arrange

            // Act
            addressBook.removeContact("0987654321");

            // Assert
            assertFalse(addressBook.getContacts().contains(contact2));
            assertEquals(3, addressBook.getContacts().size());

        }

        @Test
        @DisplayName("Remove contact that does not exist")
        void testRemoveWrongContact() {
            // Arrange

            // Act
            addressBook.removeContact("1234567891");

            // Assert
            assertEquals(4, addressBook.getContacts().size());
        }
    }

    @Nested
    @DisplayName("Edit Contact Tests")
    class EditContactTests {

        @Test
        @DisplayName("Edit contact details by number")
        void testEditContact() {
            // Arrange
            Contact newDetails = Mockito.mock(Contact.class);
            when(newDetails.getName()).thenReturn("name Updated");
            when(newDetails.getNumber()).thenReturn("1111111111");
            when(newDetails.getEmail()).thenReturn("email.updated@example.com");

            // Act
            boolean actual = addressBook.editContact( "1234567890", newDetails);

            // Assert
            assertTrue(actual);
            verify(contact1).setName("name Updated");
            verify(contact1).setNumber("1111111111");
            verify(contact1).setEmail("email.updated@example.com");
        }

        @Test
        @DisplayName("Edit contact that does not exist")
        void testEditWrongContact() {
            // Arrange
            Contact newDetails = Mockito.mock(Contact.class);
            when(newDetails.getName()).thenReturn("name Updated");
            when(newDetails.getNumber()).thenReturn("1111111111");
            when(newDetails.getEmail()).thenReturn("email.updated@example.com");

            // Act
            boolean actual = addressBook.editContact("1111111111", newDetails);

            // Assert
            assertFalse(actual);
        }
    }

    @Nested
    @DisplayName("View All Contacts Tests")
    class ViewAllContactsTests {

        @Test
        @DisplayName("View contacts")
        void testGetAllContacts() {
            // Arrange

            // Act
            ArrayList<Contact> contacts = addressBook.getAllContacts();

            // Assert
            assertEquals(4, contacts.size());

        }
    }

}
