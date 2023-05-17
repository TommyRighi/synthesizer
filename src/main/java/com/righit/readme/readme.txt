Distorsioni da implementareà

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