package Klassen;

import Interfaces.ParkscheinIF;

public class Parkschein implements ParkscheinIF {
	
	boolean bezahlt;
	long Timestamp = System.currentTimeMillis();


	@Override
	public boolean istBezahlt() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
