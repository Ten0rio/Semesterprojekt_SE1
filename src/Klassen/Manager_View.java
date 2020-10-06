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
                                .add(String.format("%1.2f",getTagesEinnahmen()))
                        )
                        .add(Json.createArrayBuilder()
                                .add(getWochenEinnahmen())
                        ).build();

        JsonObject data = Json.createObjectBuilder().add( "data",Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type","table")
                        .add("header",Json.createObjectBuilder()
                                .add("values",Json.createArrayBuilder()
                                        .add(Json.createArrayBuilder().add("<b>TagesEinnahmen</b>"))
                                        .add(Json.createArrayBuilder().add("<b>WochenEinnahmen</b>"))

                                )
                                .add("align","center")
                                .add("line", Json.createObjectBuilder().add("width",1).add("color","black"))
                                .add("fill", Json.createObjectBuilder().add("color","gray"))
                                .add("font", Json.createObjectBuilder().add("family","Arial").add("size",12).add("color","white"))

                        ).add("cells", Json.createObjectBuilder()
                                .add("values", values)
                                .add("align","center")
                                .add("line",Json.createObjectBuilder().add("color","black").add("width",1))
                                .add("font",Json.createObjectBuilder().add("family","Arial").add("size",11).add("color", Json.createArrayBuilder().add("black")))
                        )

                )).build();

        return data.toString();

    }

}
