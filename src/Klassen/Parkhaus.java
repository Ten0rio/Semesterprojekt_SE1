package Klassen;

import Interfaces.ParkhausIF;

public class Parkhaus implements ParkhausIF {

    int parkPlaetzeMax;
    int parkplatzbelegt;
    boolean schrankeOeffnet;

    @Override
    public void einfahren() {
    }

    @Override
    public void ausfahren() {

    }

    @Override
    public int getParkPlaetzeMax() {
        return 0;
    }

    @Override
    public int getParkplatzbelegt() {
        return 0;
    }
}
