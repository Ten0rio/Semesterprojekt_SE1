package Klassen;

public class Parkplatz {

    public Parkplatz(){}

    private float gesamtParkGebühren;

    public float getGesamtParkGebühren() {
        return gesamtParkGebühren;
    }

    public void sumGesamtParkGebühren(float parkGebühren) {
        gesamtParkGebühren += parkGebühren;
    }
}
