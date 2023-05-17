module com.sapisynth.synthesizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.righit.synthesizer to javafx.fxml;
    exports com.righit.synthesizer;
}