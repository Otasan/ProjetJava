/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import BatailleNavale.Torpilleur;
import BatailleNavale.Bateau;
import BatailleNavale.PorteAvion;
import BatailleNavale.Croiseur;
import BatailleNavale.Direction;
import BatailleNavale.TypeCase;
import BatailleNavale.SousMarin;
import BatailleNavale.CaseBatailleNavale;
import BatailleNavale.ContreTorpilleur;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aabdo
 */
public class BateauTest {
    
    public BateauTest() {
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
     * Test of getTaille method, of class Bateau.
     */
    @Test
    public void testGetTaille() {
        ContreTorpilleur c = new ContreTorpilleur(new CaseBatailleNavale(0,0), Direction.horizontal);
        assertEquals(c.getTaille(), 3);
        Croiseur co = new Croiseur(new CaseBatailleNavale(0,0), Direction.horizontal);
        assertEquals(co.getTaille(), 4);
        PorteAvion p = new PorteAvion(new CaseBatailleNavale(0,0), Direction.horizontal);
        assertEquals(p.getTaille(), 5);
        SousMarin s = new SousMarin(new CaseBatailleNavale(0,0), Direction.horizontal);
        assertEquals(s.getTaille(), 3);
        Torpilleur t = new Torpilleur(new CaseBatailleNavale(0,0), Direction.horizontal);
        assertEquals(t.getTaille(), 2);
        ContreTorpilleur c1 = new ContreTorpilleur(new CaseBatailleNavale(0,0), Direction.vertical);
        assertEquals(c1.getTaille(), 3);
        Croiseur co1 = new Croiseur(new CaseBatailleNavale(0,0), Direction.vertical);
        assertEquals(co1.getTaille(), 4);
        PorteAvion p1 = new PorteAvion(new CaseBatailleNavale(0,0), Direction.vertical);
        assertEquals(p1.getTaille(), 5);
        SousMarin s1 = new SousMarin(new CaseBatailleNavale(0,0), Direction.vertical);
        assertEquals(s1.getTaille(), 3);
        Torpilleur t1 = new Torpilleur(new CaseBatailleNavale(0,0), Direction.vertical);
        assertEquals(t1.getTaille(), 2);
    }
    
    

    /**
     * Test of toString method, of class Bateau.
     */
    /*@Test
    public void testToString() {
        System.out.println("toString");
        Bateau instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    public class BateauImpl extends Bateau {

        public BateauImpl() {
            super(null, null, 0);
        }
    }

    /**
     * Test of getCaseInitiale method, of class Bateau.
     */
    @Test
    public void testGetCaseInitiale() {
        Bateau b = new Torpilleur(new CaseBatailleNavale(0,0), Direction.vertical);
        CaseBatailleNavale c = b.getCaseInitiale();
        assertTrue(c.getX()==0 && c.getY()==0 && c.getCase()==TypeCase.bateau);
    }

    /**
     * Test of getSens method, of class Bateau.
     */
    @Test
    public void testGetSens() {
        Bateau b = new Torpilleur(new CaseBatailleNavale(0, 0), Direction.horizontal);
        assertEquals(b.getSens(), Direction.horizontal);
        Bateau c = new ContreTorpilleur(new CaseBatailleNavale(0,0), Direction.vertical);
        assertEquals(c.getSens(), Direction.vertical);
    }

    /**
     * Test of equals method, of class Bateau.
     */
    @Test
    public void testEquals() {
        Bateau b = new Torpilleur(new CaseBatailleNavale(0, 0), Direction.horizontal);
        assertTrue(b.equals(b));
        Bateau pa = new PorteAvion(new CaseBatailleNavale(0,0),Direction.horizontal);
        assertFalse(b.equals(pa));
        Bateau b1 = new Torpilleur(new CaseBatailleNavale(50,12), Direction.vertical);
        assertTrue(b.equals(b1));
        assertFalse(b1.equals(pa));
    }
    
}
