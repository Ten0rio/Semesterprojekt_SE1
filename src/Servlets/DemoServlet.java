package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/Parkhaus")
public class DemoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println(body);
        String[] params = body.split(",");
        String event = params[0];
        if ("enter".equals(event)){
            Integer total = getPersistentTotalCars();
            total += 1;
            getApplication().setAttribute("total",total);
            System.out.println(total);
        }
        if ("leave".equals(event)) {
            Float sum = getPersistentSum();
            String priceString = params[4];
            String timeString = params[3];


            if(!"_".equals(priceString)) {
                float time = Float.parseFloat(timeString);
                if(time < 15000){
                    //Es wird keine Gebühr fällig
                } else {
                    if (!"_".equals(priceString)) {
                        float price = Float.parseFloat(priceString);
                        sum += price;
                        getApplication().setAttribute("sum", sum);
                    }
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println(sum);
                }
            }



            int total = getPersistentTotalCars();
            getApplication().setAttribute("avg",sum/total);

            Float avgtime = getPersistentAvgTimeSpent();
            //timeString = params[3]; // redundant
            if (!"_".equals(timeString)){
                float time = Float.parseFloat(timeString);
                avgtime += time;
                getApplication().setAttribute("avgtime", avgtime/total);
            }



        }


        }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = requestParamString[1];

        if ("cmd".equals(command) && "sum".equals(param)) {
            Float sum = getPersistentSum();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("sum =" + sum / 100);

            System.out.println("sum = " + sum);
            System.out.println(getApplication().getAttribute("preis")+ "<-- Preis");
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
            out.println(avgtime/ 100);

            System.out.println("avgtime = " + avgtime);
        }

        if ("cmd".equals(command)&& "config&name".equals(param) ){
            System.out.println("testststtstslgeabwäd");
            response.setContentType("text/html");
            PrintWriter out =  response.getWriter();
            out.println("20,8,24,100,100");
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

    private int getPersistentTotalCars() {
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

}
