package com.righit.synthesizer;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

public class SoundOutput extends Thread{

    public AudioHandler audioHandler;
    public byte note;
    public SourceDataLine sourceLine;
    public boolean isPlaying;
    public double pan;
    public double volume;
    public String wave;
    public double pitch;
    public FloatControl gain;
    public FloatControl panning;

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

        controls();

        sourceLine.start();

        byte[] signal = new byte[AudioHandler.BUFFER_SIZE];
        double wavePos = 0;
        double tDivP;

        switch (wave) {
            case "Sine" -> {
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * Math.sin((Math.PI * (440 * Math.pow(2, pitch)) / AudioHandler.SAMPLE_RATE * wavePos++ / 2)));
                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Square" -> {
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * Math.signum(Math.sin((Math.PI * (440 * Math.pow(2, pitch)) / AudioHandler.SAMPLE_RATE * wavePos++ / 2))));

                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Saw" -> {
                pitch = 100 * pitch;
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {

                        signal[i]=  (byte) (Byte.MAX_VALUE * (2 * ((wavePos++ / ((double) AudioHandler.SAMPLE_RATE / pitch)) - Math.floor(1/2d + (i / ((double) AudioHandler.SAMPLE_RATE / pitch))))));

                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Triangle" -> {
                tDivP = (wavePos % (double) AudioHandler.SAMPLE_RATE) / (1d / (pitch));
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i]=  (byte) (Byte.MAX_VALUE *(2 * (2 * Math.abs(tDivP - Math.floor(0.5 + tDivP))) - 1));
                        tDivP = (wavePos++ % (double) AudioHandler.SAMPLE_RATE) / (1d / (pitch));
                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Noise" -> {
                Random random = new Random();
                while (isPlaying) {


                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) random.nextInt();
                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            default -> System.out.println("Errore nella selezione wavetable");
        }

        for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
            try {
                gain.setValue(gain.getValue() - (gain.getMaximum() - gain.getMinimum() / 2 * AudioHandler.BUFFER_SIZE));
            }
            catch (Exception e) {
                System.out.println("LMAO");
            }

        }

        sourceLine.flush();
        sourceLine.close();


    }


    void controls(){

        gain = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue((float) volume);

        panning = (FloatControl) sourceLine.getControl(FloatControl.Type.PAN);
        panning.setValue((float) pan / 50);

    }


    public void stopNote() {
        isPlaying = false;
    }

    public void setPan(double pan) {
        this.pan = pan;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public void setPitch(double pitch){
        this.pitch = pitch;
    }
}
