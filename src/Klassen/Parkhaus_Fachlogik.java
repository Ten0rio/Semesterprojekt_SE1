package Klassen;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.ArrayList;
import java.util.Iterator;

public class Parkhaus_Fachlogik {

    public Parkhaus_Fachlogik(){
        for( Parkplatz p  : slots){
            p = new Parkplatz();
        }
    }

    Parkplatz[] slots = new Parkplatz[10];
    private ArrayList<Parkschein> tickets = new ArrayList<>();
   // private ArrayList<Parkschein> aktuellImParkhaus = new ArrayList<>();

    private int anzahlBesucher = 0;
    private double summeEinnahmen = 0;


    public void IncAnzahlBesucher() {
        anzahlBesucher += 1;
    }

    public void sumEinnahmen(int price) {
        // in Euro
        summeEinnahmen += (price/100.0);
    }

    public double getSummeEinnahmen() {
        return summeEinnahmen;
    }

    public double getMeanEinnahmen() {
        return  (summeEinnahmen/anzahlBesucher);
    }

    public int getAnzahlBesucher() {
        return anzahlBesucher;
    }


    public void addParkschein(String[] params){
        tickets.add( new Parkschein(params));
    }

 /*   public void addImParkhaus(String[] params){
        aktuellImParkhaus.add(new Parkschein(params));
    }

    // pop Parkschein bei cmd == occupied um Autos die nicht ins Parkhaus eingefahren sind auch nicht zu speichern
    public void popImParkhaus(){
        aktuellImParkhaus.remove(aktuellImParkhaus.size()-1);
    }*/



    //---------------------------------------------------------------------------------------------
    // Json-Methoden um Charts zu erzeugen
    public JsonArray getJsonArrayParkgebuehren() {
        JsonArrayBuilder array = Json.createArrayBuilder();

        for(Parkschein p : tickets){
            array.add(p.getParkgebuehr());
        }

        return array.build();
    }

    //--------------------------

    public JsonArray getJsonArrayNumber() {
        JsonArrayBuilder array = Json.createArrayBuilder();

        for(Parkschein p : tickets){
            array.add(p.getAutoNr());
        }

        return array.build();
    }
}
