package Klassen;

public class Auto  {


    String number = "";
    String parkplatz = "";
    String parkzeit = "";

    public Auto(String number, String parkplatz, String parkzeit){
        this.number = number;
        this.parkplatz = parkplatz;
        this.parkzeit = parkzeit;
    }


    public String getNumber(){
        return number;
    }
    public String getParkzeit() {
        return parkzeit;
    }
    public String getParkplatz() {
        return parkplatz;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setParkplatz(String parkplatz) {
        this.parkplatz = parkplatz;
    }

    public void setParkzeit(String parkzeit) {
        this.parkzeit = parkzeit;
    }


}
