package com.righit.synthesizer;

public class AudioSignals {

    public static double getSine(double pitch, double wavePos) {
        return Math.sin((Math.PI * (440 * Math.pow(2, pitch)) / AudioHandler.SAMPLE_RATE * wavePos / 2));
    }

    public static double getSine(int i) {
        return Math.sin(2d * i / 100 * Math.PI);
    }



    public static double getSaw(double pitch, double wavePos) {
        double wavePosNormalized = wavePos / (double) AudioHandler.SAMPLE_RATE;
        double frequency = Math.pow(2, pitch) * 110.0;
        double phase = (wavePosNormalized * frequency) % 1.0;

        return 2.0 * phase - 1.0;
    }

    public static double getSaw(int i) {
        return (2 * ((i / 100d) - Math.floor(1/2d + i / 100d)));
    }



    public static double getTriangle(double pitch, double wavePos) {
        double wavePosNormalized = wavePos / (double) AudioHandler.SAMPLE_RATE;
        double frequency = Math.pow(2, pitch) * 110.0;
        double phase = (wavePosNormalized * frequency) % 1.0;
        double sampleValue = 0.0;

        if (phase <= 0.5) {
            sampleValue = 4.0 * phase - 1.0;
        } else {
            sampleValue = -4.0 * phase + 3.0;
        }

        return sampleValue;
    }

    public static double getTriangle(int i) {
        double tempV = ((i / 100d) - Math.floor(i / 100d)) * 2;
        return (2 * ((tempV < 1) ? tempV : 2 - tempV) - 1);
    }




    public static double getPulse(double pitch, double wavePos, double pulseWidth) {
        double wavePosNormalized = wavePos / (double) AudioHandler.SAMPLE_RATE;
        double frequency = Math.pow(2, pitch) * 110.0;
        double period = 1.0 / frequency;

        double phase = (wavePosNormalized % period) / period;

        return (phase < pulseWidth) ? 1.0 : -1.0;
    }

    public static double getPulse(int i, double width) {
        if (i == 0) {
            return i;
        }

        return ((i < width * 100) ? (1) : (-1));
    }

}
