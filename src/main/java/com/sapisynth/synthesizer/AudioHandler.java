package com.sapisynth.synthesizer;


import javax.sound.sampled.*;

import static javax.sound.sampled.AudioSystem.*;

public class AudioHandler {


    AudioHandler() {

        Mixer.Info[] mixers = getMixerInfo();



        Mixer mixer = getMixer(mixers[0]);

        Line outputLine;
        try {
            outputLine = mixer.getLine(mixer.getLineInfo());
        }
        catch (Exception e) {
            System.out.println("Errore");
            return;
        }

        if (!outputLine.isOpen()) {
            try {
                outputLine.open();
            }
            catch (Exception e)
            {
                System.out.println("Errore");
                return;
            }
        }


        Control[] lineControls = outputLine.getControls();

        for (Control i : lineControls) {
            System.out.println(i.toString());
        }

    }





}
