package Klassen;

import Interfaces.ParkhausIF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/ParkhausKlasse")
public class Parkhaus extends HttpServlet implements ParkhausIF{

    private String message;
    private int parkPlaetzeMax;
    private int parkplatzbelegt;
    private boolean schrankeOeffnet;

    public Parkhaus(){}

    public Parkhaus(int parkPlaetzeMax){
        this.parkPlaetzeMax=parkPlaetzeMax;
    }

    @Override
    public void init() throws ServletException {
        // Do required initialization
        this.message = "Hello World";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + message + "</h1>");
    }
    @Override
    public void einfahren() {
    }

    @Override
    public void ausfahren() {

    }

    @Override
    public int getParkPlaetzeMax() {
        return this.parkPlaetzeMax;
    }

    @Override
    public int getParkplatzbelegt() {
        return this.parkplatzbelegt;
    }

    // Ein und ausfahrende Autos mit overload der funktion setParkplatz
    @Override
    public void setPakplatzbelegt() {

    }
}
