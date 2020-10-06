package Klassen;

import Interfaces.IObserver;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Manager_View implements IObserver {

    public Manager_View(Parkhaus_Fachlogik parkhaus) {
        this.parkhaus = parkhaus;
        date = LocalDate.of(0,1,1);
        update();
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

        Parkschein last = tickets.get(tickets.size()-1); // könnte zu fehler führen ##################################

        LocalDate aktuellesDatum = new Timestamp( Long.parseLong( last.getZeitAnfang() ) ).toLocalDateTime().toLocalDate();

        // Template Methode
        berechneSumme(aktuellesDatum,last);

    }

    protected abstract void berechneSumme(LocalDate aktuellesDatum, Parkschein last );

    //----------------------------------------------------------------------------------------------


}
