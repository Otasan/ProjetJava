/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import ProjetJava.ConnexionException;
import ProjetJava.Utilisateur;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dobby
 */
public class UtilisateurTest {

    /**
     * Test of getPseudo method, of class Utilisateur.
     */
    @Test
    public void testGetPseudo() {
        try {
            String pseudo = "XXXXXXXXXX";
            Utilisateur u = new Utilisateur(pseudo);
            assertTrue(u.getPseudo().equals(pseudo));
            assertFalse(!u.getPseudo().equals(pseudo));
        } catch (ConnexionException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of estPseudoValide method, of class Utilisateur.
     */
    @Test
    public void testEstPseudoValide() {
        assertFalse(Utilisateur.estPseudoValide(null));
        final String SUFFIX = "XXXXXXXX";
        String ch = "";
        for (int i = 0; i <= 35; i++) {
            if (i <= 30 && i >= 6) {
                assertTrue(Utilisateur.estPseudoValide(ch));
            } else {
                assertFalse(Utilisateur.estPseudoValide(ch));
            }
            ch += 'X';
        }
        for (char c = '0'; c <= Character.MAX_HIGH_SURROGATE; c++) {
            ch = c + SUFFIX;
            if (ch.matches("[[a-z][A-Z][0-9]]*")) {
                assertTrue(Utilisateur.estPseudoValide(ch));
            } else {
                assertFalse(Utilisateur.estPseudoValide(ch));
            }
        }
    }

}
