package Servlets;

import Klassen.Auto;
import Klassen.Parkhaus_Fachlogik;

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
            parkhaus.addImParkhaus(params);
        }

        if ("leave".equals(event)) {

            parkhaus.IncAnzahlBesucher();
            parkhaus.addParkschein(params);

            String priceString = params[4];
            if (!"_".equals(priceString)) {
                int price = Integer.parseInt(priceString);
                parkhaus.sumEinnahmen(price);
            }


            ArrayList<Auto> allcars = this.autos();
            allcars.add(new Auto(params[1], params[7], params[3]));
            this.getApplication().setAttribute("autos", allcars);

            getApplication().setAttribute("Parkhaus", parkhaus);
        }

        if ("occupied".equals(event)) {
            parkhaus.popImParkhaus();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = request.getParameter("cmd");

        Parkhaus_Fachlogik parkhaus = getParkhaus_Fachlogik();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");



        if ("cmd".equals(command) && "sum".equals(param)) {
            double summe = parkhaus.getSummeEinnahmen();
            out.println(String.format("%1.2f",summe));
            System.out.println("sum = " +String.format("%1.2f",summe));
        }

        if ("cmd".equals(command) && "avg".equals(param)) {
            double avg = parkhaus.getMeanEinnahmen();
            out.println(String.format("%1.2f",avg));
            System.out.println("avg = " + String.format("%1.2f",avg));
        }

        if ("cmd".equals(command) && "avgtimespent".equals(param)) {
            double avg = parkhaus.getMeanEinnahmen();
            out.println(String.format("%1.2f",avg));
            System.out.println("avg = " + String.format("%1.2f",avg));
        }

        if ("cmd".equals(command) && "chart".equals(param)) {
            response.setContentType("text/html");
            out.println(this.JsonChart());
        }

        if ("cmd".equals(command) && "ParkdauerAnParkplatz".equals(param)) {
            ArrayList<Auto> listeAllerAutos = this.autos();
            double parkzeit = (Double)listeAllerAutos.stream().filter((a) -> {
                return a.getParkplatz().equals("1");
            }).map((a) -> {
                return Double.parseDouble(a.getParkzeit());
            }).reduce(0.0D, (x, y) -> {
                return x + y;
            });

            out.println("Die gesamte Parkzeit auf Parkplatz 1 betraegt: " + parkzeit / 1000.0D + "s");
        }

        System.out.println(request.getQueryString());
    }

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
        JsonObject chart = Json.createObjectBuilder().add("data", Json.createArrayBuilder().add(Json.createObjectBuilder().add("x", parkhaus.getJsonArrayNumber()).add("y", parkhaus.getJsonArrayParkgebuehren()).add("type", "bar"))).build();
        return chart.toString();
    }






    private ArrayList<Auto> autos() {
        ServletContext application = this.getApplication();
        ArrayList<Auto> autos = (ArrayList)application.getAttribute("autos");
        if (autos == null) {
            autos = new ArrayList();
        }

        return autos;
    }






    private Parkhaus_Fachlogik getParkhaus_Fachlogik() {
        ServletContext application = getApplication();
        Parkhaus_Fachlogik parkhaus = (Parkhaus_Fachlogik) application.getAttribute("Parkhaus");
        if (parkhaus == null) {
            parkhaus = new Parkhaus_Fachlogik();
        }

        return parkhaus;
    }
}