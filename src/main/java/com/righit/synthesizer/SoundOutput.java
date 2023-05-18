package com.righit.synthesizer;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class SoundOutput extends Thread{

    public AudioHandler audioHandler;
    public byte note;
    public SourceDataLine sourceLine;
    public boolean isPlaying;

    SoundOutput(AudioHandler audioHandler, byte note) {


        this.audioHandler = audioHandler;
        this.note = note;
        isPlaying = true;

        try {
            DataLine.Info LineInfo = new DataLine.Info(SourceDataLine.class, audioHandler.getAudioFormat());
            sourceLine = (SourceDataLine)audioHandler.getMixer().getLine(LineInfo);
            sourceLine.open();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        sourceLine.start();

        byte[] signal = new byte[AudioHandler.BUFFER_SIZE];
        double wavePos = 0;

        while (isPlaying) {

            for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                signal[i] = (byte) (Byte.MAX_VALUE * Math.sin((Math.PI * (440 * Math.pow(2, (double) (note - 69) /12)) ) / AudioHandler.SAMPLE_RATE * wavePos++ / 2));
                if (signal[i] < 0) {
                    signal[i] = Byte.MAX_VALUE;
                }
                else {
                    signal[i] = -Byte.MAX_VALUE;
                }
            }


            sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);

        }

        sourceLine.flush();
        sourceLine.close();


    }


    public void stopNote() {
        isPlaying = false;
    }

}
