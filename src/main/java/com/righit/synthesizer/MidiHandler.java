package com.righit.synthesizer;

import javax.sound.midi.*;
import java.util.List;

public class MidiHandler {


    public MidiHandler() {

        MidiDevice midiDevice;
        MidiDevice.Info[] midiInfo = MidiSystem.getMidiDeviceInfo();


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


    }


}
