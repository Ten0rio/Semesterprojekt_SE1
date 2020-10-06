package Klassen;

import java.time.LocalDate;

public class WochenEinnahmen_View extends Manager_View {

    public WochenEinnahmen_View(Parkhaus_Fachlogik parkhaus) {
        super(parkhaus);
    }

    @Override
    protected void berechneSumme(LocalDate aktuellesDatum, Parkschein last) {
        if( aktuellesDatum.minusWeeks(1).isAfter(date)){
            date = aktuellesDatum;
            einnahmen = Double.parseDouble(last.getParkgebuehr()) / 100;
        }else {
            einnahmen += Double.parseDouble(last.getParkgebuehr()) /100;
        }
    }
}
