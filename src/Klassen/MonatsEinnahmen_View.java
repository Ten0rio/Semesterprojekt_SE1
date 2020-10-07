package Klassen;

import java.time.LocalDate;

public class MonatsEinnahmen_View extends Manager_View {

    public MonatsEinnahmen_View(Parkhaus_Fachlogik parkhaus) {
        super(parkhaus);
    }

    @Override
    protected boolean datePassed(LocalDate aktuellesDatum ) {
        return aktuellesDatum.minusMonths(1).isAfter(date);
    }
}


