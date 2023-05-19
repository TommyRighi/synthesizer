package com.righit.synthesizer;

import javax.sound.midi.*;

public class MidiReceiver implements Receiver {

    public AudioHandler audioHandler;
    AudioTracker tracker;
    SoundProperties soundProperties;


    public MidiReceiver(AudioHandler audioHandler, AudioTracker tracker, SoundProperties soundProperties) {
        super();

        this.audioHandler = audioHandler;
        this.tracker = tracker;
        this.setSoundProperties(soundProperties);
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {



        if (message.getMessage().length > 1 /*&& (message.getMessage()[0] == -128 || message.getMessage()[0] == - 112)*/) {

            System.out.println(timeStamp);
            for (byte s : message.getMessage()) {
                System.out.println(s);
            }
            System.out.println();


            if (message.getMessage()[2] != 0) {

                for (int i = 0; i < soundProperties.N_OSCILLATORS; i++) {

                    if (soundProperties.getActiveOscillators()[i]) {

                        for (int j = 0; j < soundProperties.getnVoices()[i]; j++) {

                            SoundOutput nota = new SoundOutput(audioHandler, message.getMessage()[1]);

                            nota.setPan(soundProperties.getPans()[i] + (j * Math.pow(-1, j) * soundProperties.getSpread()[i]));
                            nota.setVolume((soundProperties.masterVolume + soundProperties.getVolumes()[i]) / 2 - (j * Math.pow(-1, j) * soundProperties.getBlend()[i]));
                            nota.setWave(soundProperties.waveTable[i]);
                            nota.setPitch(((double) (message.getMessage()[1] - 69) /12) + (j * Math.pow(-1, j) / 25 * soundProperties.getSpread()[i]));

                            nota.start();

                            tracker.addThread(nota.hashCode(), nota);

                        }

                    }

                }

            }
            else {

                for (Integer i : tracker.getThreadMap().keySet()) {
                    if (tracker.getThreadMap().get(i).note == message.getMessage()[1]) {
                        tracker.getThreadMap().get(i).stopNote();
                        tracker.removeThread(i);
                    }
                }

            }

        }

        
    }

    @Override
    public void close() {

    }

    void setSoundProperties(SoundProperties soundProperties) {
        this.soundProperties = soundProperties;
    }



}