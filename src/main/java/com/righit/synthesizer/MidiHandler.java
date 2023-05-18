package com.righit.synthesizer;

import javax.sound.midi.*;

public class MidiHandler {

    public MidiDevice midiDevice;
    public MidiDevice.Info[] midiInfo;
    public String[] midisNames;
    public MidiReceiver receiver;
    public AudioHandler audioHandler;
    public AudioTracker tracker = new AudioTracker();


    public MidiHandler(AudioHandler audioHandler) {

        this.audioHandler = audioHandler;

        midiInfo = MidiSystem.getMidiDeviceInfo();
        midisNames = new String[midiInfo.length];
        for (int i = 0; i < midiInfo.length; i++) {
            midisNames[i] = midiInfo[i].getName();
        }


        byte contatore = 0;
        for (MidiDevice.Info i :midiInfo) {
            try {
                midiDevice = MidiSystem.getMidiDevice(i);

                System.out.println(contatore++ + ") " +i.getName() + "\n" + i.getDescription() + "\n" + i.getVendor());
                System.out.println();

            } catch (MidiUnavailableException e) {
                System.out.println("Device non disponibile");
                e.printStackTrace();
            }
        }

    }




    public String[] getMidisNames(){
        return midisNames;
    }



    public void setMidi(String chosenMidiName) {

        for (MidiDevice.Info i : midiInfo) {
            if (i.getName().equals(chosenMidiName)) {
                try {
                    midiDevice = MidiSystem.getMidiDevice(i);
                    midiDevice.open();
                } catch (MidiUnavailableException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //Debugging
        System.out.println(midiDevice.getDeviceInfo().getName());


        try {
            Transmitter transmitter = midiDevice.getTransmitter();

            if (midiDevice.isOpen()) {
                receiver = new MidiReceiver(audioHandler, tracker);

                System.out.println("Il transmitter è: " + transmitter.toString());
                transmitter.setReceiver(receiver);

                transmitter.getReceiver();
                System.out.println();
            }


        } catch (MidiUnavailableException e) {
            System.out.println("Errore unavaiacvdasg");
            e.printStackTrace();
        }
    }


}
