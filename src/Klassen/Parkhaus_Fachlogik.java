package Klassen;

public class Parkhaus_Fachlogik {

    public Parkhaus_Fachlogik(){
        for( Parkplatz p  : slots){
            p = new Parkplatz();
        }
    }

    Parkplatz[] slots = new Parkplatz[10];
    private int aktuellImParkhaus;
    private int anzahlBesucher = 0;
    private double summeEinnahmen;


    public void IncAnzahlBesucher() {
        anzahlBesucher += 1;
    }

    public void sumEinnahmen(int price) {
        // in Euro
        summeEinnahmen += (price/100.0);
    }

    public double getSummeEinnahmen() {
        return summeEinnahmen;
    }

    public double getMeanEinnahmen() {
        return  (summeEinnahmen/anzahlBesucher);
    }

    public int getAnzahlBesucher() {
        return anzahlBesucher;
    }
}
