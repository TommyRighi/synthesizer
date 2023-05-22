package com.righit.synthesizer;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class SynthController {

    @FXML private ComboBox<String> WaveTableSelector1;
    @FXML private ComboBox<Integer> nVoiceSelector1;
    @FXML private RadioButton oscillatorSwitch1;
    @FXML private Slider blendControl1;
    @FXML private Slider spreadControl1;
    @FXML private Slider panControl1;
    @FXML private Slider volumeControl1;
    @FXML private LineChart<Integer, Byte> waveTableVisualizer1;

    @FXML private ComboBox<String> WaveTableSelector2;
    @FXML private ComboBox<Integer> nVoiceSelector2;
    @FXML private RadioButton oscillatorSwitch2;
    @FXML private Slider blendControl2;
    @FXML private Slider spreadControl2;
    @FXML private Slider panControl2;
    @FXML private Slider volumeControl2;
    @FXML private LineChart<Integer, Byte> waveTableVisualizer2;


    @FXML private ComboBox<String> WaveTableSelector3;
    @FXML private ComboBox<Integer> nVoiceSelector3;
    @FXML private RadioButton oscillatorSwitch3;
    @FXML private Slider blendControl3;
    @FXML private Slider spreadControl3;
    @FXML private Slider panControl3;
    @FXML private Slider volumeControl3;
    @FXML private LineChart<Integer, Byte> waveTableVisualizer3;



    @FXML private Slider masterAttack;
    @FXML private Slider masterVolume;
    @FXML private ComboBox<String> audioInterfaceSelector;
    @FXML private ComboBox<String> midiInterfaceSelector;
    @FXML private Label midiTextLabel;
    @FXML private Button midiokButton;
    @FXML private Button activatePresetButton;
    @FXML private Button initPresetButton;


    AudioHandler audioHandler;
    MidiHandler midiHandler;
    SoundProperties soundProperties;

    @FXML
    void initialize() {
        audioHandler = new AudioHandler();
        midiHandler = new MidiHandler(audioHandler);

        initValues();
        audioInterfaceSelectorInitialization();
        midiInterfaceSelectorInitialization();

        soundProperties = new SoundProperties();
        midiHandler.setSoundProperties(soundProperties);
    }


    void initValues() {
        hideInTheBush();

        initPresetValues();

        masterAttackInitialization();
        masterVolumeInitialization();
    }

    void initPresetValues() {

        waveTableSelectorInitialization();
        nVoicesSelectorInitialization();
        oscillatorSwitchInitialization();
        panControlInitialization();
        volumeControlInitialization();
        blendControlInitialization();
        spreadControlInitialization();

    }

    void masterAttackInitialization() {
        masterAttack.setValue(5);
    }

    void masterVolumeInitialization() {
        masterVolume.setValue(0);
    }

    void blendControlInitialization() {
        blendControl1.setValue(0);
        blendControl2.setValue(0);
        blendControl3.setValue(0);
    }

    void spreadControlInitialization() {
        spreadControl1.setValue(0);
        spreadControl2.setValue(0);
        spreadControl3.setValue(0);
    }

    void waveTableSelectorInitialization() {
        WaveTableSelector1.getItems().removeAll(WaveTableSelector1.getItems());
        WaveTableSelector1.getItems().addAll("Sine", "Square", "Saw", "Triangle", "Noise");
        WaveTableSelector1.getSelectionModel().select("Sine");

        WaveTableSelector2.getItems().removeAll(WaveTableSelector2.getItems());
        WaveTableSelector2.getItems().addAll("Sine", "Square", "Saw", "Triangle", "Noise");
        WaveTableSelector2.getSelectionModel().select("Square");

        WaveTableSelector3.getItems().removeAll(WaveTableSelector3.getItems());
        WaveTableSelector3.getItems().addAll("Sine", "Square", "Saw", "Triangle", "Noise");
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


    void volumeControlInitialization() {

        volumeControl1.adjustValue(-5);

        volumeControl2.adjustValue(-5);

        volumeControl3.adjustValue(-5);

    }

    void audioInterfaceSelectorInitialization() {
        ArrayList<String> arrayMixers = audioHandler.getMixersNames();


        audioInterfaceSelector.getItems().removeAll(audioInterfaceSelector.getItems());
        audioInterfaceSelector.getItems().addAll(arrayMixers);
    }

    void midiInterfaceSelectorInitialization() {
        ArrayList<String> arrayMidis = midiHandler.getMidisNames();

        midiInterfaceSelector.getItems().removeAll(midiInterfaceSelector.getItems());
        midiInterfaceSelector.getItems().addAll(arrayMidis);
    }

    @FXML void changeMixer(MouseEvent mouseEvent) {
        String mixerName = audioInterfaceSelector.getSelectionModel().getSelectedItem();

        if (mixerName != "") {
            audioHandler.setMixer(mixerName);

            if (!midiInterfaceSelector.isVisible()) {
                midiVisible();
            }
        }
    }


    @FXML void changeMidi(MouseEvent event) {
        String midiName = midiInterfaceSelector.getSelectionModel().getSelectedItem();

        if (midiName != "") {
            midiHandler.setMidi(midiName);

            if (!initPresetButton.isVisible()) {
                presetVisible();
            }
        }
    }

    @FXML void update(MouseEvent event) {
        soundProperties = new SoundProperties(
                new boolean[] {oscillatorSwitch1.isSelected(), oscillatorSwitch2.isSelected(), oscillatorSwitch3.isSelected()},
                new String[] {WaveTableSelector1.getSelectionModel().getSelectedItem(), WaveTableSelector2.getSelectionModel().getSelectedItem(), WaveTableSelector3.getSelectionModel().getSelectedItem()},
                new int[] {nVoiceSelector1.getSelectionModel().getSelectedItem(), nVoiceSelector2.getSelectionModel().getSelectedItem(), nVoiceSelector3.getSelectionModel().getSelectedItem()},
                new double[] {spreadControl1.getValue(), spreadControl2.getValue(), spreadControl3.getValue()},
                new double[] {blendControl1.getValue(), blendControl2.getValue(), blendControl3.getValue()},
                new double[] {volumeControl1.getValue(), volumeControl2.getValue(), volumeControl3.getValue()},
                new double[] {panControl1.getValue(), panControl2.getValue(), panControl3.getValue()},
                masterAttack.getValue(),
                masterVolume.getValue()
        );
        midiHandler.setSoundProperties(soundProperties);
    }

    @FXML
    void initPreset(MouseEvent event) {
        initPresetValues();
        update(null);
    }

    void midiVisible() {
        midiInterfaceSelector.setVisible(true);
        midiTextLabel.setVisible(true);
        midiokButton.setVisible(true);
    }

    void presetVisible() {
        activatePresetButton.setVisible(true);
        initPresetButton.setVisible(true);
    }

    void hideInTheBush() {
        midiInterfaceSelector.setVisible(false);
        midiTextLabel.setVisible(false);
        midiokButton.setVisible(false);

        activatePresetButton.setVisible(false);
        initPresetButton.setVisible(false);
    }

}