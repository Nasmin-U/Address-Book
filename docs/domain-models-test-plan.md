# Domain Models, Class Diagrams and Test Plan

## User Stories and Test Plan

1. ***Add a contact***
   - As a user,
   - I want to add a contact to the address book,
   - So that I can keep them in one place.
   - TEST: check if you can add a contact to the **contacts** list using the **addContact(Contact)** function.


2. ***Search for a contact***
   - As a user,
   - I want to search for a contact by name,
   - So that I can display the result.
   - TEST: check if you can search for a contact from the **contacts** list using the **searchContact(name)** function.

3. ***Remove a contact***
   - As a user,
   - I want to remove a contact from the address book,
   - So that I can get rid of unwanted contacts.
   - TEST: check if you can remove a contact from the **contacts** list using the **removeContact(name)** function.


4. ***Edit contact's details***
    - As a user,
    - I want to edit a contact's details,
    - So that I can change contact details as needed.
    - TEST: check if you can edit a contact from the **contacts** list using the **editContact(name, newDetails)** function.


5. ***Check for duplicate phone numbers***
    - As an address book developer,
    - I want to check for duplicate phone numbers,
    - So that I don’t allow them to be inputted multiple times.
    - TEST: detect duplicate numbers using the **isDuplicateNumber(number, contacts)** function 
      - if **TRUE** don't allow the user to input it and provide and error message
      - if **FALSE** allow the user to input it.


6. ***Check for duplicate email addresses***
    - As an address book developer,
    - I want to check for duplicate email addresses,
    - So that I don’t allow them to be inputted multiple times.
    - TEST: detect duplicate emails using the **isDuplicateEmail(email, contacts)** function
       - if **TRUE** don't allow the user to input it and provide and error message
       - if **FALSE** allow the user to input it


7. ***View all contacts***
    - As a user,
    - I want to view all contacts in the address book,
    - So that I can view them all in a list.
    - TEST: check if the **viewAllContacts()** function displays the correct list of contacts.


8. ***Interact through console interface***
    - As a user,
    - I want to interact with the application using the console interface,
    - So that I can input which functionality I require.
    - TEST: check if the class AddressBookInterface allows the user to interact with the app.



## Class Diagram

```mermaid
classDiagram
   class Contact {
     -name : String
     -number: String 
     -email : String 
     +getName(): String
     +setName(name: String): void
     +getNumber(): String
     +setNumber(number: String): void
     +getEmail(): String
     +setEmail(email: String): void
   }
   class ContactValidator {
      +isValidName(name: String): boolean
      +isValidNumber(number: String): boolean
      +isValidEmail(email: String): boolean
      +isDuplicateNumber(number: String, contacts: List<Contact>): boolean
      +isDuplicateEmail(email: String, contacts: List<Contact>): boolean
   }

    class AddressBook {
      +contacts : List<Contact>
      +addContact(contact: Contact): void
      +searchContact(name: String): List<Contact>
      +removeContact(name: String): void
      +editContact(name: String, number: String , newDetails: Contact): boolean
      +getAllContacts(): List<Contact>
    }

   class AddressBookUI {
      +showMenu(): void
      +handleSelection(): void
      +addContact(): void
      +searchContact(): void
      +removeContact(): void
      +editContact(): void
      +viewAllContacts(): void
   }

AddressBook "1" *-- "many" Contact
AddressBookUI "1" *-- "1" AddressBook
AddressBookUI "1" *-- "1" ContactValidator
