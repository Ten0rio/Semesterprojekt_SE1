package JUnit_Tests;

import Klassen.Parkhaus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    Parkhaus parkhaus;
    @AfterEach
    void init(){
       parkhaus=new Parkhaus(100);
    }

    // Ein und ausfahren sind abhängig von automat und parkschein
    @Test
    void einfahren() {

    }

    @Test
    void ausfahren() {
    }

    @Test
    void getParkPlaetzeMax() {
        assertEquals(100,parkhaus.getParkPlaetzeMax());

    }

    // 0 da noch kein auto einfahren kann aender wenn moeglich
    @Test
    void getParkplatzbelegt() {
        assertEquals(0,parkhaus.getParkplatzbelegt());
    }
}