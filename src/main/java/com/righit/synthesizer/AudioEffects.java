package com.righit.synthesizer;

public class AudioEffects {

    public static double softClip(double signal) {

        if (signal < -2/3d) {
            signal = (-2/3d);
        }
        else if (signal > 2/3d) {
            signal = 2/3d;
        }
        else {
            signal = (signal - (Math.pow(signal, 3) / 3d));
        }

        return signal;
    }

    public static double hardClip(double signal) {
        if (signal > 0.5) {
            signal = 0.5;
        }
        if (signal < -0.5) {
            signal = -0.5;
        }

        return signal * 2;
    }

    public static double fuzz(double signal) {
        return hardClip(( signal / Math.abs(signal)) * ( 1 - Math.pow(Math.E, Math.pow(signal, 2) / Math.abs(signal))));
    }

}
