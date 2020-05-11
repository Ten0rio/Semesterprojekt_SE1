package Servlets;

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float sum = 0;
        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = requestParamString[1];

        if("cmd".equals(command) && "sum".equals(param)){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(sum);

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

}
