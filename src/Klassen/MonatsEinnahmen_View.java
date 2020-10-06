package Klassen;

import java.time.LocalDate;

public class MonatsEinnahmen_View extends Manager_View {

    public MonatsEinnahmen_View(Parkhaus_Fachlogik parkhaus) {
        super(parkhaus);
    }

    @Override
    protected void berechneSumme(LocalDate aktuellesDatum, Parkschein last) {
        // wird die Bedingung erfüllt, ist der in "date" gespeicherte Tag länger als einen Monat her und die Einnahmen müssen zurück gesetzt werden
        if( aktuellesDatum.minusMonths(1).isAfter(date)){
            date = aktuellesDatum;
            einnahmen = Double.parseDouble(last.getParkgebuehr()) / 100;
        }else {
            einnahmen += Double.parseDouble(last.getParkgebuehr()) /100;
        }
    }
}
