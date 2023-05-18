package com.righit.synthesizer;


import javax.sound.sampled.*;



public class AudioHandler {

    static final int BUFFER_SIZE = 512;
    static final int SAMPLE_RATE = 48000;

    public String[] mixersNames;
    public Mixer.Info[] mixerInfo;
    public Mixer mixer;
    AudioFormat audioFormat;

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

        mixerInfo = AudioSystem.getMixerInfo();
        audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, SAMPLE_RATE, 16, 2, 4, SAMPLE_RATE, false);


        mixersNames = new String[mixerInfo.length];
        for (int i = 0; i < mixerInfo.length; i++) {
            mixersNames[i] = mixerInfo[i].getName();
        }

        //Pc portatile
        //Mixer mixer = AudioSystem.getMixer(mixerInfo[4]);

        //Pc fisso
        //Mixer mixer = AudioSystem.getMixer(mixerInfo[6]);


/*
        try {
            DataLine.Info LineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            final SourceDataLine sourceLineSine = (SourceDataLine)mixer.getLine(LineInfo);
            sourceLineSine.open();


            Thread sourceAudioThreadSine = new Thread(() -> {

                sourceLineSine.start();

                byte[] sineWave = new byte[BUFFER_SIZE];
                double wavePos = 0;

                while (true) {

                    for (int i = 0; i < BUFFER_SIZE; i++) {
                        sineWave[i] = (byte)(Byte.MAX_VALUE * Math.sin((Math.PI * 120) / SAMPLE_RATE * wavePos++ / 2));
                    }

                    sourceLineSine.write(sineWave,0, BUFFER_SIZE);

                }
            });



            final SourceDataLine sourceLineSquare = (SourceDataLine)mixer.getLine(LineInfo);
            sourceLineSquare.open();

            Thread sourceAudioThreadSquare = new Thread(() -> {

                sourceLineSquare.start();

                byte[] sineWave = new byte[BUFFER_SIZE];
                double wavePos = 0;

                while (true) {

                    for (int i = 0; i < BUFFER_SIZE; i++) {
                        sineWave[i] = (byte)(Byte.MAX_VALUE * Math.sin((Math.PI * 60) / SAMPLE_RATE * wavePos++ / 2));
                        if (sineWave[i] < 0) {
                            sineWave[i] = Byte.MAX_VALUE;
                        }
                        else {
                            sineWave[i] = -Byte.MAX_VALUE;
                        }
                    }

                    sourceLineSquare.write(sineWave,0, BUFFER_SIZE);

                }
            });


            FloatControl panCtrlSine = (FloatControl) sourceLineSine.getControl(FloatControl.Type.PAN);
            FloatControl gainCtrlSine = (FloatControl) sourceLineSquare.getControl(FloatControl.Type.MASTER_GAIN);
            panCtrlSine.setValue(1);
            gainCtrlSine.setValue(6);
            sourceAudioThreadSine.start();




            FloatControl panCtrlSquare = (FloatControl) sourceLineSquare.getControl(FloatControl.Type.PAN);
            FloatControl gainCtrlSquare = (FloatControl) sourceLineSquare.getControl(FloatControl.Type.MASTER_GAIN);
            panCtrlSquare.setValue(-1);
            gainCtrlSquare.setValue(-10);
            sourceAudioThreadSquare.start();






        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Linea non disponibile");
        }

 */



    }

    public void setMixer(String nome) {
        for (Mixer.Info i : mixerInfo) {
            if (i.getName().equals(nome)) {
                mixer = AudioSystem.getMixer(i);
            }
        }

        //Debugging
        System.out.println(mixer.getMixerInfo().getName());
    }


    public String[] getMixersNames(){
        return mixersNames;
    }


}
