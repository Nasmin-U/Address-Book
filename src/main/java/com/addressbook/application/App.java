package com.addressbook.application;

import com.addressbook.app.AddressBook;
import com.addressbook.app.AddressBookUI;
import com.addressbook.app.Contact;
import com.addressbook.app.ContactValidator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        ContactValidator contactValidator = new ContactValidator();
        Scanner scanner = new Scanner(System.in);
        AddressBookUI addressBookUI = new AddressBookUI(addressBook, contactValidator, scanner);

        addressBook.addContact(new Contact("Nico Robin", "1234567890", "nico.robin@example.com"));
        addressBook.addContact(new Contact("Roronoa Zoro", "0987654321", "roronoa.zoro@example.com"));
        addressBook.addContact(new Contact("Monkey D. Luffy", "1122334455", "luffy@onepiece.com"));

        addressBookUI.showMenu();
    }
}
