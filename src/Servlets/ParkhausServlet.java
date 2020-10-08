package Servlets;

import Interfaces.ICommand;
import Klassen.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ParkhausServlet"})
public class ParkhausServlet extends HttpServlet {

    public ParkhausServlet() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println(body);
        String[] params = body.split(",");
        String event = params[0];

        Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
        CommandStack commands = getCommandStack();

        if ("enter".equals(event)) {

        }

        if ("leave".equals(event)) {

            //--------------ICommand Entkapselung von erstellen und speichern----------------
            Parkscheinersteller ticket = new Parkscheinersteller();
            ICommand ticketplus = new ParkscheinerstellenCommand(parkhaus,params);

            ticket.saveCommand(ticketplus);
            ticket.activate();
            commands.addCommand(ticketplus);

            //parkhaus.addParkschein(params);

            getApplication().setAttribute("Parkhaus", parkhaus);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = request.getParameter("cmd");

        Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
        CommandStack commands = getCommandStack();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

//--------------Entferne des letzten Commandos(letztes hinzugefügtes Auto)----------------
        if ("cmd".equals(command) && "UndoLastCar".equals(param)){
            commands.removeCommand().undo();
        }

        if ("cmd".equals(command) && "SummeEinnahmen".equals(param)) {
            double summe = parkhaus.getSummeEinnahmen();
            out.println(String.format("%1.2f",summe));
            System.out.println("sum = " +String.format("%1.2f",summe));
        }

        if ("cmd".equals(command) && "AnzahlBesucher".equals(param)) {
            int anzahl = parkhaus.getAnzahlBesucher();
            out.println(anzahl);
            System.out.println("anzahl = "+ anzahl);
        }

        if ("cmd".equals(command) && "MeanEinnahmen".equals(param)) {
            double mean = parkhaus.getMeanEinnahmen();
            out.println(String.format("%1.2f",mean));
            System.out.println("mean = " + String.format("%1.2f",mean));
        }

        if ("cmd".equals(command) && "KostenChart".equals(param)) {
            out.println(this.JsonChart());
        }

        if ("cmd".equals(command) && "Manager_View".equals(param)) {
            out.println(showManagerView());
        }

        if ("cmd".equals(command) && "config".equals(param)) {
            // create Objects in Servlet Context
            getParkhaus_Fachlogik();
            getTagesEinnahmen_View();
            getWochenEinnahmen_View();
            getMonatsEinnahmen_View();
        }



        if ("cmd".equals(command) && "ParkdauerAnParkplatz".equals(param)) {
            ArrayList<Parkschein> tickets = parkhaus.getTickets();
            double parkzeit = (Double)tickets.stream().filter((a) -> {
                return a.getParkplatz().equals("1");
            }).map((a) -> {
                return Double.parseDouble(a.getParkzeit());
            }).reduce(0.0D, (x, y) -> {
                return x + y;
            });

            out.println("Die gesamte Parkzeit auf Parkplatz 1 betraegt: " + String.format("%1.2f",parkzeit / 1000.0) + "s");
        }

        System.out.println(request.getQueryString());
    }


    //---------------------------------------------------------------------------------------------------------

    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charbuffer = new char[128];
                boolean var5 = true;

                int bytesread;
                while((bytesread = bufferedReader.read(charbuffer)) > 0) {
                    stringBuilder.append(charbuffer, 0, bytesread);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

        }

        return stringBuilder.toString();
    }

    //-----------------------------------------------------------------------

    private ServletContext getApplication() {
        return this.getServletConfig().getServletContext();
    }


    //------------------------------------------------------------------------




    private String JsonChart() {
        Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
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

    private String showManagerView() {

        TagesEinnahmen_View tag = getTagesEinnahmen_View();
        WochenEinnahmen_View woche = getWochenEinnahmen_View();
        MonatsEinnahmen_View monat = getMonatsEinnahmen_View();

        JsonArray values = Json.createArrayBuilder()
                .add(Json.createArrayBuilder()
                        .add(String.format("%1.2f",tag.getEinnahmen()))
                )
                .add(Json.createArrayBuilder()
                        .add(String.format("%1.2f",woche.getEinnahmen()))
                )
                .add(Json.createArrayBuilder()
                        .add(String.format("%1.2f",monat.getEinnahmen()))
                ).build();

        JsonObject data = Json.createObjectBuilder().add( "data",Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type","table")
                        .add("header",Json.createObjectBuilder()
                                .add("values",Json.createArrayBuilder()
                                        .add(Json.createArrayBuilder().add("TagesEinnahmen"))
                                        .add(Json.createArrayBuilder().add("WochenEinnahmen"))
                                        .add(Json.createArrayBuilder().add("MonatsEinnahmen"))

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







    //--------------------------------------------------------------------------------------------


    private Parkhaus_Fachlogik getParkhaus_Fachlogik() {
        ServletContext application = getApplication();
        Parkhaus_Fachlogik parkhaus = (Parkhaus_Fachlogik) application.getAttribute("Parkhaus");
        if (parkhaus == null) {
            parkhaus = new Parkhaus_Fachlogik();
            getApplication().setAttribute("Parkhaus", parkhaus);
        }

        return parkhaus;
    }


    private TagesEinnahmen_View getTagesEinnahmen_View() {
        ServletContext application = getApplication();
        TagesEinnahmen_View tagesEinnahmen = (TagesEinnahmen_View) application.getAttribute("TagesEinnahmen");
        if (tagesEinnahmen == null) {
            Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
            tagesEinnahmen = new TagesEinnahmen_View(parkhaus);
            parkhaus.add(tagesEinnahmen);
            getApplication().setAttribute("TagesEinnahmen", tagesEinnahmen);
        }

        return tagesEinnahmen;
    }

    private WochenEinnahmen_View getWochenEinnahmen_View() {
        ServletContext application = getApplication();
        WochenEinnahmen_View wochenEinnahmen = (WochenEinnahmen_View) application.getAttribute("WochenEinnahmen");
        if (wochenEinnahmen == null) {
            Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
            wochenEinnahmen = new WochenEinnahmen_View(parkhaus);
            parkhaus.add(wochenEinnahmen);
            getApplication().setAttribute("WochenEinnahmen", wochenEinnahmen);
        }

        return wochenEinnahmen;
    }

    private MonatsEinnahmen_View getMonatsEinnahmen_View() {
        ServletContext application = getApplication();
        MonatsEinnahmen_View monatsEinnahmen = (MonatsEinnahmen_View) application.getAttribute("MonatsEinnahmen");
        if (monatsEinnahmen == null) {
            Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
            monatsEinnahmen = new MonatsEinnahmen_View(parkhaus);
            parkhaus.add(monatsEinnahmen);
            getApplication().setAttribute("MonatsEinnahmen", monatsEinnahmen);
        }

        return monatsEinnahmen;
    }

    private CommandStack getCommandStack() {
        ServletContext application = getApplication();
        CommandStack commands = (CommandStack) application.getAttribute("Commands");
        if (commands == null) {
            commands = new CommandStack();
            getApplication().setAttribute("Commands", commands);
        }

        return commands;
    }
}