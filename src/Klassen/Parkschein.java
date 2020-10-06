package Klassen;



public class Parkschein  {
	
	private String autoNr;
	private String zeitAnfang;
	private String parkzeit;
	private String parkgebuehr;
	private String ticketHash;
	private String farbe;
	private String parkplatz;
	private String clientCategorie;
	private String fahrzeugart;

	public Parkschein(String[] params ){
		autoNr = params[1];
		zeitAnfang = params[2];
		parkzeit = params[3];
		parkgebuehr = params[4];
		ticketHash = params[5];
		farbe = params[6];
		parkplatz = params[7];
		clientCategorie = params[8];
		fahrzeugart = params[9];
	}

	public String getAutoNr() {
		return autoNr;
	}

	public String getZeitAnfang() { return zeitAnfang; }

	public String getParkzeit() {
		return parkzeit;
	}

	public String getParkgebuehr() {
		return parkgebuehr;
	}

	public String getTicketHash() {
		return ticketHash;
	}

	public String getFarbe() { return farbe; }

	public String getParkplatz() {
		return parkplatz;
	}
}
