Distorsioni da implementare

Questi algoritmi funzionano con il segnale che va da -1 a 1



Soft clip
if (signal < -quantitadistorsione) {
    signal = -2/3;
}
else if (signal > quantitadistorsione) {
    signal = 2/3;
}
else {
    signal = signal - (signal * signal * signal / 3);
}



Hard clip:
if (signal < -quantitaDistorsione) {
    signal = -quantitadistorsione;
}
if (signal > quantitaDistorsione) {
    signal = quantitadistorsione;
}



Fuzz:

signal = ( signal / |signal| ) * ( 1 - e^(signal^2 / |signal|));










FloatControl panCtrlSine = (FloatControl) sourceLineSine.getControl(FloatControl.Type.PAN);
            FloatControl gainCtrlSine = (FloatControl) sourceLineSquare.getControl(FloatControl.Type.MASTER_GAIN);
            panCtrlSine.setValue(1);
            gainCtrlSine.setValue(6);
            sourceAudioThreadSine.start();




            FloatControl panCtrlSquare = (FloatControl) sourceLineSquare.getControl(FloatControl.Type.PAN);
            FloatControl gainCtrlSquare = (FloatControl) sourceLineSquare.getControl(FloatControl.Type.MASTER_GAIN);
            panCtrlSquare.setValue(-1);
            gainCtrlSquare.setValue(-10);
            sourceAudioThreadSquare.start();


            DataLine.Info LineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
                        final SourceDataLine sourceLineSine = (SourceDataLine)mixer.getLine(LineInfo);
                        sourceLineSine.open();


                        Thread sourceAudioThreadSine = new Thread(() -> {

                            sourceLineSine.start();

                            byte[] sineWave = new byte[BUFFER_SIZE];
                            double wavePos = 0;

                            while (true) {

                                for (int i = 0; i < BUFFER_SIZE; i++) {
                                    sineWave[i] = (byte)(Byte.MAX_VALUE * Math.sin((Math.PI * 120) / SAMPLE_RATE * wavePos++ / 2));
                                }

                                sourceLineSine.write(sineWave,0, BUFFER_SIZE);

                            }
                        });



                        final SourceDataLine sourceLineSquare = (SourceDataLine)mixer.getLine(LineInfo);
                        sourceLineSquare.open();

                        Thread sourceAudioThreadSquare = new Thread(() -> {

                            sourceLineSquare.start();

                            byte[] sineWave = new byte[BUFFER_SIZE];
                            double wavePos = 0;

                            while (true) {

                                for (int i = 0; i < BUFFER_SIZE; i++) {
                                    sineWave[i] = (byte)(Byte.MAX_VALUE * Math.sin((Math.PI * 60) / SAMPLE_RATE * wavePos++ / 2));
                                    if (sineWave[i] < 0) {
                                        sineWave[i] = Byte.MAX_VALUE;
                                    }
                                    else {
                                        sineWave[i] = -Byte.MAX_VALUE;
                                    }
                                }

                                sourceLineSquare.write(sineWave,0, BUFFER_SIZE);

                            }
                        });
