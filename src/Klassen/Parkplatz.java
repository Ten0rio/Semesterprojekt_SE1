package Klassen;

import java.security.PublicKey;

public class Parkplatz {

    public Parkplatz(){}

    private float gesamtParkGebühren;

    public float getGesamtParkGebühren() {
        return gesamtParkGebühren;
    }

    public void sumParkGebühren(float parkGebühren) {
        gesamtParkGebühren += parkGebühren;
    }

}
