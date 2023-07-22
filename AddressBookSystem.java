// Name : Kiran Saklani
// Project name : Address book system

import java.io.*;
import java.util.*;


class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null; // Contact not found
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println("Name: " + contact.getName());
            System.out.println("Phone Number: " + contact.getPhoneNumber());
            System.out.println("Email Address: " + contact.getEmailAddress());
            System.out.println("-----------------------");
        }
    }

    // Implement methods to read and write contact data to a storage medium as a file
    public void saveContactsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
            }
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving contacts: " + e.getMessage());
        }
    }

    public void loadContactsFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0].trim();
                    String phoneNumber = data[1].trim();
                    String emailAddress = data[2].trim();
                    Contact contact = new Contact(name, phoneNumber, emailAddress);
                    contacts.add(contact);
                }
            }
            System.out.println("Contacts loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Contacts file not found: " + e.getMessage());
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book System");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Load contacts from file");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    break;
                case 2:
                    System.out.print("Enter name to remove: ");
                    String nameToRemove = scanner.nextLine();
                    Contact contactToRemove = addressBook.searchContact(nameToRemove);
                    if (contactToRemove != null) {
                        addressBook.removeContact(contactToRemove);
                        System.out.println("Contact removed successfully!");
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String nameToSearch = scanner.nextLine();
                    Contact searchedContact = addressBook.searchContact(nameToSearch);
                    if (searchedContact != null) {
                        System.out.println("Contact found:");
                        System.out.println("Name: " + searchedContact.getName());
                        System.out.println("Phone Number: " + searchedContact.getPhoneNumber());
                        System.out.println("Email Address: " + searchedContact.getEmailAddress());
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;
                case 4:
                    System.out.println("All contacts:");
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    System.out.print("Enter file name to save contacts: ");
                    String saveFileName = scanner.nextLine();
                    addressBook.saveContactsToFile(saveFileName);
                    break;
                case 6:
                    System.out.print("Enter file name to load contacts: ");
                    String loadFileName = scanner.nextLine();
                    addressBook.loadContactsFromFile(loadFileName);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println();
        }
    }
}
