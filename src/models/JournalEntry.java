package models;

public class JournalEntry {

    private String title;
    private String text;

    // Constructor
    public JournalEntry(String title, String text) {
        this.title = title;
        this.text = text;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return title;
    }
}
