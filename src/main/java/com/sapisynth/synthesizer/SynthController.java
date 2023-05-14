package com.sapisynth.synthesizer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SynthController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}