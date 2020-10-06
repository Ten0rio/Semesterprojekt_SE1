package Servlets;

import Klassen.Auto;
import Klassen.Manager_View;
import Klassen.Parkhaus_Fachlogik;
import Klassen.Parkschein;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/DemoServlet"})
public class DemoServlet extends HttpServlet {

    public DemoServlet() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println(body);
        String[] params = body.split(",");
        String event = params[0];

        Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();

        if ("enter".equals(event)) {

        }

        if ("leave".equals(event)) {

            parkhaus.addParkschein(params);


            //---------------------VIEWS-------------------------------
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            Manager_View manager_view = getManager_View();
            out.println(manager_view.showManagerView());
            //---------------------------------------------------------



            getApplication().setAttribute("Parkhaus", parkhaus);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = request.getParameter("cmd");

        Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");



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
            Manager_View manager_view = getManager_View();
            out.println(manager_view.showManagerView());
        }

        if ("cmd".equals(command) && "config".equals(param)) {
            // create Objects in Servlet Context
            getParkhaus_Fachlogik();
            getManager_View();
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


    private Manager_View getManager_View() {
        ServletContext application = getApplication();
        Manager_View manager_view = (Manager_View) application.getAttribute("Manager_View");
        if (manager_view == null) {
            Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();
            manager_view = new Manager_View(parkhaus);
            parkhaus.add(manager_view);
            getApplication().setAttribute("Manager_View", manager_view);
        }

        return manager_view;
    }
}