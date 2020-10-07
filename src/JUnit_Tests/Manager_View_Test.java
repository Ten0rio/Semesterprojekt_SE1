package JUnit_Tests;

import Klassen.Parkschein;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Manager_View_Test {

    @BeforeEach
    void setUp() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp timestamp1 = new Timestamp(1601981027296L);
        LocalDate date = LocalDate.of(0,1,1);
        System.out.println(timestamp1.toLocalDateTime().toLocalDate().minusWeeks(1));
        System.out.println(timestamp1.toLocalDateTime().toLocalDate());

        ArrayList<Parkschein> tickets = new ArrayList<>();
        tickets. add(new Parkschein(new String[]{"hallo","123","123" }));
        Parkschein p = tickets.remove(tickets.size()-1);
        System.out.println(p.getAutoNr() + "   " + p.getClientCategorie());



    }

    @Test
    void getTagesEinnahmen() {
    }

    @Test
    void getWochenEinnahmen() {
    }

    @Test
    void update() {
    }

    @Test
    void showManagerView() {

    }
}