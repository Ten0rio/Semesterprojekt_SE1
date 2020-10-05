package Klassen;

import Interfaces.IObserver;

import javax.json.Json;
import javax.json.JsonObject;

public class Manager_View implements IObserver {

    public Manager_View(Parkhaus_Fachlogik parkhaus){
        this.parkhaus = parkhaus;
    }

    Parkhaus_Fachlogik parkhaus;
    double tagesEinnahmen;
    double wochenEinnahmen;



    @Override
    public void update() {
        tagesEinnahmen = parkhaus.getSummeEinnahmen();
    }

    public String showManagerView(){
        JsonObject chart = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("x", parkhaus.getJsonArrayNumber())
                                .add("y", parkhaus.getJsonArrayParkgebuehren())
                                .add("type", "bar")
                                .add("name","Gebuehren in Euro")
                        )
                )
                .build();
        return chart.toString();
    }
}
