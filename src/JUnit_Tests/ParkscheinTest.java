package JUnit_Tests;

import Klassen.Parkschein;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkscheinTest {
    @Test
    void parkscheinKonstruktor(){
        assertDoesNotThrow(()->new Parkschein(new String[]{"0"}));
    }

}