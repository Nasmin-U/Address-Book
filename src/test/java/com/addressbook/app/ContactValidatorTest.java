package com.addressbook.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactValidatorTest {
    private ContactValidator contactValidator;
    private ArrayList<Contact> contacts;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;
    private Contact contact4;

    @BeforeEach
    void setUp(){
        contactValidator = new ContactValidator();
        contacts = new ArrayList<>();
        contact1 = Mockito.mock(Contact.class);

        when(contact1.getName()).thenReturn("Roronoa Zoro");
        when(contact1.getNumber()).thenReturn("1234567890");
        when(contact1.getEmail()).thenReturn("roronoa_zoro@example.com");

       contacts.add(contact1);

    }

    @Nested
    @DisplayName("Number Validation and Duplicate Tests")
    class NumberValidationTests {

        @Test
        @DisplayName("Valid and unique number")
        void testIsValidAndUniqueNumber() {
            // Arrange

            // Act
            boolean actual = contactValidator.isValidNumber("1112223333", contacts);

            // Assert
            assertTrue(actual);
        }

        @Test
        @DisplayName("Valid and duplicate number")
        void testIsValidAndDuplicateNumber() {
            // Arrange

            // Act
            boolean actual = contactValidator.isValidNumber("1234567890", contacts);

            // Assert
            assertFalse(actual);
        }


        @Test
        @DisplayName("Less than 10 digits - invalid")
        void testShortNumber() {
            // Arrange

            // Act
            boolean actual = contactValidator.isValidNumber("12345", contacts);

            // Assert
            assertFalse(actual);
        }

        @Test
        @DisplayName("More than 10 digits - invalid ")
        void testLongNumber() {
            // Arrange

            // Act
            boolean actual = contactValidator.isValidNumber("0987654321123", contacts);

            // Assert
            assertFalse(actual);
        }

        @Test
        @DisplayName("Invalid non-numeric number")
        void testInvalidNonNumericNumber() {
            // Arrange

            // Act
            boolean actual = contactValidator.isValidNumber("12345abcd1", contacts);

            // Assert
            assertFalse(actual);
        }


    }

    @Nested
    @DisplayName("Email Validation and Duplicate Tests")
    class EmailValidationTests {

        @Test
        @DisplayName("Valid and unique email")
        void testIsValidAndUniqueEmail() {
            //Arrange

            // Act
            boolean actual = contactValidator.isValidEmail("new@email.com", contacts);

            // Assert
            assertTrue(actual);
        }

        @Test
        @DisplayName("Valid and duplicate email")
        void testIsValidAndDuplicateEmail() {
            //Arrange

            // Act
            boolean actual = contactValidator.isValidEmail("roronoa_zoro@example.com", contacts);

            // Assert
            assertFalse(actual);
        }

        @Test
        @DisplayName("Missing username - invalid")
        void testMissingUsernameEmail() {
            //Arrange

            // Act
            boolean actual = contactValidator.isValidEmail("@email.com", contacts);

            // Assert
            assertFalse(actual);
        }

        @Test
        @DisplayName("Missing '@' symbol - invalid")
        void testMissingAtEmail() {
            //Arrange

            // Act
            boolean actual = contactValidator.isValidEmail("newemail.com", contacts);

            // Assert
            assertFalse(actual);
        }

        @Test
        @DisplayName("Missing domain - invalid")
        void testMissingDomainEmail() {
            //Arrange

            // Act
            boolean actual = contactValidator.isValidEmail("oden@", contacts);

            // Assert
            assertFalse(actual);
        }

    }


}
