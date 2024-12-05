package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    // Handle register button action
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String username = userNameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            showAlert("Password Mismatch", "Passwords do not match!", AlertType.ERROR);
            return;
        }

        // Validate user name and password (check if they are empty or already exist)
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Invalid Input", "Username or password cannot be empty.", AlertType.ERROR);
            return;
        }
        
        if (isUsernameTaken(username)) {
            showAlert("Username Taken", "The username is already taken.", AlertType.ERROR);
            return;
        }

        // Save new user credentials to file
        saveUser(username, password);
        showAlert("Registration Successful", "Welcome " + username + "!", AlertType.INFORMATION);
        // Code to switch back to login screen can go here
    }

    // Check if user name already exists in the file
    private boolean isUsernameTaken(String username) {
        String userFilePath = "src/data/users.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username)) {
                    return true;  // User name is already taken
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  // User name is available
    }

    // Save new user to the file
    private void saveUser(String username, String password) {
        String userFilePath = "src/data/users.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Utility function to show alerts
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
