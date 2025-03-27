import java.io.*;
import java.util.*;
import java.util.regex.*;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    void display() {
        System.out.println("Name: " + name + ", Phone: " + phone + ", Email: " + email);
    }
}

public class PhoneBookSystem {
    public static boolean isValidPhone(String phone) {
        String pattern = "^\\+?\\d{10,15}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    
    public static List<Contact> loadContacts(String filename) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            //dont display anything
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public static void saveContacts(String filename, List<Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.name + "," + contact.phone + "," + contact.email);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.display();
        }
    }

    public static List<Contact> searchContact(List<Contact> contacts, String searchTerm) {
        List<Contact> results = new ArrayList<>();
        searchTerm = searchTerm.toLowerCase();
        for (Contact contact : contacts) {
            if (contact.name.toLowerCase().contains(searchTerm) ||
                contact.phone.toLowerCase().contains(searchTerm) ||
                contact.email.toLowerCase().contains(searchTerm)) {
                results.add(contact);
            }
        }
        return results;
    }

    public static boolean editContact(List<Contact> contacts, String name, String newName, String newPhone, String newEmail) {
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(name)) {
                if (!newName.isEmpty()) contact.name = newName;
                if (!newPhone.isEmpty()) contact.phone = newPhone;
                if (!newEmail.isEmpty()) contact.email = newEmail;
                return true;
            }
        }
        return false;
    }

    public static boolean deleteContact(List<Contact> contacts, String name) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.name.equalsIgnoreCase(name)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String filename = "contacts.csv";
        List<Contact> contacts = loadContacts(filename);
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nPhone Book Management System");
            System.out.println("1. Add New Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search for a Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Phone Number: ");
                String phone = scanner.nextLine();
                if (!isValidPhone(phone)) {
                    System.out.println("Invalid phone number format!");
                    continue;
                }
                System.out.print("Enter Email Address: ");
                String email = scanner.nextLine();

                contacts.add(new Contact(name, phone, email));
                saveContacts(filename, contacts);
                System.out.println("Contact added successfully!");

            } else if (choice == 2) {
                System.out.println("\nAll Contacts:");
                displayContacts(contacts);

            } else if (choice == 3) {
                System.out.print("Enter Name, Phone or Email to search: ");
                String searchTerm = scanner.nextLine();

                List<Contact> results = searchContact(contacts, searchTerm);
                if (!results.isEmpty()) {
                    System.out.println("\nSearch Results:");
                    for (Contact contact : results) {
                        contact.display();
                    }
                } else {
                    System.out.println("No matching contacts found.");
                }

            } else if (choice == 4) {
                System.out.print("Enter the Name of the contact you want to edit: ");
                String name = scanner.nextLine();
                System.out.print("Enter new Name (leave empty to keep the current): ");
                String newName = scanner.nextLine();
                System.out.print("Enter new Phone (leave empty to keep the current): ");
                String newPhone = scanner.nextLine();
                System.out.print("Enter new Email (leave empty to keep the current): ");
                String newEmail = scanner.nextLine();

                if (editContact(contacts, name, newName, newPhone, newEmail)) {
                    saveContacts(filename, contacts);
                    System.out.println("Contact updated successfully!");
                } else {
                    System.out.println("Contact not found!");
                }

            } else if (choice == 5) {
                System.out.print("Enter the Name of the contact you want to delete: ");
                String name = scanner.nextLine();

                if (deleteContact(contacts, name)) {
                    saveContacts(filename, contacts);
                    System.out.println("Contact deleted successfully!");
                } else {
                    System.out.println("Contact not found!");
                }

            } else if (choice == 6) {
                System.out.println("Exiting the Phone Book Management System...");
                break;

            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
