package Klassen;

import Interfaces.IObserver;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Manager_View implements IObserver {

    public Manager_View(Parkhaus_Fachlogik parkhaus) {
        this.parkhaus = parkhaus;
    }

    Parkhaus_Fachlogik parkhaus;
    private double tagesEinnahmen;
    private double wochenEinnahmen;

    public double getTagesEinnahmen() {
        return tagesEinnahmen;
    }

    public double getWochenEinnahmen() {
        return wochenEinnahmen;
    }

    @Override
    public void update() {
        tagesEinnahmen = parkhaus.getSummeEinnahmen();
    }

   public String showManagerView() {
        System.out.println("Tageseinnahmen: "+ tagesEinnahmen);
        JsonArray values = Json.createArrayBuilder()
                        .add(Json.createArrayBuilder()
                                .add("TagesEinnahmen")
                                .add("WochenEinnahmen")
                        )
                        .add(Json.createArrayBuilder()
                                .add(getTagesEinnahmen())
                                .add(getWochenEinnahmen())
                        ).build();
        //nicht fertig
        return values.toString();

    }

}
