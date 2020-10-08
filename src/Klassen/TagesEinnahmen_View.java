package Klassen;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class TagesEinnahmen_View extends Manager_View {

    public TagesEinnahmen_View(Parkhaus_Fachlogik parkhaus) {
        super(parkhaus);
    }

    @Override
    protected boolean datePassed(LocalDate aktuellesDatum) {
        return aktuellesDatum.isAfter(date);
    }


}
