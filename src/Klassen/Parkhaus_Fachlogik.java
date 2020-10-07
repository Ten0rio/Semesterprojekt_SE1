package Klassen;

import Interfaces.IObservable;
import Interfaces.IObserver;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.ArrayList;

public class Parkhaus_Fachlogik implements IObservable {

    public Parkhaus_Fachlogik(){}


    private ArrayList<Parkschein> tickets = new ArrayList<>();
    ArrayList<IObserver> views = new ArrayList<>();
   // private ArrayList<Parkschein> aktuellImParkhaus = new ArrayList<>();

    private int anzahlBesucher = 0;
    private double summeEinnahmen = 0;


    public void IncAnzahlBesucher() {
        anzahlBesucher += 1;
    }

    public void DecAnzahlBesucher() {anzahlBesucher -= 1;}

    public void sumEinnahmen(int price) {
        // in Euro
        summeEinnahmen += (price/100.0);
    }

    public void updateSumEinnahmen(int price) {
        // in Euro
        summeEinnahmen -= (price/100.0);
        if (summeEinnahmen < 0){
            summeEinnahmen = 0;
        }
    }

    public double getSummeEinnahmen() {
        return summeEinnahmen;
    }

    public double getMeanEinnahmen() {
        if (Double.isNaN(summeEinnahmen/anzahlBesucher) || Double.isInfinite(summeEinnahmen/anzahlBesucher)){
            return 0;
        }
        return  (summeEinnahmen/anzahlBesucher);
    }

    public int getAnzahlBesucher() {
        return anzahlBesucher;
    }

    public ArrayList<Parkschein> getTickets() {
        return tickets;
    }

    public void addParkschein(String[] params){
        tickets.add( new Parkschein(params));
        IncAnzahlBesucher();
        sumEinnahmen(Integer.parseInt(params[4]));
        notifyObservers();
    }

    public void removeParkschein(String[] params) throws IndexOutOfBoundsException {
        try {
            Parkschein last = tickets.remove(tickets.size() - 1);
            tickets.get(tickets.size()-1).setParkzeitVorgänger(last.getParkzeitVorgänger());
            DecAnzahlBesucher();
            updateSumEinnahmen(Integer.parseInt(params[4]));
            notifyObservers();
        } catch( IndexOutOfBoundsException e){}
    }

 /*   public void addImParkhaus(String[] params){
        aktuellImParkhaus.add(new Parkschein(params));
    }

    // pop Parkschein bei cmd == occupied um Autos die nicht ins Parkhaus eingefahren sind auch nicht zu speichern
    public void popImParkhaus(){
        aktuellImParkhaus.remove(aktuellImParkhaus.size()-1);
    }*/



    //---------------------------------------------------------------------------------------------
    // Json-HilfsMethoden um Charts zu erzeugen

    public JsonArray getJsonArrayParkgebuehren() {
        JsonArrayBuilder array = Json.createArrayBuilder();

        for(Parkschein p : tickets){
            array.add(Integer.parseInt(p.getParkgebuehr())/100.0);
        }

        return array.build();
    }

    //--------------------------

    public JsonArray getJsonArrayNumber() {
        JsonArrayBuilder array = Json.createArrayBuilder();

        for(Parkschein p : tickets){
            array.add("Nr."+p.getAutoNr());
        }

        return array.build();
    }


    //---------------------------------------------------------------------------------------------
    // Obeservable Methods:

    @Override
    public void add(IObserver x) {
        views.add(x);
    }

    @Override
    public void remove(IObserver x) {
        views.remove(x);
    }

    @Override
    public void notifyObservers() {
        for( IObserver v : views){
            v.update();
        }
    }
}
