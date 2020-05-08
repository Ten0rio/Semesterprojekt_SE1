package JUnit_Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Klassen.Parkschein;

class ParkscheinIFTest {
	
	Parkschein p1;


	@BeforeEach
	void setUp() {
		p1 = new Parkschein();
	}

	@Test
	void test_istBezahlt() {
		p1.setBezahlt(true);
		assertEquals(true,p1.istBezahlt());
	}

}
