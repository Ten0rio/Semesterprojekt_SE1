package Klassen;

import Interfaces.ParkscheinIF;

public class Parkschein implements ParkscheinIF {
	
	boolean bezahlt;
	long Timestamp = System.currentTimeMillis();


	@Override
	public boolean istBezahlt() {
		return bezahlt;
	}


	@Override
	public void setBezahlt(boolean bezahlt) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
