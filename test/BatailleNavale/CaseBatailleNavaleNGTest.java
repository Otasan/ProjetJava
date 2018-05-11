/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import BatailleNavale.TypeCase;
import BatailleNavale.CaseBN;
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
     * Test of touche method, of class CaseBN.
     */
    @Test
    public void testTouche() throws Exception {
        CaseBN cb = new CaseBN(0,0);
        assertEquals(cb.touche(), TypeCase.toucheVierge);
        cb.setCase(TypeCase.bateau);
        assertEquals(cb.touche(), TypeCase.touche);
    }

    /**
     * Test of getCase method, of class CaseBN.
     */
    @Test
    public void testGetCase() {
        CaseBN cb = new CaseBN(0,0);
        assertEquals(cb.getCase(), TypeCase.vierge);
        cb.setCase(TypeCase.bateau);
        assertEquals(cb.getCase(), TypeCase.bateau);
        cb.setCase(TypeCase.toucheVierge);
        assertEquals(cb.getCase(), TypeCase.toucheVierge);
        cb.setCase(TypeCase.touche);
        assertEquals(cb.getCase(), TypeCase.touche);
    }

    /**
     * Test of getX method, of class CaseBN.
     */
    @Test
    public void testGetX() {
        for(int i=0;i<10;i++){
            CaseBN cb = new CaseBN(i,0);
            assertEquals(cb.getX(), i);
        }
    }

    /**
     * Test of getY method, of class CaseBN.
     */
    @Test
    public void testGetY() {
        for(int i=0;i<10;i++){
            CaseBN cb = new CaseBN(0,i);
            assertEquals(cb.getY(), i);
        }
    }

    /**
     * Test of setCase method, of class CaseBN.
     */
    @Test
    public void testSetCase() {
        CaseBN cb = new CaseBN(0,0);
        assertEquals(cb.getCase(), TypeCase.vierge);
        cb.setCase(TypeCase.bateau);
        assertEquals(cb.getCase(), TypeCase.bateau);
        cb.setCase(TypeCase.toucheVierge);
        assertEquals(cb.getCase(), TypeCase.toucheVierge);
        cb.setCase(TypeCase.touche);
        assertEquals(cb.getCase(), TypeCase.touche);
    }

    /**
     * Test of compareTo method, of class CaseBN.
     */
    @Test
    public void testCompareTo() {
        for(int i = 0;i<10;i++){
            CaseBN cbm = new CaseBN(i,0);
            CaseBN cb = new CaseBN(1+i, 0);
            assertTrue(cbm.compareTo(cb)<0);
        }
        for(int i=0;i<10;i++){
            CaseBN cbm = new CaseBN(0,i);
            CaseBN cb = new CaseBN(0, i+1);
            assertTrue(cbm.compareTo(cb)<0);
        }
        for(int i=0;i<100;i++){
            CaseBN cbm = new CaseBN(i%10, (int)i/10);
            CaseBN cb = new CaseBN((i+1)%10, (int)(i+1)/10);
            assertTrue(cbm.compareTo(cb)<0);
        }
    }

    /**
     * Test of toString method, of class CaseBatailleNavale.
     */
    /*@Test
    public void testToString() {
        System.out.println("toString");
        CaseBN instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
