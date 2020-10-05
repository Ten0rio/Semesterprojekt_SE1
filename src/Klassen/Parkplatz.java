package Klassen;

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
