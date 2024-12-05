package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    // Handle login button action
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = userNameField.getText();
        String password = passwordField.getText();

        // Validate user credentials
        if (validateUser(username, password)) {
            showAlert("Login Successful", "Welcome " + username + "!", AlertType.INFORMATION);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
                Scene dashboardScene = new Scene(loader.load());
                Stage currentStage = (Stage) userNameField.getScene().getWindow();
                currentStage.setScene(dashboardScene);
                currentStage.setTitle("Dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Login Failed", "Invalid username or password", AlertType.ERROR);
        }
    }

    // Handle register button action
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        try {
            // Load the register screen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/registerScreen.fxml")); // Correct path here
            Scene registerScene = new Scene(loader.load());

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) userNameField.getScene().getWindow();  // Get current stage
            currentStage.setScene(registerScene);  // Set new scene

            // Optionally, you can set the title of the new stage
            currentStage.setTitle("Register");

            // Show the register screen
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the registration page", AlertType.ERROR);
        }
    }

    // Validate user by checking if username and password match the record in the file
    private boolean validateUser(String username, String password) {
        String userFilePath = "src/data/users.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;  // User found with matching credentials
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  // No matching user found
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
