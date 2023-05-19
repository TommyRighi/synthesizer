package com.righit.synthesizer;

import java.util.Arrays;

public class SoundProperties {

    public final int N_OSCILLATORS = 3;
    public boolean[] activeOscillators = new boolean[3];
    public String[] waveTable = new String[3];
    public int[] nVoices = new int[3];
    public double[] spread = new double[3];
    public double[] blend = new double[3];
    public double[] volumes = new double[3];
    public double[] pans = new double[3];
    public double attack;
    public double masterVolume;

    SoundProperties() {

    }

    public SoundProperties(boolean[] activeOscillators, String[] waveTable, int[] nVoices, double[] spread, double[] blend, double[] volumes, double[] pans, double attack, double masterVolume) {
        this.activeOscillators = activeOscillators;
        this.waveTable = waveTable;
        this.nVoices = nVoices;
        this.spread = spread;
        this.blend = blend;
        this.volumes = volumes;
        this.pans = pans;
        this.attack = attack;
        this.masterVolume = masterVolume;


        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SoundProperties{" +
                "nOscillators=" + Arrays.toString(activeOscillators) +
                ", waveTable=" + Arrays.toString(waveTable) +
                ", nVoices=" + Arrays.toString(nVoices) +
                ", spread=" + Arrays.toString(spread) +
                ", blend=" + Arrays.toString(blend) +
                ", volumes=" + Arrays.toString(volumes) +
                ", pans=" + Arrays.toString(pans) +
                ", attack=" + attack +
                ", masterVolume=" + masterVolume +
                '}';
    }

    public boolean[] getActiveOscillators() {
        return activeOscillators;
    }

    public int[] getnVoices() {
        return nVoices;
    }

    public double[] getSpread() {
        return spread;
    }

    public double[] getBlend() {
        return blend;
    }

    public double[] getVolumes() {
        return volumes;
    }

    public double[] getPans() {
        return pans;
    }

}
