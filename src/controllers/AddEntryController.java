package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.JournalEntry;
import java.io.File;

public class AddEntryController {

    @FXML
    private TextField titleField;
    @FXML
    private TextArea textArea;

    // Action for adding an image
    @FXML
    private void handleAddImageAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Here you could store the image path or handle it
            System.out.println("Image added: " + selectedFile.getAbsolutePath());
        }
    }

    // Action for saving a new journal entry
    @FXML
    private void handleSaveEntryAction() {
        String title = titleField.getText();
        String text = textArea.getText();
        if (!title.isEmpty() && !text.isEmpty()) {
            JournalEntry newEntry = new JournalEntry(title, text);
            // Add the new entry to the list (this could be passed to the DashboardController)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entry Saved");
            alert.setHeaderText("Your journal entry has been saved.");
            alert.showAndWait();
        }
    }
}
