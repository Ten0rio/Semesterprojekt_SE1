package Klassen;



public class Parkschein  {
	
	int autoNr;
	private String zeitAnfang;
	private String parkzeit;
	int parkgebuehr ;
	private String ticketHash;
	private String farbe;
	private String parkplatz;

	public Parkschein(String[] params ){
		autoNr = Integer.parseInt(params[1]);
		zeitAnfang = params[2];
		parkzeit = params[3];
		parkgebuehr = Integer.parseInt(params[4]);
		ticketHash = params[5];
		farbe = params[6];
		parkplatz = params[7];
	}

	public int getAutoNr() {
		return autoNr;
	}

	public String getZeitAnfang() {
		return zeitAnfang;
	}

	public String getParkzeit() {
		return parkzeit;
	}

	public int getParkgebuehr() {
		return parkgebuehr;
	}

	public String getTicketHash() {
		return ticketHash;
	}

	public String getFarbe() {
		return farbe;
	}

	public String getParkplatz() {
		return parkplatz;
	}
}
