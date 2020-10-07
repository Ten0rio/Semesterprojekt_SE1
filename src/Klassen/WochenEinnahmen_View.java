package Klassen;

import java.time.LocalDate;

public class WochenEinnahmen_View extends Manager_View {

    public WochenEinnahmen_View(Parkhaus_Fachlogik parkhaus) {
        super(parkhaus);
    }

    @Override
    protected boolean datePassed(LocalDate aktuellesDatum) {
        return aktuellesDatum.minusWeeks(1).isAfter(date);
    }

}
