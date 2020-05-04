package Klassen;

import Interfaces.ParkhausIF;

public class Parkhaus implements ParkhausIF {

    private int parkPlaetzeMax;
    private int parkplatzbelegt;
    private boolean schrankeOeffnet;

    public Parkhaus(int parkPlaetzeMax){
        this.parkPlaetzeMax=parkPlaetzeMax;
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
