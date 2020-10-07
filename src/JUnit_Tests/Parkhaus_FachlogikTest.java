package JUnit_Tests;

import Klassen.Parkhaus_Fachlogik;
import Klassen.Parkschein;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Parkhaus_FachlogikTest {


    Parkhaus_Fachlogik parkhaus;

    @BeforeEach
    void setup(){
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


    @Test
    void decAnzahlBesucher() {
        parkhaus.IncAnzahlBesucher();
        parkhaus.DecAnzahlBesucher();
        assertEquals(0,parkhaus.getAnzahlBesucher());
    }

    @Test
    void updateSumEinnahmen() {
        parkhaus.sumEinnahmen(500);
        parkhaus.updateSumEinnahmen(300);
        assertEquals(2, parkhaus.getSummeEinnahmen());

        parkhaus.updateSumEinnahmen(300);
        assertEquals(0,parkhaus.getSummeEinnahmen());
    }

    @Test
    void addParkschein() {
        parkhaus.addParkschein(new String[]{"leave","1154","87000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        Parkschein parkschein=  parkhaus.getTickets().get(0);
        assertEquals("1154", parkschein.getAutoNr());
        assertEquals(1,parkhaus.getAnzahlBesucher());
        assertEquals(1.5, parkhaus.getSummeEinnahmen());
    }

    @Test
    void removeParkschein() {
        parkhaus.addParkschein(new String[]{"leave","1154","87000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        parkhaus.removeParkschein();
        assertEquals(0,parkhaus.getAnzahlBesucher());
        parkhaus.removeParkschein();
        assertDoesNotThrow(()->{parkhaus.removeParkschein();});
    }

    @Test
    void getJsonArrayParkgebuehren() {
        parkhaus.addParkschein(new String[]{"leave","1154","87000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        assertEquals("[1.5]",parkhaus.getJsonArrayParkgebuehren().toString());
    }

    @Test
    void getJsonArrayNumber() {
        parkhaus.addParkschein(new String[]{"leave","1154","87000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        assertEquals("[\"Nr.1154\"]",parkhaus.getJsonArrayNumber().toString());
    }
}