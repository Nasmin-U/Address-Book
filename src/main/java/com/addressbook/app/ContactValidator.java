package com.addressbook.app;

import java.util.ArrayList;

public class ContactValidator {
    public boolean isValidNumber(String number, ArrayList <Contact> contacts) {
        if (number == null || !number.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")) {
            return false;
        }
        for (Contact contact : contacts) {
            if (contact.getNumber().equalsIgnoreCase(number)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidEmail(String email,ArrayList <Contact> contacts){
        if(email==null||!email.matches("[^@\\t\\r\\n]+@[^@\\t\\r\\n]+\\.[^@\\t\\r\\n]+")){
            return false;
        }
        for(Contact contact : contacts){
            if(contact.getEmail().equalsIgnoreCase(email)){
                return false;
            }
        }
        return true;
    }

    public boolean isValidName(String name){
        return name!=null;
    }


}
