package com.addressbook.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    @Nested
    @DisplayName("Contact Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Name is set by constructor")
        void testNameSetByConstructor() {
            // Arrange
            String testName = "Nico Robin";
            Contact contact = new Contact(testName, "1234567890", "nico.robin@example.com");

            // Act
            String actualName = contact.getName();

            // Assert
            assertEquals(testName, actualName);
        }

        @Test
        @DisplayName("Number is set by constructor")
        void testNumberSetByConstructor() {
            // Arrange
            String testNumber = "1234567890";
            Contact contact = new Contact("Nico Robin", testNumber, "nico.robin@example.com");

            // Act
            String actualNumber = contact.getNumber();

            // Assert
            assertEquals(testNumber, actualNumber);
        }

        @Test
        @DisplayName("Email is set by constructor")
        void testEmailSetByConstructor() {
            // Arrange
            String testEmail = "nico.robin@example.com";
            Contact contact = new Contact("Nico Robin", "1234567890", testEmail);

            // Act
            String actualEmail = contact.getEmail();

            // Assert
            assertEquals(testEmail, actualEmail);
        }

        @Test
        @DisplayName("Constructor sets all values when valid")
        void testConstructorSetsValuesWhenValid() {
            // Arrange
            String testName = "Nico Robin";
            String testNumber = "1234567890";
            String testEmail = "nico.robin@example.com";

            // Act
            Contact contact = new Contact(testName, testNumber, testEmail);

            // Assert
            assertAll("Constructor sets values when valid",
                    () -> assertEquals(testName, contact.getName()),
                    () -> assertEquals(testNumber, contact.getNumber()),
                    () -> assertEquals(testEmail, contact.getEmail()));
        }
    }

    @Nested
    @DisplayName("Contact Getter and Setter Tests")
    class GetterSetterTests {

        @Test
        @DisplayName("Get and set name")
        void testGetNameSetName() {
            // Arrange
            Contact contact = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            String newName = "Jane Doe";

            // Act
            contact.setName(newName);
            String actualName = contact.getName();

            // Assert
            assertEquals(newName, actualName);
        }

        @Test
        @DisplayName("Get and set number")
        void testGetNumberSetNumber() {
            // Arrange
            Contact contact = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            String newNumber = "0987654321";

            // Act
            contact.setNumber(newNumber);
            String actualNumber = contact.getNumber();

            // Assert
            assertEquals(newNumber, actualNumber);
        }

        @Test
        @DisplayName("Get and set email")
        void testGetEmailSetEmail() {
            // Arrange
            Contact contact = new Contact("Nico Robin", "1234567890", "nico.robin@example.com");
            String newEmail = "jane.doe@example.com";

            // Act
            contact.setEmail(newEmail);
            String actualEmail = contact.getEmail();

            // Assert
            assertEquals(newEmail, actualEmail);
        }
    }
}
