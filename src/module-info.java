module traveDiaryApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	exports application;
	exports controllers;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
}
