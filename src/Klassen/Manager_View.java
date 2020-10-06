package Klassen;

import Interfaces.IObserver;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Manager_View implements IObserver {

    public Manager_View(Parkhaus_Fachlogik parkhaus) {
        this.parkhaus = parkhaus;
        date = LocalDate.of(0,1,1);
    }

    protected Parkhaus_Fachlogik parkhaus;
    protected LocalDate date;
    protected double einnahmen;

    public double getEinnahmen() {
        return einnahmen;
    }


    //----------------------------------------------------------------------------------------------

    @Override
    final public void update() {
        ArrayList<Parkschein> tickets = parkhaus.getTickets();
        // letzter hinzugefügter Parkschein => der für update() relevante Parkschein
        Parkschein last = tickets.get(tickets.size()-1);
        // Erstelldatum des Parkscheins
        LocalDate aktuellesDatum = new Timestamp( Long.parseLong( last.getZeitAnfang() ) ).toLocalDateTime().toLocalDate();

        // Aufruf Template Methode
        berechneSumme(aktuellesDatum,last);

    }

    // Template Methode
    protected abstract void berechneSumme(LocalDate aktuellesDatum, Parkschein last );

    //----------------------------------------------------------------------------------------------


}
