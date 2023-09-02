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
    public double attack;

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

        switch (wave) {
            case "Sine" -> {
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * AudioSignals.getSine(pitch, wavePos++));
                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Square" -> {
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * AudioSignals.getPulse(pitch, wavePos++, 1/2d));
                    }

                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Saw" -> {
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {

                        signal[i] = (byte) (Byte.MAX_VALUE * AudioSignals.getSaw(pitch, wavePos++));
                    }


                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Triangle" -> {
                while (isPlaying) {

                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * AudioSignals.getTriangle(pitch, wavePos++));
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
            case "Pulse20" -> {
                while (isPlaying) {
                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * AudioSignals.getPulse(pitch, wavePos++, 2/10d));
                    }
                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "LoFi" -> {
                double sample;
                while (isPlaying) {
                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        sample = AudioSignals.getSine(pitch, wavePos++);
                        sample = AudioEffects.softClip(sample);

                        signal[i] = (byte) (sample * Byte.MAX_VALUE);
                        wavePos++;
                    }
                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);
                }
            }
            case "Synthwave" -> {
                double sample;
                while (isPlaying) {
                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        sample = AudioSignals.getTriangle(pitch, wavePos++);
                        sample = AudioEffects.softClip(sample);

                        signal[i] = (byte) (sample * Byte.MAX_VALUE);
                        wavePos++;
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
                e.printStackTrace();
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
    public void setAttack(double attack) {
        this.attack = attack;
    }
}
