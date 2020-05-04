package JUnit_Tests;

import Klassen.Automat;
import Klassen.Parkschein;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomatIFTest {
    Automat A1;
    Parkschein test;

    @BeforeEach
    void setup (){
        A1 = new Automat();
        test = new Parkschein();
    }

    @Test
    void parkscheinbezahlen (){
        assertEquals(true, test.istBezahlt());
    }
}