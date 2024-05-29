package com.addressbook.app;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddressBook {
    private final ArrayList<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    public ArrayList<Contact> searchContactByName(String name) {
        ArrayList<Contact> matches = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                matches.add(contact);
            }
        }
        return matches;
    }

    public Contact searchContactByNumber(String number) {
        for (Contact contact : contacts) {
            if (contact.getNumber().equals(number)) {
                return contact;
            }
        }
        return null;
    }

    public void removeContact(String number) {
        for (Contact contact : contacts) {
            if (contact.getNumber().equalsIgnoreCase(number)) {
                contacts.remove(contact);
                return;
            }
        }
    }

    public boolean editContact(String number, Contact newDetails) {
        for (Contact contact : contacts) {
            if (contact.getNumber().equals(number)) {
                contact.setName(newDetails.getName());
                contact.setNumber(newDetails.getNumber());
                contact.setEmail(newDetails.getEmail());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Contact> getAllContacts(){
        return contacts;
    }
}
