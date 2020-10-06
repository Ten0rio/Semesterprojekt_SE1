package Klassen;

import Interfaces.IObserver;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public class Manager_View implements IObserver {

    public Manager_View(Parkhaus_Fachlogik parkhaus) {
        this.parkhaus = parkhaus;
        tag = woche = LocalDate.of(0,1,1);
        update();
    }

    private Parkhaus_Fachlogik parkhaus;
    private LocalDate tag;
    private LocalDate woche;
    private double tagesEinnahmen;
    private double wochenEinnahmen;

    public double getTagesEinnahmen() {
        return tagesEinnahmen;
    }

    public double getWochenEinnahmen() {
        return wochenEinnahmen;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void update() {
        ArrayList<Parkschein> tickets = parkhaus.getTickets();
        Parkschein last = tickets.get(tickets.size()-1);
        LocalDate aktuellesDatum = new Timestamp( Long.parseLong( last.getZeitAnfang() ) ).toLocalDateTime().toLocalDate();

        if( aktuellesDatum.isAfter(tag)){
            tag = aktuellesDatum;
            tagesEinnahmen = Double.parseDouble(last.getParkgebuehr()) / 100;
        }else {
            tagesEinnahmen += Double.parseDouble(last.getParkgebuehr()) /100;
        }

        if( aktuellesDatum.minusWeeks(1).isAfter(woche)){
            woche = aktuellesDatum;
            wochenEinnahmen = Double.parseDouble(last.getParkgebuehr()) / 100;
        }else {
            wochenEinnahmen += Double.parseDouble(last.getParkgebuehr()) /100;
        }


    }

    //----------------------------------------------------------------------------------------------

   public String showManagerView() {

        JsonArray values = Json.createArrayBuilder()
                        .add(Json.createArrayBuilder()
                                .add(String.format("%1.2f",getTagesEinnahmen()))
                        )
                        .add(Json.createArrayBuilder()
                                .add(String.format("%1.2f",getWochenEinnahmen()))
                        ).build();

        JsonObject data = Json.createObjectBuilder().add( "data",Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type","table")
                        .add("header",Json.createObjectBuilder()
                                .add("values",Json.createArrayBuilder()
                                        .add(Json.createArrayBuilder().add("TagesEinnahmen"))
                                        .add(Json.createArrayBuilder().add("WochenEinnahmen"))

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
