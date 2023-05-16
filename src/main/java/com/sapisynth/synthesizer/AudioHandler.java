package com.sapisynth.synthesizer;


import javax.sound.sampled.*;



public class AudioHandler {

    static final int BUFFER_SIZE = 256;
    static final int SAMPLE_RATE = 48000;



/*  Funzione di prova 1
    AudioHandler() {

        Mixer.Info[] mixers = getMixerInfo();

        for (Mixer.Info mixerInfo : mixers) {
            System.out.println(mixerInfo.getName());
        }

        Mixer mixer = getMixer(mixers[4]);
        Clip clip = null;
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        try {
            clip = (Clip)mixer.getLine(dataInfo);
        }
        catch (LineUnavailableException lineNotAvailable) {
            lineNotAvailable.printStackTrace();
        }

        try {


            URL tryUrl = AudioHandler.class.getResource("synthesizer/SFM_-Shrekophone.wav");

            if (tryUrl == null) {
                System.out.println("Non mi prende il sax");
                return;
            }


            AudioInputStream audioStream = getAudioInputStream(tryUrl);

            clip.open(audioStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        clip.start();


        do {
            try {
                Thread.sleep(2000);
                System.out.println("Running");
            }
            catch (InterruptedException interrupted) {
                interrupted.printStackTrace();
            }

        } while (clip.isActive());

        //clip.ope

        Mixer mixer = null;
        for (Mixer.Info i : mixers) {
            try {
                mixer = getMixer(i);
            }
            catch (Exception e) {
                System.out.println("Mixer" + i.getName() + "non disponibile");
            }
        }


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




    }
*/

    AudioHandler() {


        byte[] sineWave = new byte[BUFFER_SIZE];

        for (int i = 0; i < BUFFER_SIZE; i++) {
            sineWave[i] = (byte)(Byte.MAX_VALUE * Math.sin((2 * Math.PI * 500) / SAMPLE_RATE * i));
            if (sineWave[i] > 0) {
                sineWave[i] = Byte.MAX_VALUE;
            }
            else {
                sineWave[i] = - Byte.MAX_VALUE;
            }
        }

        for (byte i : sineWave) {
            System.out.println(i);
        }



        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        Mixer mixer = AudioSystem.getMixer(mixerInfo[4]);




        AudioFormat audioFormat = null;
        audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, SAMPLE_RATE, 16, 2, 4, SAMPLE_RATE, false);

        try {
            DataLine.Info LineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            final SourceDataLine sourceLine = (SourceDataLine)mixer.getLine(LineInfo);
            sourceLine.open();

            Thread sourceAudioThread = new Thread(() -> {

                sourceLine.start();

                while (true) {
                    sourceLine.write(sineWave,0, BUFFER_SIZE);
                }
            });

            sourceAudioThread.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Linea non disponibile");
        }






    }




}
