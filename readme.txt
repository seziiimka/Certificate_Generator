# Certificate Generator App

## Overview
The Certificate Generator App is a Java-based console application that allows users to register, log in, and create certificate designs. Users can manage their accounts and view their recent certificate designs.

## Features
1. **User Registration and Login**
   - Register with a name, surname, email, phone number, username, and password.
   - Log in using a username and password.
   - Forgot password feature to reset the password via email.

2. **Home Page**
   - Access to settings, recent works, and the make design page.
   - Option to quit the program.

3. **Settings Page**
   - View account details.
   - Delete the account and associated certificates.

4. **Recent Certificates**
   - View recent certificates created by the user.

5. **Make Design**
   - Create and save new certificate designs.

## Files
- **CertificateGenerator.java**: The main Java file containing all classes and methods.
- **database.txt**: Stores user account information in the format `name,surname,email,phoneNumber,username,password`.
- **{username}certificates.txt**: Stores certificate designs for each user.

## How to Run
1. Compile the Java program:

2. Run the program:

## Program Structure

### Main Class
- **CertificateGenerator**: The main class that starts the application by displaying the `WelcomePage`.

### Inner Classes
- **WelcomePage**: Displays the main menu and handles user input for login, registration, forgot password, and quitting the program.
- **WelcomePage.UserSession**: Manages the current user's session.
- **WelcomePage.Account**: Handles user login and forgot password functionality.
- **HomePage**: Displays the home page with options for settings, recent works, making designs, and quitting the program.
- **SettingsPage**: Provides options for viewing account details and additional services like deleting the account.
- **SettingsPage.RecentsPage**: Displays recent certificates and provides a method to delete user certificates.
- **SettingsPage.RecentsPage.MakeDesignPage**: Allows users to create and save new certificate designs.

## User Guide

### Register
1. Choose the "Register" option from the welcome page.
2. Enter the required details: name, surname, email, phone number, username, and password.
3. After successful registration, you will be redirected to the home page.

### Login
1. Choose the "Login" option from the welcome page.
2. Enter your username and password.
3. After successful login, you will be redirected to the home page.

### Forgot Password
1. Choose the "Forgot Password" option from the welcome page.
2. Enter your username.
3. A password reset link will be sent to the email associated with the username.

### Home Page
1. Choose an option:
- Settings: View account details or delete your account.
- Recent Works: View your recent certificate designs.
- Make Design: Create a new certificate design.
- Quit: Exit the program.

### Make Design
1. Enter the certificate title, recipient name, and issue date.
2. The certificate design will be saved, and you will be redirected to the home page.

## Error Handling
- The program handles invalid input and prompts the user to try again.
- If the database file is not found, an error message is displayed.

## Notes
- Ensure that `database.txt` is in the same directory as the Java program.
- The program uses a simple file-based approach to store user data and certificate designs.
- Email functionality for password reset is a placeholder and does not actually send an email.


