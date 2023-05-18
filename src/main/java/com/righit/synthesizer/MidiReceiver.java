package com.righit.synthesizer;

import javax.sound.midi.*;

public class MidiReceiver implements Receiver {

    public AudioHandler audioHandler;
    AudioTracker tracker;


    public MidiReceiver(AudioHandler audioHandler, AudioTracker tracker) {
        super();

        this.audioHandler = audioHandler;
        this.tracker = tracker;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {



        if (message.getMessage().length > 1) {
            System.out.println(timeStamp);
            for (byte s : message.getMessage()) {
                System.out.println(s);
            }
            System.out.println();


            if (message.getMessage()[2] != 0) {
                SoundOutput nota = new SoundOutput(audioHandler, message.getMessage()[1]);

                nota.start();

                tracker.addThread(nota.hashCode(), nota);
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



}