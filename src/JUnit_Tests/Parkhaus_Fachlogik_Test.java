package JUnit_Tests;

import Klassen.Parkhaus_Fachlogik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Parkhaus_Fachlogik_Test {

    Parkhaus_Fachlogik parkhaus;

    @BeforeEach
    void init(){
        parkhaus = new Parkhaus_Fachlogik();

    }

    @Test
    void IncAnzahlBesucher_Test() {
        parkhaus.IncAnzahlBesucher();
        assertEquals(1,parkhaus.getAnzahlBesucher());
        parkhaus.IncAnzahlBesucher();
        parkhaus.IncAnzahlBesucher();
        assertEquals(3,parkhaus.getAnzahlBesucher());
    }


    @Test
    void sumEinnahmen_Test() {
        parkhaus.sumEinnahmen(100);
        assertEquals(1,parkhaus.getSummeEinnahmen());
        parkhaus.sumEinnahmen(250);
        assertEquals(3.5 , parkhaus.getSummeEinnahmen());
    }

    @Test
    void getMeanEinnahmen_Test() {
        parkhaus.IncAnzahlBesucher();
        parkhaus.sumEinnahmen(100);
        parkhaus.IncAnzahlBesucher();
        parkhaus.sumEinnahmen(200);
        assertEquals(1.5, parkhaus.getMeanEinnahmen());
    }
}
