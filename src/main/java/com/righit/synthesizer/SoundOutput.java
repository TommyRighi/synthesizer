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

    SoundOutput(AudioHandler audioHandler, byte note) {


        this.audioHandler = audioHandler;
        this.note = note;
        isPlaying = true;

        try {
            DataLine.Info LineInfo = new DataLine.Info(SourceDataLine.class, audioHandler.getAudioFormat());
            sourceLine = (SourceDataLine)audioHandler.getMixer().getLine(LineInfo);
            sourceLine.open();

            FloatControl gain = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue((float) volume / 100 * gain.getMaximum());

            FloatControl panning = (FloatControl) sourceLine.getControl(FloatControl.Type.PAN);
            panning.setValue((float) pan / 50);

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

        switch (wave) {
            case "Sine":
                while (isPlaying) {


                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * Math.sin((Math.PI * (440 * Math.pow(2, pitch) ) / AudioHandler.SAMPLE_RATE * wavePos++ / 2)));

                    }


                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);

                }
                break;
            case "Square":
                while (isPlaying) {


                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) (Byte.MAX_VALUE * Math.sin((Math.PI * (440 * Math.pow(2, pitch) ) / AudioHandler.SAMPLE_RATE * wavePos++ / 2)));
                        if (signal[i] < 0) {
                            signal[i] = Byte.MAX_VALUE;
                        }
                        else {
                            signal[i] = -Byte.MAX_VALUE;
                        }
                    }


                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);

                }
                break;
            case "Noise":
                Random random = new Random();
                while (isPlaying) {


                    for (int i = 0; i < AudioHandler.BUFFER_SIZE; i++) {
                        signal[i] = (byte) random.nextInt();
                    }
                    sourceLine.write(signal, 0, AudioHandler.BUFFER_SIZE);

                }
                break;
            case "Saw":

            default:
                System.out.println("Errore nella selezione wavetable");
        }



        sourceLine.flush();
        sourceLine.close();


    }


    public void stopNote() {
        isPlaying = false;
    }

    public double getPan() {
        return pan;
    }

    public void setPan(double pan) {
        this.pan = pan;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getWave() {
        return wave;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public void setPitch(double pitch){
        this.pitch = pitch;
    }
}
