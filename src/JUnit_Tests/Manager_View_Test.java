package JUnit_Tests;

import Klassen.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class Manager_View_Test {


    Parkhaus_Fachlogik parkhaus_fachlogik;
    Manager_View manager_view_Woche;
    Manager_View manager_view_Tag;
    Manager_View manager_view_Monat;

    @BeforeEach
    void setup(){
        parkhaus_fachlogik = new Parkhaus_Fachlogik();
        manager_view_Woche = new WochenEinnahmen_View(parkhaus_fachlogik);
        manager_view_Tag = new TagesEinnahmen_View(parkhaus_fachlogik);
        manager_view_Monat = new MonatsEinnahmen_View(parkhaus_fachlogik);

        parkhaus_fachlogik.add(manager_view_Woche);
        parkhaus_fachlogik.add(manager_view_Tag);
        parkhaus_fachlogik.add(manager_view_Monat);
    }


    @Test
    @DisplayName("update() bei leerem Parkhaus sollte einnahmen auf 0 setzen")
    void updateleer() {
        manager_view_Woche.update();
        assertEquals(0, manager_view_Woche.getEinnahmen());
        parkhaus_fachlogik.removeParkschein();
        assertEquals(0, manager_view_Woche.getEinnahmen());
    }


    @Test
    @DisplayName("Werte höher 0")
    void update(){
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","500"});
        assertEquals(5, manager_view_Woche.getEinnahmen());
    }
    @Test
    @DisplayName("Wert gleich 5")
    void updatemehr(){
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","500"});
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","200"});
        parkhaus_fachlogik.removeParkschein();
        assertEquals(5, manager_view_Woche.getEinnahmen());
    }
    @Test
    @DisplayName("der in date gespeicherte Tag ist länger als ein Tag her und die Einnahmen müssen zurück gesetzt werden")
    void updatedatetag(){

        parkhaus_fachlogik.addParkschein(new String[]{"leave","1153","0","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1153" });
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1154","87000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        assertEquals(1.5, manager_view_Tag.getEinnahmen());
    }


    @Test
    @DisplayName("der in date gespeicherte Tag ist länger als eine Woche her und die Einnahmen müssen zurück gesetzt werden")
    void updatedateWoche(){
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1153","0","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1153" });
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1154","1000000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        assertEquals(1.5, manager_view_Woche.getEinnahmen());
    }

    @Test
    @DisplayName("der in date gespeicherte Tag ist länger als einen Monat her und die Einnahmen müssen zurück gesetzt werden")
    void updatedateMonat(){
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1153","0","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1153" });
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1154","3000000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        assertEquals(1.5, manager_view_Monat.getEinnahmen());
    }

    @Test
    @DisplayName("undo() nachdem die Einnahmen zurück gesetzt wurden holt den Wert vor dem Zurücksetzen zurück")
    void updatedate2(){
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1153","0","1500","400","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1153" });
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1153","0","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1153" });
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1153","1000000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1153" });
        parkhaus_fachlogik.addParkschein(new String[]{"leave","1154","2000000000","1500","150","2b0f8a8452b04d02b98d5193216a8cab","#a6eedf","8","family","Trike","1154" });
        assertEquals(1.5, manager_view_Woche.getEinnahmen());
        parkhaus_fachlogik.removeParkschein();
        assertEquals(1.5, manager_view_Woche.getEinnahmen());
        parkhaus_fachlogik.removeParkschein();
        assertEquals(5.5, manager_view_Woche.getEinnahmen());
        parkhaus_fachlogik.removeParkschein();
        assertEquals(4, manager_view_Woche.getEinnahmen());
        parkhaus_fachlogik.removeParkschein();
        assertEquals(0 , manager_view_Woche.getEinnahmen());
    }
}