package JUnit_Tests;

import Klassen.Manager_View;
import Klassen.Parkhaus_Fachlogik;
import Klassen.Parkschein;
import Klassen.WochenEinnahmen_View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Manager_View_Test {

//    @BeforeEach
//    void setUp() {

//        Parkhaus_Fachlogik parkhaus = new Parkhaus_Fachlogik();
//
//
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Timestamp timestamp1 = new Timestamp(1601981027296L);
//        LocalDate date = LocalDate.of(0,1,1);
//        System.out.println(timestamp1.toLocalDateTime().toLocalDate().minusWeeks(1));
//        System.out.println(timestamp1.toLocalDateTime().toLocalDate());
//
//        ArrayList<Parkschein> tickets = new ArrayList<>();
//        tickets. add(new Parkschein(new String[]{"hallo","123","123" }));
//        Parkschein p = tickets.remove(tickets.size()-1);
//        System.out.println(p.getAutoNr() + "   " + p.getClientCategorie());
//    }

    Parkhaus_Fachlogik parkhaus_fachlogik;
    Manager_View manager_view;

    @BeforeEach
    void setup(){
        parkhaus_fachlogik = new Parkhaus_Fachlogik();
        manager_view = new WochenEinnahmen_View(parkhaus_fachlogik);
        parkhaus_fachlogik.add(manager_view);
    }


    @Test
    @DisplayName("Wert sollte 0 sein!")
    void updateleer() {
        manager_view.update();
        assertEquals(0,manager_view.getEinnahmen());
    }


    @Test
    @DisplayName("Werte höher 0")
    void update(){
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","500"});
        assertEquals(5,manager_view.getEinnahmen());
    }
    @Test
    @DisplayName("Wert gleich 5")
    void updatemehr(){
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","500"});
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","200"});
        parkhaus_fachlogik.removeParkschein();
        assertEquals(5,manager_view.getEinnahmen());
    }

    @Test
    @DisplayName("Update mit remove auf 0")
    void udatetozero() {
        parkhaus_fachlogik.addParkschein(new String[]{"1","2","3","4","500"});
        parkhaus_fachlogik.removeParkschein();
        assertEquals(0,manager_view.getEinnahmen());
    }
}