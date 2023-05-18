package com.righit.synthesizer;


import javax.sound.sampled.*;



public class AudioHandler {

    static final int BUFFER_SIZE = 512;
    static final int SAMPLE_RATE = 48000;

    public String[] mixersNames;
    public Mixer.Info[] mixerInfo;
    public Mixer mixer;
    AudioFormat audioFormat;


    AudioHandler() {

        mixerInfo = AudioSystem.getMixerInfo();
        audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, SAMPLE_RATE, 16, 2, 4, SAMPLE_RATE, false);


        mixersNames = new String[mixerInfo.length];
        for (int i = 0; i < mixerInfo.length; i++) {
            mixersNames[i] = mixerInfo[i].getName();
        }

    }

    public void setMixer(String nome) {
        for (Mixer.Info i : mixerInfo) {
            if (i.getName().equals(nome)) {
                mixer = AudioSystem.getMixer(i);
            }
        }

        //Debugging
        System.out.println(mixer.getMixerInfo().getName());
    }


    public String[] getMixersNames(){
        return mixersNames;
    }

    public Mixer getMixer() {
        return mixer;
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }


}
