package Klassen;

import Interfaces.IObserver;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public abstract class Manager_View implements IObserver {

    public Manager_View(Parkhaus_Fachlogik parkhaus) {
        this.parkhaus = parkhaus;
        vorherigeEinnahmen = new Stack<>();
        date = LocalDate.of(0,1,1);
    }

    protected Parkhaus_Fachlogik parkhaus;
    protected LocalDate date;
    protected double einnahmen;
    Stack<Double> vorherigeEinnahmen;

    public double getEinnahmen() {
        return einnahmen;
    }


    //----------------------------------------------------------------------------------------------

    @Override
    final public void update() {
        ArrayList<Parkschein> tickets = parkhaus.getTickets();

        try{
            // letzter hinzugefügter Parkschein => der für update() relevante Parkschein
            Parkschein last = tickets.get(tickets.size()-1);


            // Erstelldatum des Parkscheins
            LocalDate aktuellesDatum = new Timestamp( Long.parseLong( last.getZeitAnfang() ) ).toLocalDateTime().toLocalDate();

            double parkzeitVorgaenger = Double.parseDouble(last.getParkgebuehrVorgänger()) / 100;
            if( parkzeitVorgaenger > 0 && aktuellesDatum.isBefore(date)){
                einnahmen = vorherigeEinnahmen.pop();
                date = aktuellesDatum;
                return;
            }
            // Aufruf Template Methode
            // wird die Bedingung erfüllt, ist der in "date" gespeicherte Tag länger als eine/n Tag,Woche,Monat her und die Einnahmen müssen zurück gesetzt werden
            if( datePassed(aktuellesDatum) ){
                vorherigeEinnahmen.push(einnahmen);
                date = aktuellesDatum;
                einnahmen = Double.parseDouble(last.getParkgebuehr()) / 100;
            }else {
                if( parkzeitVorgaenger > 0 ){
                    einnahmen -= parkzeitVorgaenger;
                } else {
                    einnahmen += Double.parseDouble(last.getParkgebuehr()) /100;
                }

            }


        } catch ( IndexOutOfBoundsException e){ einnahmen = 0; }
    }

    // Template Methode
    protected abstract boolean datePassed(LocalDate aktuellesDatum );

    //----------------------------------------------------------------------------------------------


}
