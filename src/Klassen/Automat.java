package Klassen;

import Interfaces.AutomatIF;

public class Automat implements AutomatIF {

    double gewinn;

    public Automat(){
        this.gewinn = 0;
    }

    @Override
    public Parkschein parkscheinbezahlen(Parkschein zubezahlen) {
        //Theoretisches Hochrechnen mit Zeitdifferenz modulo 3min, für alle 3min entstehen 50cent kosten

        //Ist bezahlt set true?

        //returned bezahltes Ticket
        return zubezahlen;
    }
}