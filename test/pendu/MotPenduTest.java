/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author achelle
 */
public class MotPenduTest {
    
    public MotPenduTest() {
    }

    /**
     * Test of toString method, of class MotPendu.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MotPendu instance = new MotPendu();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lettreEstValide method, of class MotPendu.
     */
    @Test
    public void testLettreEstValide() {
        MotPendu instance = new MotPendu();
        String lettre = "";
        assertFalse(instance.lettreEstValide(lettre));
        
    }

    /**
     * Test of DemarerLeJeu method, of class MotPendu.
     */
    @Test
    public void testDemarerLeJeu() {
        System.out.println("DemarerLeJeu");
        MotPendu instance = new MotPendu();
        instance.DemarerLeJeu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
