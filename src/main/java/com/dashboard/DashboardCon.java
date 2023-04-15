package com.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardCon {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}