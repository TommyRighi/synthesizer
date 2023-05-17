package com.righit.synthesizer;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;

import java.awt.event.ActionEvent;
import java.util.Arrays;

public class SynthController {

    @FXML private ComboBox<String> WaveTableSelector1;
    @FXML private ComboBox<Integer> nVoiceSelector1;
    @FXML private RadioButton oscillatorSwitch1;
    @FXML private Slider panControl1;
    @FXML private Slider volumeControl1;
    @FXML private LineChart<Integer, Byte> waveTableVisualizer1;

    @FXML private ComboBox<String> WaveTableSelector2;
    @FXML private ComboBox<Integer> nVoiceSelector2;
    @FXML private RadioButton oscillatorSwitch2;
    @FXML private Slider panControl2;
    @FXML private Slider volumeControl2;
    @FXML private LineChart<Integer, Byte> waveTableVisualizer2;


    @FXML private ComboBox<String> WaveTableSelector3;
    @FXML private ComboBox<Integer> nVoiceSelector3;
    @FXML private RadioButton oscillatorSwitch3;
    @FXML private Slider panControl3;
    @FXML private Slider volumeControl3;
    @FXML private LineChart<Integer, Byte> waveTableVisualizer3;


    @FXML private ComboBox<String> audioInterfaceSelector;
    @FXML private ComboBox<String> midiInterfaceSelector;


    AudioHandler audioHandler;
    MidiHandler provaMidi;

    @FXML
    void initialize() {
        audioHandler = new AudioHandler();

        waveTableSelectorInitialization();
        nVoicesSelectorInitialization();
        oscillatorSwitchInitialization();
        panControlInitialization();
        volumeControlInitialiation();

        audioInterfaceSelectorInitialization();


    }


    void waveTableSelectorInitialization() {
        WaveTableSelector1.getItems().removeAll(WaveTableSelector1.getItems());
        WaveTableSelector1.getItems().addAll("Sine", "Square", "Saw", "Noise");
        WaveTableSelector1.getSelectionModel().select("Sine");

        WaveTableSelector2.getItems().removeAll(WaveTableSelector2.getItems());
        WaveTableSelector2.getItems().addAll("Sine", "Square", "Saw", "Noise");
        WaveTableSelector2.getSelectionModel().select("Square");

        WaveTableSelector3.getItems().removeAll(WaveTableSelector3.getItems());
        WaveTableSelector3.getItems().addAll("Sine", "Square", "Saw", "Noise");
        WaveTableSelector3. getSelectionModel().select("Saw");
    }

    void nVoicesSelectorInitialization() {

        nVoiceSelector1.getItems().removeAll(nVoiceSelector1.getItems());
        nVoiceSelector1.getItems().addAll(1, 2, 3 , 4, 5);
        nVoiceSelector1.getSelectionModel().select(0);

        nVoiceSelector2.getItems().removeAll(nVoiceSelector2.getItems());
        nVoiceSelector2.getItems().addAll(1, 2, 3, 4, 5);
        nVoiceSelector2.getSelectionModel().select(0);

        nVoiceSelector3.getItems().removeAll(nVoiceSelector3.getItems());
        nVoiceSelector3.getItems().addAll(1, 2, 3, 4, 5);
        nVoiceSelector3.getSelectionModel().select(0);

    }


    void oscillatorSwitchInitialization() {

        oscillatorSwitch1.setSelected(true);

        oscillatorSwitch2.setSelected(false);

        oscillatorSwitch3.setSelected(false);

    }

    void panControlInitialization() {

        panControl1.adjustValue(0);

        panControl2.adjustValue(50);
        panControl2.adjustValue(0);

        panControl3.adjustValue(-50);
        panControl3.adjustValue(0);

    }


    void volumeControlInitialiation() {

        volumeControl1.adjustValue(70);

        volumeControl2.adjustValue(70);

        volumeControl3.adjustValue(70);

    }

    void audioInterfaceSelectorInitialization() {
        String[] arrayMixers = audioHandler.getMixersNames();

        audioInterfaceSelector.getItems().removeAll(audioInterfaceSelector.getItems());
        audioInterfaceSelector.getItems().addAll(arrayMixers);
    }

    @FXML void changeMixer(ActionEvent event) {

    }

}