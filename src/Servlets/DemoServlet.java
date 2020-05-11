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
        if ("leave".equals(event)){
            Float sum = getPersistentSum();
            String priceString = params[4];
            if(!"_".equals(priceString)){
                float price = Float.parseFloat(priceString);
                sum += price;
                getApplication().setAttribute("sum",sum);
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(sum);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = requestParamString[1];

        if("cmd".equals(command) && "sum".equals(param)){
            Float sum = getPersistentSum();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(sum/100);

            System.out.println("sum = " + sum );
        }
        System.out.println(request.getQueryString());

    }

    private static String getBody(HttpServletRequest request) throws IOException{
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
        }finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
        }

        private ServletContext getApplication(){
        return getServletConfig().getServletContext();
        }

        private Float getPersistentSum(){
        Float sum;
        ServletContext application = getApplication();
        sum = (Float)application.getAttribute("sum");
        if(sum == null) sum = 0.0f;
        return sum;
        }

}
