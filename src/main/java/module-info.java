module com.sapisynth.synthesizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.sapisynth.synthesizer to javafx.fxml;
    exports com.sapisynth.synthesizer;
}