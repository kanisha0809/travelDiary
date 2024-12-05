package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    @FXML
    private ListView<String> journalListView;

    // Example of adding a sample entry for now
    @FXML
    public void initialize() {
        journalListView.getItems().add("Sample Entry - Click to Edit or Delete");
    }

    @FXML
    private void handleAddEntryAction() {
        // Navigate to Add Entry view
        System.out.println("Navigating to Add Entry...");
    }

    @FXML
    private void handleEditEntryAction() {
        String selectedEntry = journalListView.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            System.out.println("Editing Entry: " + selectedEntry);
        } else {
            showAlert("No Entry Selected", "Please select an entry to edit.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleDeleteEntryAction() {
        String selectedEntry = journalListView.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            journalListView.getItems().remove(selectedEntry);
            System.out.println("Deleted Entry: " + selectedEntry);
        } else {
            showAlert("No Entry Selected", "Please select an entry to delete.", Alert.AlertType.WARNING);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
