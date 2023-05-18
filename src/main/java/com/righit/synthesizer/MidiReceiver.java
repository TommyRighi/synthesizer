package com.righit.synthesizer;

import javax.sound.midi.*;

public class MidiReceiver implements Receiver {
    @Override
    public void send(MidiMessage message, long timeStamp) {
        System.out.println(message.getMessage().toString());

        
    }

    @Override
    public void close() {

    }




}