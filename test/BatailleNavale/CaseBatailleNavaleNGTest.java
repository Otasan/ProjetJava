/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import BatailleNavale.TypeCase;
import BatailleNavale.CaseBatailleNavale;
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
public class CaseBatailleNavaleNGTest {
    
    public CaseBatailleNavaleNGTest() {
    }

    /**
     * Test of touche method, of class CaseBatailleNavale.
     */
    @Test
    public void testTouche() throws Exception {
        CaseBatailleNavale cb = new CaseBatailleNavale(0,0);
        assertEquals(cb.touche(), TypeCase.toucheVierge);
        cb.setCase(TypeCase.bateau);
        assertEquals(cb.touche(), TypeCase.touche);
    }

    /**
     * Test of getCase method, of class CaseBatailleNavale.
     */
    @Test
    public void testGetCase() {
        CaseBatailleNavale cb = new CaseBatailleNavale(0,0);
        assertEquals(cb.getCase(), TypeCase.vierge);
        cb.setCase(TypeCase.bateau);
        assertEquals(cb.getCase(), TypeCase.bateau);
        cb.setCase(TypeCase.toucheVierge);
        assertEquals(cb.getCase(), TypeCase.toucheVierge);
        cb.setCase(TypeCase.touche);
        assertEquals(cb.getCase(), TypeCase.touche);
    }

    /**
     * Test of getX method, of class CaseBatailleNavale.
     */
    @Test
    public void testGetX() {
        for(int i=0;i<10;i++){
            CaseBatailleNavale cb = new CaseBatailleNavale(i,0);
            assertEquals(cb.getX(), i);
        }
    }

    /**
     * Test of getY method, of class CaseBatailleNavale.
     */
    @Test
    public void testGetY() {
        for(int i=0;i<10;i++){
            CaseBatailleNavale cb = new CaseBatailleNavale(0,i);
            assertEquals(cb.getY(), i);
        }
    }

    /**
     * Test of setCase method, of class CaseBatailleNavale.
     */
    @Test
    public void testSetCase() {
        CaseBatailleNavale cb = new CaseBatailleNavale(0,0);
        assertEquals(cb.getCase(), TypeCase.vierge);
        cb.setCase(TypeCase.bateau);
        assertEquals(cb.getCase(), TypeCase.bateau);
        cb.setCase(TypeCase.toucheVierge);
        assertEquals(cb.getCase(), TypeCase.toucheVierge);
        cb.setCase(TypeCase.touche);
        assertEquals(cb.getCase(), TypeCase.touche);
    }

    /**
     * Test of compareTo method, of class CaseBatailleNavale.
     */
    @Test
    public void testCompareTo() {
        for(int i = 0;i<10;i++){
            CaseBatailleNavale cbm = new CaseBatailleNavale(i,0);
            CaseBatailleNavale cb = new CaseBatailleNavale(1+i, 0);
            assertTrue(cbm.compareTo(cb)<0);
        }
        for(int i=0;i<10;i++){
            CaseBatailleNavale cbm = new CaseBatailleNavale(0,i);
            CaseBatailleNavale cb = new CaseBatailleNavale(0, i+1);
            assertTrue(cbm.compareTo(cb)<0);
        }
        for(int i=0;i<100;i++){
            CaseBatailleNavale cbm = new CaseBatailleNavale(i%10, (int)i/10);
            CaseBatailleNavale cb = new CaseBatailleNavale((i+1)%10, (int)(i+1)/10);
            assertTrue(cbm.compareTo(cb)<0);
        }
    }

    /**
     * Test of toString method, of class CaseBatailleNavale.
     */
    /*@Test
    public void testToString() {
        System.out.println("toString");
        CaseBatailleNavale instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
