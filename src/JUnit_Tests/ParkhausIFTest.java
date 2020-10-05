package JUnit_Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkhausIFTest {
    Parkhaus parkhaus;
    @BeforeEach
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