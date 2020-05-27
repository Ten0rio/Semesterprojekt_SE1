package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import Klassen.Auto;

@WebServlet("/ParkhausServlet")
public class ParkhausServlet extends HttpServlet {

    //ArrayList<Auto> alleAutosList = new ArrayList<Auto>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println(body);
        String[] params = body.split(",");
        String event = params[0];
        if ("enter".equals(event)) {
            Integer total = getPersistentTotalCars();
            total += 1;
            getApplication().setAttribute("total", total);

        }
        if ("leave".equals(event)) {
            Float sum = getPersistentSum(); // Summe sum = getsum();
            String priceString = params[4];
            if (!"_".equals(priceString)) {
                float price = Float.parseFloat(priceString);
                sum += price;
                getApplication().setAttribute("sum", sum);
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(sum);

            int total = getPersistentTotalCars();
            getApplication().setAttribute("avg", sum / total);

            Float avgtime = getPersistentAvgTimeSpent();
            String timeString = params[3];
            if (!"_".equals(timeString)) {
                float time = Float.parseFloat(timeString);
                avgtime += time;
                getApplication().setAttribute("avgtime", avgtime / total);
            }

            //Auto kek = new Auto(params[1],params[7],params[3]);
            //alleAutosList.add(kek);
            ArrayList<Auto> allcars = autos();
            allcars.add(new Auto(params[1],params[7],params[3]));
            //System.out.println(allcars.size());
            getApplication().setAttribute("autos", allcars);

        }


       /* response.setContentType("text,html");
        ServletOutputStream out = response.getOutputStream();

        out.write(params[0]+","+params[1]+","+params[2]+","
                +params[3]+","+42+","+params[5]+","
                +params[6]+","+params[7]);



        String x = (params[0]+","+params[1]+","+params[2]+","
                +params[3]+","+42+","+params[5]+","
                +params[6]+","+params[7]);
        out.print(x);
        */


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        //String param = requestParamString[1];
        String param = request.getParameter("cmd");

        if ("cmd".equals(command) && "sum".equals(param)) {
            Float sum = getPersistentSum();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(sum / 100);

            System.out.println("sum = " + sum);
        }
        if ("cmd".equals(command) && "avg".equals(param)) {
            Float avg = getPersistentAvg();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(avg / 100);

            System.out.println("avg = " + avg);
        }

        if ("cmd".equals(command) && "avgtimespent".equals(param)) {
            Float avgtime = getPersistentAvg();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(avgtime / 100);

            System.out.println("avgtime = " + avgtime);
        }

        if("cmd".equals(command) && "chart".equals(param)){


            ArrayList<Auto> listeAllerAutos = autos();
            String jsonString = "{" + " \"data\": [" + " {" + " \"x\": [";


            Auto car;
            for(int i= 0; i < listeAllerAutos.size()-1; i++){
                car = listeAllerAutos.get(i);
                jsonString += "\""+  car.getNumber()  + "\","; //die nummern der Autos werden in Strings fuer die x-werte transformiert
            }
            car = listeAllerAutos.get(listeAllerAutos.size()-1);
            jsonString += "\""+ car.getNumber() + "\"" + " ]," +  " \"y\": [" ; //Beim letzten element das Komma weglassen
            //------------------------



            for(int i= 0; i < listeAllerAutos.size()-1; i++){
                jsonString +=   listeAllerAutos.get(i).getParkzeit()  + ","; //die Parkzeiten der Autos werden in Strings fuer die y-werte transformiert
            }
            jsonString +=  listeAllerAutos.get(listeAllerAutos.size()-1).getParkzeit() +  " ]," + " \"type\": \"bar\"" + " }" + " ]" + "}"; //Beim letzten element das Komma weglassen

            System.out.println(jsonString);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(jsonString);

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
                int bytesread = -1;
                while ((bytesread = bufferedReader.read(charbuffer)) > 0) {
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

    private ServletContext getApplication() {
        return getServletConfig().getServletContext();
    }

    private Float getPersistentSum() {
        Float sum;
        ServletContext application = getApplication();
        sum = (Float) application.getAttribute("sum");
        if (sum == null) sum = 0.0f;
        return sum;
    }

    private Float getPersistentAvg() {
        Float avg;
        ServletContext application = getApplication();
        avg = (Float) application.getAttribute("avg");
        if (avg == null) avg = 0.0f;
        return avg;
    }

    private Integer getPersistentTotalCars() {
        Integer total;
        ServletContext application = getApplication();
        total = (Integer) application.getAttribute("total");
        if (total == null) total = 0;
        return total;
    }

    private Float getPersistentAvgTimeSpent() {
        Float avgtime;
        ServletContext application = getApplication();
        avgtime = (Float) application.getAttribute("avgtime");
        if (avgtime == null) avgtime = 0.0f;
        return avgtime;
    }

    private ArrayList<Auto> autos() {
        ServletContext application = getApplication();
        ArrayList<Auto> autos = (ArrayList<Auto>) application.getAttribute("autos");
        if (autos == null) {
            autos = new ArrayList<Auto>() ;{
            }
        }
        return autos;
    }
}
