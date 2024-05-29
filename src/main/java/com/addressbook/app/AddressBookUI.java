package com.addressbook.app;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookUI {
    private final AddressBook addressBook;
    private final ContactValidator validator;
    private final Scanner scan;

    public AddressBookUI(AddressBook addressBook, ContactValidator validator, Scanner scan) {
        this.addressBook = addressBook;
        this.validator = validator;
        this.scan = scan;
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            getMenu();
            int selected = getSelection();
            running = handleSelection(selected);
        }
    }

    private void getMenu() {
        System.out.println("Welcome to your Address Book");
        System.out.println("1. Add Contact");
        System.out.println("2. Search contact by Name");
        System.out.println("3. Remove contact by Number");
        System.out.println("4. Edit contact by Number");
        System.out.println("5. View all Contacts");
        System.out.println("6. Exit");
        System.out.print("Select an option: ");
    }

    private int getSelection() {
        int selected = scan.nextInt();
        scan.nextLine();
        return selected;
    }

    private boolean handleSelection(int choice) {
        switch (choice) {
            case 1:
                addContact();
                break;
            case 2:
                 searchContact();
                break;
            case 3:
                 removeContact();
                break;
            case 4:
                 editContact();
                break;
            case 5:
                 viewAllContacts();
                break;
            case 6:
                System.out.println("Exit");
                return false;
            default:
                System.out.println("The selected option is not listed");
        }
        return true;
    }

    public void addContact() {
        String name = getInput("Input name: ");
        String number = getInput("Input number: ");
        String email = getInput("Input email: ");

        if (isValidDetails(name, number, email, null)) {
            Contact contact = new Contact(name, number, email);
            addressBook.addContact(contact);
            System.out.println(name + " added");
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }

    private boolean isValidDetails(String newName, String newNumber, String newEmail, Contact existingContact) {
        if (!validator.isValidName(newName)) {
            System.out.println("Invalid name.");
            return false;
        }

        if (existingContact == null || !newNumber.equals(existingContact.getNumber())) {
            if (!validator.isValidNumber(newNumber, addressBook.getContacts())) {
                System.out.println("Invalid or duplicate number.");
                return false;
            }
        }

        if (existingContact == null || !newEmail.equals(existingContact.getEmail())) {
            if (!validator.isValidEmail(newEmail, addressBook.getContacts())) {
                System.out.println("Invalid or duplicate email.");
                return false;
            }
        }

        return true;
    }

    public ArrayList<Contact> searchContact() {
        String name = getInput("Input name to search: ");
        ArrayList<Contact> matches = addressBook.searchContactByName(name);
        if (matches.isEmpty()) {
            System.out.println("No contacts found called: " + name);
        }
        else {

            for (Contact contact : matches) {
                System.out.println("Name: " + contact.getName() + ", Number: " + contact.getNumber() + ", Email: " + contact.getEmail());
            }
        }
        return matches;
    }

    public void removeContact() {
        String number = getInput("Input number to remove: ");
        addressBook.removeContact(number);
        System.out.println("Removed " + number );
    }

    public void editContact() {
        String number = getInput("Input the number of the contact you want to edit: ");
        Contact contact = addressBook.searchContactByNumber(number);
        if (contact == null) {
            System.out.println("Contact " + number + " not found.");
            return;
        }

        String newName = getInput("Input new name: ");
        String newNumber = getInput("Input new number: ");
        String newEmail = getInput("Input new email: ");

        if (isValidDetails(newName, newNumber, newEmail, contact)) {
            Contact newInfo = new Contact(newName, newNumber, newEmail);
            addressBook.editContact(number, newInfo);
            System.out.println("Updated");
        }
    }

    public void viewAllContacts() {
        ArrayList<Contact> contacts = addressBook.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts");
        }
        else {
            for (Contact contact : contacts) {
                System.out.println("Name: " + contact.getName() + ", Number: " + contact.getNumber() + ", Email: " + contact.getEmail());
            }
        }
    }


}
