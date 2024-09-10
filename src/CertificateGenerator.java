import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CertificateGenerator {

    public static void main(String[] args) {
        // Start the application
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.display();
    }

    static class WelcomePage {
        Scanner scanner = new Scanner(System.in);

        public void display() {
            System.out.println("Welcome to the Certificate Generator App!");
            System.out.println("Please choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Forgot Password");
            System.out.println("4. Quit the program");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over

                switch (option) {
                    case 1:
                        Account account = new Account();
                        account.login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        forgotPassword();
                        break;
                    case 4:
                        System.out.println("Thank you for using the Certificate Generator Program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        display();
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please try again.");
                scanner.nextLine();
                display();
            }
        }

        public static class UserSession {
            private static UserSession instance;
            private String currentUser;

            private UserSession() {
            }

            public static UserSession getInstance() {
                if (instance == null) {
                    instance = new UserSession();
                }
                return instance;
            }

            public String getCurrentUser() {
                return currentUser;
            }

            public void setCurrentUser(String currentUser) {
                this.currentUser = currentUser;
            }
        }

        public static class Account {
            Scanner scanner = new Scanner(System.in);

            public void login() {
                System.out.println("Please enter your username:");
                String username = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();

                // Validate the username and password
                try {
                    File file = new File("database.txt");
                    Scanner fileScanner = new Scanner(file);

                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] credentials = line.split(","); // Assumes usernames and passwords are separated by a comma

                        if (credentials.length == 6 && credentials[4].equals(username) && credentials[5].equals(password)) {
                            System.out.println("Login successful!");
                            UserSession.getInstance().setCurrentUser(username); // Remember the user

                            // Redirecting to HomePage
                            HomePage homePage = new HomePage();
                            homePage.display();
                            return;
                        }
                    }

                    System.out.println("Username does not exist. Redirecting to Register Page...");

                    // If the username does not exist, redirect to the register page
                    WelcomePage welcomePage = new WelcomePage();
                    welcomePage.register();

                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }

        public void register() {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            System.out.println("Please enter your surname:");
            String surname = scanner.nextLine();
            System.out.println("Please enter your email:");
            String email = scanner.nextLine();
            System.out.println("Please enter your phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Please enter your desired username:");
            String username = scanner.nextLine();
            System.out.println("Please enter your desired password:");
            String password = scanner.nextLine();

            // Save the new account information in the database.txt file
            try {
                FileWriter writer = new FileWriter("database.txt", true); // Open the file in append mode
                writer.write(name + "," + surname + "," + email + "," + phoneNumber + "," + username + "," + password + "\n"); // Write the username and password, separated by a comma
                writer.close();
                System.out.println("Account created successfully!");
                UserSession.getInstance().setCurrentUser(username); // Remember the user
                // Redirect to the HomePage
                HomePage homePage = new HomePage();
                homePage.display();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        public void forgotPassword() {
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();

            // Check if the username exists in the database.txt file
            try {
                File file = new File("database.txt");
                Scanner fileScanner = new Scanner(file);

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] credentials = line.split(","); // Assumes usernames and passwords are separated by a comma

                    if (credentials.length == 6 && credentials[4].equals(username)) {
                        // If the username exists, send a password reset link to the email associated with the username
                        sendEmail(credentials[2]);
                        return;
                    }
                }

                System.out.println("Username not found. Please try again.");
                forgotPassword();

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        public void sendEmail(String email) {
            // Code to send an email with a password reset link
            // This is just a placeholder and won't actually send an email
            System.out.println("A password reset link has been sent to the email " + email + ".");
        }
    }

    public static class HomePage {
        Scanner scanner = new Scanner(System.in);

        public void display() {
            System.out.println("Home Page");
            System.out.println("Please choose an option:");
            System.out.println("1. Settings");
            System.out.println("2. Recent works");
            System.out.println("3. Make Design");
            System.out.println("4. Quit the program");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over

                switch (option) {
                    case 1:
                        // Redirecting to Settings Page
                        SettingsPage settingsPage = new SettingsPage();
                        settingsPage.display();
                        break;
                    case 2:
                        // Redirecting to Recent Certificates Page
                        SettingsPage.RecentsPage recentsPage = new SettingsPage.RecentsPage();
                        recentsPage.display();
                        break;
                    case 3:
                        // Redirecting to Make Design Page
                        SettingsPage.RecentsPage.MakeDesignPage makeDesignPage = new SettingsPage.RecentsPage.MakeDesignPage();
                        makeDesignPage.display();
                        break;
                    case 4:
                        System.out.println("Thank you for using the Certificate Generator Program. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        display();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please try again.");
                scanner.nextLine();
                display();
            }
        }
    }

    public static class SettingsPage {
        Scanner scanner = new Scanner(System.in);

        public void display() {
            System.out.println("Settings Page");
            System.out.println("Please choose an option:");
            System.out.println("1. My Account");
            System.out.println("2. Additional Services");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over

                switch (option) {
                    case 1:
                        myAccount();
                        break;
                    case 2:
                        additionalServices();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        display();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please try again.");
                scanner.nextLine();
                display();
            }
        }

        public void myAccount() {
            System.out.println("My Account page");

            // Retrieve the current user's username from the session
            String currentUser = WelcomePage.UserSession.getInstance().getCurrentUser();

            // Check if the current user is set
            if (currentUser == null || currentUser.isEmpty()) {
                System.out.println("No user is currently logged in.");
                return;
            }

            // Attempt to display the current user's information from the database.txt file
            try {
                File file = new File("database.txt");
                Scanner fileScanner = new Scanner(file);

                boolean userFound = false;
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    // Split the line into credentials assuming they are separated by a comma
                    String[] credentials = line.split(",");
                    // Check if the line contains user information and matches the current user
                    if (credentials.length == 6 && credentials[4].equals(currentUser)) {
                        System.out.println("Name: " + credentials[0]);
                        System.out.println("Surname: " + credentials[1]);
                        System.out.println("Email: " + credentials[2]);
                        System.out.println("Phone number: " + credentials[3]);
                        System.out.println("Username: " + credentials[4]);
                        // Avoid displaying the password for security reasons
                        System.out.println("Redirecting to Home Page...");
                        HomePage homePage = new HomePage();
                        homePage.display();
                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    System.out.println("User information not found in the database.");
                }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        public void additionalServices() {
            System.out.println("Additional Services page");

            // Option to delete an account
            System.out.println("Do you want to delete your account? (yes/no)");
            String input = scanner.nextLine();
            if ("yes".equalsIgnoreCase(input)) {
                // Call the method that handles account deletion
                deleteAccount();
            } else {
                System.out.println("No changes were made to your account.");
            }

            // Redirect to the Home Page
            WelcomePage welcomePage = new WelcomePage();
            welcomePage.display();
        }

        public void deleteAccount() {
            String currentUser = WelcomePage.UserSession.getInstance().getCurrentUser();
            if (currentUser == null || currentUser.isEmpty()) {
                System.out.println("No user is currently logged in.");
                return;
            }

            Path path = Paths.get("database.txt");
            List<String> fileContent;

            try {
                fileContent = new ArrayList<>(Files.readAllLines(path));
                boolean accountDeleted = false;
                for (int i = 0; i < fileContent.size(); i++) {
                    String[] credentials = fileContent.get(i).split(",");
                    if (credentials.length == 6 && credentials[4].equals(currentUser)) {
                        fileContent.remove(i);
                        accountDeleted = true;
                        break;
                    }
                }
                if (accountDeleted) {
                    Files.write(path, fileContent);
                    System.out.println("Account '" + currentUser + "' has been deleted.");
                    // Delete the user's certificates
                    RecentsPage recentsPage = new RecentsPage();
                    recentsPage.deleteUserCertificates();
                    WelcomePage.UserSession.getInstance().setCurrentUser(null); // Clear the current user session
                } else {
                    System.out.println("Account not found.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while accessing the database.");
                e.printStackTrace();
            }

            // Redirect to the Home Page
            HomePage homePage = new HomePage();
            homePage.display();
        }

        public static class RecentsPage {
            Scanner scanner = new Scanner(System.in);

            public void display() {
                String currentUser = WelcomePage.UserSession.getInstance().getCurrentUser();
                System.out.println("Recent Certificates Page for " + currentUser);

                // Display recent certificates for the current user
                try {
                    File file = new File(currentUser + "certificates.txt");
                    Scanner fileScanner = new Scanner(file);

                    List<String> certificates = new ArrayList<>();
                    while (fileScanner.hasNextLine()) {
                        certificates.add(fileScanner.nextLine());
                    }

                    if (certificates.isEmpty()) {
                        System.out.println("No recent certificates found.");
                    } else {
                        System.out.println("Recent certificates:");
                        for (String certificate : certificates) {
                            System.out.println(certificate);
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("No certificate file found for " + currentUser);
                }

                System.out.println("Redirecting to Home Page...");
                HomePage homePage = new HomePage();
                homePage.display();

            }

            // Method to delete user's certificates when account is deleted or user logs out
            public void deleteUserCertificates() {
                String currentUser = WelcomePage.UserSession.getInstance().getCurrentUser();
                File file = new File(currentUser + "certificates.txt");
                if (file.delete()) {
                    System.out.println("Certificates for " + currentUser + " have been deleted.");
                } else {
                    System.out.println("No certificates to delete for " + currentUser);
                }
            }

            public static class MakeDesignPage {
                Scanner scanner = new Scanner(System.in);

                public void display() {
                    System.out.println("Make Design Page");

                    // Add code to create a new certificate design
                    System.out.println("Please enter the design details:");

                    System.out.println("Certificate Title:");
                    String title = scanner.nextLine();

                    System.out.println("Recipient Name:");
                    String recipient = scanner.nextLine();

                    System.out.println("Issue Date:");
                    String issueDate = scanner.nextLine();

                    // Save the certificate design details in a file
                    try {
                        String currentUser = WelcomePage.UserSession.getInstance().getCurrentUser();
                        FileWriter writer = new FileWriter(currentUser + "certificates.txt", true); // Open the file in append mode
                        writer.write("==== Certificate ====\n");
                        writer.write("Title: " + title + "\n");
                        writer.write("Recipient: " + recipient + "\n");
                        writer.write("Issue Date: " + issueDate + "\n");
                        writer.write("=====================\n\n");
                        writer.close();
                        System.out.println("Certificate design saved successfully!");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                    // Redirect to Home Page after saving the certificate design
                    HomePage homePage = new HomePage();
                    homePage.display();
                }
            }
        }
    }
}
