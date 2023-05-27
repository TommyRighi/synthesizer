module com.sapisynth.synthesizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.righit.synthesizer to javafx.fxml;
    exports com.righit.synthesizer;
}