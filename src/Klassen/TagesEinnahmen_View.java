package Klassen;

import java.time.LocalDate;

public class TagesEinnahmen_View extends Manager_View {

    public TagesEinnahmen_View(Parkhaus_Fachlogik parkhaus) {
        super(parkhaus);
    }

    @Override
    protected void berechneSumme(LocalDate aktuellesDatum, Parkschein last) {
        // wird die Bedingung erfüllt, ist nicht mehr der in "date" gespeicherte Tag und die Einnahmen müssen zurück gesetzt werden
        if( aktuellesDatum.isAfter(date)){
            date = aktuellesDatum;
            einnahmen = Double.parseDouble(last.getParkgebuehr()) / 100;
        } else {
            einnahmen += Double.parseDouble(last.getParkgebuehr()) /100;
        }
    }
}
