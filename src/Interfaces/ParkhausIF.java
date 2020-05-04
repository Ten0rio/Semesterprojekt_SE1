package Interfaces;
/*

 Schranke = true/false
 nur wenn Klassen.Parkschein gezogen wurde oder bezahlt wurde
 boolean Ein/AusfahrtGewähren();
 min/max
 boolean ParkhausHatPlaetze();
 test auf neues Klassen.Parkschein
 ticket ParkscheinZiehen();

 */

public interface ParkhausIF {

    void einfahren();
    void ausfahren();

    int getParkPlaetzeMax();
    int getParkplatzbelegt();
    void setPakplatzbelegt();

}
