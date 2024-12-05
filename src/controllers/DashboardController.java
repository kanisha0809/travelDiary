package controllers;

import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import models.JournalEntry;

public class DashboardController {

    @FXML
    private ListView<JournalEntry> journalListView;

    private List<JournalEntry> journalEntries;
    
    @FXML
    private Label welcomeLabel;
    
    public void setLoggedInUser(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }

    // Initialize the list of journal entries
    public void initialize() {
        // Assuming journalEntries is populated (could be from a file, database, etc.)
        journalEntries = loadEntries();  // Method to load existing entries
        journalListView.getItems().setAll(journalEntries);
    }

    // Action for Add Entry button
    @FXML
    private void handleAddEntryAction() {
        // Show dialog to enter the new journal entry
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Journal Entry");
        dialog.setHeaderText("Enter a title for your new entry:");
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String title = result.get();
            // Here you could add more fields like date, images, text, etc.
            JournalEntry newEntry = new JournalEntry(title, "Some text here..."); // Example entry
            journalEntries.add(newEntry);
            journalListView.getItems().setAll(journalEntries); // Refresh ListView
        }
    }

    // Action for Edit Entry button
    @FXML
    private void handleEditEntryAction() {
        JournalEntry selectedEntry = journalListView.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            // Open a dialog to edit the entry
            TextInputDialog dialog = new TextInputDialog(selectedEntry.getText());
            dialog.setTitle("Edit Journal Entry");
            dialog.setHeaderText("Edit your entry:");
            dialog.showAndWait().ifPresent(newText -> {
                selectedEntry.setText(newText);
                journalListView.refresh(); // Refresh ListView
            });
        }
    }

    // Action for Delete Entry button
    @FXML
    private void handleDeleteEntryAction() {
        JournalEntry selectedEntry = journalListView.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Entry");
            alert.setHeaderText("Are you sure you want to delete this entry?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                journalEntries.remove(selectedEntry);
                journalListView.getItems().setAll(journalEntries); // Refresh ListView
            }
        }
    }

    // Method to load journal entries (this could be from a file, database, etc.)
    private List<JournalEntry> loadEntries() {
        // For simplicity, add dummy data here
        return List.of(
            new JournalEntry("Trip to Paris", "It was an amazing trip!"),
            new JournalEntry("Birthday Celebration", "Had a great time with friends!")
        );
    }
}
