package com.sapisynth.synthesizer;


import javax.sound.sampled.*;

import static javax.sound.sampled.AudioSystem.*;

public class AudioHandler {


    AudioHandler() {

        Mixer.Info[] mixers = getMixerInfo();



        Line[] outputLines;
/*
        Mixer mixer = null;
        for (Mixer.Info i : mixers) {
            try {
                mixer = getMixer(i);
            }
            catch (Exception e) {
                System.out.println("Mixer" + i.getName() + "non disponibile");
            }
        }
*/
        Mixer mixer;

        //Line.Info[] lineInfo = mixer.getSourceLineInfo();




        for (Mixer.Info i : mixers) {
            mixer = getMixer(i);

            System.out.println(i.getName());
            System.out.println(i.getDescription());




            Line.Info[] linesInfo = mixer.getSourceLineInfo();
            for (Line.Info j : linesInfo) {
                System.out.println("\t" + j.toString());
            }

            System.out.println();
            System.out.println();
        }

/*
        try {
            chosenLine.open();
        } catch (LineUnavailableException e) {
            System.out.println("Errore apertura line");
        }
        System.out.println(chosenLine.isOpen());

        try {
            outputLines = mixer.getSourceLines();
        }
        catch (Exception e) {
            System.out.println("Errore");
            return;
        }

 */


    }





}
