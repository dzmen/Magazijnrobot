/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.simulator.algoritmes;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tsp.simulator.GUI.Scherm;
import tsp.simulator.Locatie;
import tsp.simulator.Order;

/**
 *
 * @author Hugo
 */
public class SimpelTest {

    public SimpelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of berekenRoute method, of class Simpel.
     */
    @Test
    public void testBerekenRoute1() {
        System.out.println("berekenRoute");
        Order order = new Order(new Scherm());
        order.getArtikelen().add(new Locatie(1, 2));
        order.getArtikelen().add(new Locatie(3, 2));
        Simpel simpel = new Simpel(order);
        simpel.berekenRoute();
        ArrayList<Locatie> locaties = simpel.getRoute();

        for (Locatie l : locaties) {
            System.out.println(l.getX() + "," + l.getY());
        }
        // start
        assertEquals(1, locaties.get(0).getX());
        assertEquals(0, locaties.get(0).getY());
        // lokaties
        assertEquals(1, locaties.get(1).getX());
        assertEquals(2, locaties.get(1).getY());
        assertEquals(3, locaties.get(2).getX());
        assertEquals(2, locaties.get(2).getY());
    }

    @Test
    public void testBerekenRoute2() {
        System.out.println("berekenRoute");
        Order order = new Order(new Scherm());
        order.getArtikelen().add(new Locatie(1, 2));
        order.getArtikelen().add(new Locatie(3, 2));
        order.getArtikelen().add(new Locatie(9, 4));
        order.getArtikelen().add(new Locatie(2, 8));
        Simpel simpel = new Simpel(order);
        simpel.berekenRoute();
        ArrayList<Locatie> locaties = simpel.getRoute();

        for (Locatie l : locaties) {
            System.out.println(l.getX() + "," + l.getY());
        }
        // start
        assertEquals(1, locaties.get(0).getX());
        assertEquals(0, locaties.get(0).getY());
        // lokaties
        assertEquals(1, locaties.get(1).getX());
        assertEquals(2, locaties.get(1).getY());
        assertEquals(3, locaties.get(2).getX());
        assertEquals(2, locaties.get(2).getY());
        assertEquals(9, locaties.get(3).getX());
        assertEquals(4, locaties.get(3).getY());
        assertEquals(2, locaties.get(4).getX());
        assertEquals(8, locaties.get(4).getY());
    }

}
