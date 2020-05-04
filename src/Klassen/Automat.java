package Klassen;

import Interfaces.AutomatIF;

public class Automat implements AutomatIF {

    double gewinn;

    public Automat(){
        this.gewinn = 0;
    };

    @Override
    public Parkschein parkscheinbezahlen(Parkschein zubezahlen) {
        //Theoretisches Hochrechnen ohne Zeitdifferenz
        this.gewinn =+ zubezahlen.Timestamp * 3;
        //Ist bezahlt set true?
        zubezahlen.istBezahlt();
        //returned bezahltes Ticket
        return zubezahlen;
    }
}
