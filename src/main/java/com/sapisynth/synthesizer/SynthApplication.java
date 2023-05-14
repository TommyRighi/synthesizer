package com.sapisynth.synthesizer;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SynthApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SynthApplication.class.getResource("synth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        AudioHandler audio = new AudioHandler();
    }

    public static void main(String[] args) {
        launch();
    }
}