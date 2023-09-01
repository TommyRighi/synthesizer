package com.righit.synthesizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SynthController {

    @FXML private ComboBox<String> WaveTableSelector1;
    @FXML private ComboBox<Integer> nVoiceSelector1;
    @FXML private RadioButton oscillatorSwitch1;
    @FXML private Slider blendControl1;
    @FXML private Slider spreadControl1;
    @FXML private Slider panControl1;
    @FXML private Slider volumeControl1;
    @FXML private LineChart<Number, Number> waveTableVisualizer1;
    @FXML private NumberAxis xAxis1;
    @FXML private NumberAxis yAxis1;


    @FXML private ComboBox<String> WaveTableSelector2;
    @FXML private ComboBox<Integer> nVoiceSelector2;
    @FXML private RadioButton oscillatorSwitch2;
    @FXML private Slider blendControl2;
    @FXML private Slider spreadControl2;
    @FXML private Slider panControl2;
    @FXML private Slider volumeControl2;
    @FXML private LineChart<Number, Number> waveTableVisualizer2;
    @FXML private NumberAxis xAxis2;
    @FXML private NumberAxis yAxis2;


    @FXML private ComboBox<String> WaveTableSelector3;
    @FXML private ComboBox<Integer> nVoiceSelector3;
    @FXML private RadioButton oscillatorSwitch3;
    @FXML private Slider blendControl3;
    @FXML private Slider spreadControl3;
    @FXML private Slider panControl3;
    @FXML private Slider volumeControl3;
    @FXML private LineChart<Number, Number> waveTableVisualizer3;
    @FXML private NumberAxis xAxis3;
    @FXML private NumberAxis yAxis3;




    @FXML private Slider masterVolume;
    @FXML private ComboBox<String> audioInterfaceSelector;
    @FXML private ComboBox<String> midiInterfaceSelector;
    @FXML private Label midiTextLabel;
    @FXML private Button midiokButton;
    @FXML private Button activatePresetButton;
    @FXML private Button activateSavedPreset;
    @FXML private Button initPresetButton;
    @FXML private TextField presetName;
    @FXML private TableColumn<String, SoundProperties> presetColumn;
    @FXML private TableView<SoundProperties> presetTable;
    @FXML private Button minusButton;
    @FXML private Button plusButton;


    public final String[] signalShapes = {"Sine", "Square", "Saw", "Triangle", "Pulse20", "LoFi", "Synthwave", "Noise"};


    public AudioHandler audioHandler;
    public MidiHandler midiHandler;
    public SoundProperties soundProperties;
    public ObservableList<SoundProperties> observablePresets;


    //Inizializzazione di tutti i componenti del controller
    @FXML
    void initialize() {
        audioHandler = new AudioHandler();
        midiHandler = new MidiHandler(audioHandler);

        initValues();
        audioInterfaceSelectorInitialization();
        midiInterfaceSelectorInitialization();

        tableInirialization();

        soundProperties = new SoundProperties();
        midiHandler.setSoundProperties(soundProperties);

    }


    //funzioni di inizializzazione
    void initValues() {
        hideInTheBush();

        initPresetValues();

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
        WaveTableSelector1.getItems().addAll(signalShapes);
        WaveTableSelector1.getSelectionModel().select("Sine");

        WaveTableSelector2.getItems().removeAll(WaveTableSelector2.getItems());
        WaveTableSelector2.getItems().addAll(signalShapes);
        WaveTableSelector2.getSelectionModel().select("Square");

        WaveTableSelector3.getItems().removeAll(WaveTableSelector3.getItems());
        WaveTableSelector3.getItems().addAll(signalShapes);
        WaveTableSelector3. getSelectionModel().select("Saw");
    }

    void nVoicesSelectorInitialization() {

        nVoiceSelector1.getItems().removeAll(nVoiceSelector1.getItems());
        nVoiceSelector1.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        nVoiceSelector1.getSelectionModel().select(0);

        nVoiceSelector2.getItems().removeAll(nVoiceSelector2.getItems());
        nVoiceSelector2.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        nVoiceSelector2.getSelectionModel().select(0);

        nVoiceSelector3.getItems().removeAll(nVoiceSelector3.getItems());
        nVoiceSelector3.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
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
        audioInterfaceSelector.setValue(arrayMixers.get(1)); //Uno è il Mixer predefinito
    }

    void midiInterfaceSelectorInitialization() {
        ArrayList<String> arrayMidis = midiHandler.getMidisNames();

        midiInterfaceSelector.getItems().removeAll(midiInterfaceSelector.getItems());
        midiInterfaceSelector.getItems().addAll(arrayMidis);
        midiInterfaceSelector.setValue(arrayMidis.get(1)); //Uno è il device Midi predefinito
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

        minusButton.setVisible(false);
        plusButton.setVisible(false);
        presetTable.setVisible(false);
        presetName.setVisible(false);
        activateSavedPreset.setVisible(false);
    }



    //bottoni per il cambio di mixer audio e source midi
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



    //funzione che aggiorna la finestra e attiava il preset
    //sotto funzione per la visualizzazione delle onde su schermo
    @FXML void update(MouseEvent event) {
        soundProperties = new SoundProperties(
                new boolean[] {oscillatorSwitch1.isSelected(), oscillatorSwitch2.isSelected(), oscillatorSwitch3.isSelected()},
                new String[] {WaveTableSelector1.getSelectionModel().getSelectedItem(), WaveTableSelector2.getSelectionModel().getSelectedItem(), WaveTableSelector3.getSelectionModel().getSelectedItem()},
                new int[] {nVoiceSelector1.getSelectionModel().getSelectedItem(), nVoiceSelector2.getSelectionModel().getSelectedItem(), nVoiceSelector3.getSelectionModel().getSelectedItem()},
                new double[] {spreadControl1.getValue(), spreadControl2.getValue(), spreadControl3.getValue()},
                new double[] {blendControl1.getValue(), blendControl2.getValue(), blendControl3.getValue()},
                new double[] {volumeControl1.getValue(), volumeControl2.getValue(), volumeControl3.getValue()},
                new double[] {panControl1.getValue(), panControl2.getValue(), panControl3.getValue()},
                0,
                masterVolume.getValue()
        );

        if (!presetTable.isVisible()) {
            presetTable.setVisible(true);
            plusButton.setVisible(true);
            minusButton.setVisible(true);
            presetName.setVisible(true);
            activateSavedPreset.setVisible(true);
        }

        midiHandler.setSoundProperties(soundProperties);
        showSignalWaves();
    }


    void showSignalWaves() {

        XYChart.Series<Number, Number> series[] = new XYChart.Series[3];
        series[0] = new XYChart.Series<>();
        series[1] = new XYChart.Series<>();
        series[2] = new XYChart.Series<>();
        waveTableVisualizer1.getData().removeAll(waveTableVisualizer1.getData());
        waveTableVisualizer2.getData().removeAll(waveTableVisualizer2.getData());
        waveTableVisualizer3.getData().removeAll(waveTableVisualizer3.getData());

        if (oscillatorSwitch1.isSelected()) {
            waveTableVisualizer1.getData().add(series[0]);
            for (int i = 0; i < 100; i++) {
                series[0].getData().add(new XYChart.Data<>(i, returnVisualizerValue(i, WaveTableSelector1.getValue())));
            }
        }

        if (oscillatorSwitch2.isSelected()) {
            waveTableVisualizer2.getData().add(series[1]);
            for (int i = 0; i < 100; i++) {
                series[1].getData().add(new XYChart.Data<>(i, returnVisualizerValue(i, WaveTableSelector2.getValue())));
            }
        }

        if (oscillatorSwitch3.isSelected()) {
            waveTableVisualizer3.getData().add(series[2]);
            for (int i = 0; i < 100; i++) {
                series[2].getData().add(new XYChart.Data<>(i, returnVisualizerValue(i, WaveTableSelector3.getValue())));
            }
        }


    }

    double returnVisualizerValue(int i, String waveform){

        double heightMultiplier = 50;

        switch (waveform) {
            case "Sine" -> {
                return heightMultiplier * AudioSignals.getSine(i);
            }
            case "Square" -> {
                return heightMultiplier * AudioSignals.getPulse(i, 1/2d);
            }
            case "Saw" -> {
                return heightMultiplier * AudioSignals.getSaw(i);
            }
            case "Triangle" -> {
                return heightMultiplier * AudioSignals.getTriangle(i);
            }
            case "Pulse20" -> {
                return heightMultiplier * AudioSignals.getPulse(i, 2/10d);
            }
            case "LoFi" -> {
                return heightMultiplier * AudioEffects.softClip(AudioSignals.getSine(i));
            }
            case "Synthwave" -> {
                return heightMultiplier * AudioEffects.softClip(AudioSignals.getTriangle(i));
            }
            case "Noise" -> {
                return heightMultiplier * (Math.random() - 0.5);
            }
            default -> {
                System.out.println("Errore scelta wavetable");
                return -1;
            }
        }

    }




    //Gestione dei preset
    @FXML
    void addPreset(MouseEvent event) {

        String name = presetName.getText();

        if (name.equals("")) {
            return;
        }

        boolean controll = false;

        while (!controll) {
            controll = true;

            for (SoundProperties n : observablePresets) {
                if (n.getNameOfThePreset().equals(name)) {
                    name = name + "_x";
                    controll = false;
                }
            }
        }


        soundProperties.setNameOfThePreset(name);
        observablePresets.add(new SoundProperties(soundProperties));

    }

    @FXML
    void removePreset(MouseEvent event) {

        try {
            int selectedIndex = selectedIndex();
            presetTable.getItems().remove(selectedIndex);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void activateSavedPreset(MouseEvent event) {

        int selectedIndex = selectedIndex();
        SoundProperties selected = presetTable.getItems().get(selectedIndex);

        setWindowValues(selected);

        midiHandler.setSoundProperties(selected);
        showSignalWaves();

    }

    void setWindowValues(SoundProperties selected) {

        oscillatorSwitch1.setSelected(selected.activeOscillators[0]);
        oscillatorSwitch2.setSelected(selected.activeOscillators[1]);
        oscillatorSwitch3.setSelected(selected.activeOscillators[2]);

        masterVolume.setValue(selected.masterVolume);

        nVoiceSelector1.setValue(selected.nVoices[0]);
        nVoiceSelector2.setValue(selected.nVoices[1]);
        nVoiceSelector3.setValue(selected.nVoices[2]);

        blendControl1.setValue(selected.blend[0]);
        blendControl2.setValue(selected.blend[1]);
        blendControl3.setValue(selected.blend[2]);

        panControl1.setValue(selected.pans[0]);
        panControl2.setValue(selected.pans[1]);
        panControl3.setValue(selected.pans[2]);

        spreadControl1.setValue(selected.spread[0]);
        spreadControl2.setValue(selected.spread[1]);
        spreadControl3.setValue(selected.spread[2]);

        volumeControl1.setValue(selected.volumes[0]);
        volumeControl2.setValue(selected.volumes[1]);
        volumeControl3.setValue(selected.volumes[2]);

        WaveTableSelector1.setValue(selected.waveTable[0]);
        WaveTableSelector2.setValue(selected.waveTable[1]);
        WaveTableSelector3.setValue(selected.waveTable[2]);
        showSignalWaves();

    }

    int selectedIndex() {
        int selectedIndex = presetTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            throw new NoSuchElementException();
        }
        return selectedIndex;
    }


    void tableInirialization() {
        observablePresets = FXCollections.observableArrayList();

        presetColumn.setCellValueFactory(new PropertyValueFactory<>("nameOfThePreset"));

        presetTable.setItems(observablePresets);
    }
}