package com.righit.synthesizer;

import javax.sound.midi.*;

public class MidiHandler {

    public MidiDevice midiDevice;
    public MidiDevice.Info[] midiInfo;
    public String[] midisNames;
    public MidiReceiver receiver;


    public MidiHandler() {

        midiInfo = MidiSystem.getMidiDeviceInfo();
        midisNames = new String[midiInfo.length];
        for (int i = 0; i < midiInfo.length; i++) {
            midisNames[i] = midiInfo[1].getName();
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


/*
        try {
            midiDevice = MidiSystem.getMidiDevice(midiInfo[5]);

            List<Transmitter> transmitters = midiDevice.getTransmitters();

            for (Transmitter i : transmitters) {

                //i.setReceiver();

            }

        } catch (MidiUnavailableException e) {
            System.out.println("Device non disponibile");
            e.printStackTrace();
        }
*/




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

            transmitter.setReceiver(receiver);

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }



    void temp() {


        Sequencer sequencer;


        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.getReceiver();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }




    }


}
