/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import ProjetJava.ConnexionException;
import ProjetJava.Membre;
import ProjetJava.ScoreException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dobby
 */
public class MembreTest {

    /**
     * Test of getScore method, of class Membre.
     */
    @Test
    public void testGetScore() {
        try {
            /**
             * Le score est nul au départ pour le pendu et la bataille navalle.
             */
            Membre m = new Membre("XXXXXXX", "XXXXXXX", false);
            String batailleNav = "BatailleNavale";

            int[] scoreTestNav = {0, 0};
            int[] scoreNav = {m.getScore(batailleNav)[0], m.getScore(batailleNav)[1]};

            assertArrayEquals(scoreTestNav, scoreNav);

            String pendu = "Pendu";

            int[] scoreTestPen = {0, 0};
            int[] scorePen = {m.getScore(pendu)[0], m.getScore(pendu)[1]};

            assertArrayEquals(scoreTestPen, scorePen);

            /**
             * Le score augmente bien de la bonne façon.
             */
            m.incrementGagne(batailleNav);
            m.incrementPerdu(pendu);

            int[] scoreTestNav2 = {1, 1};
            int[] scoreNav2 = {m.getScore(batailleNav)[0], m.getScore(batailleNav)[1]};

            assertArrayEquals(scoreTestNav2, scoreNav2);

            int[] scoreTestPen2 = {1, 0};
            int[] scorePen2 = {m.getScore(pendu)[0], m.getScore(pendu)[1]};

            assertArrayEquals(scoreTestPen2, scorePen2);
        } catch (ScoreException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of getRatio method, of class Membre.
     */
    @Test
    public void testGetRatio() throws Exception {

    }

    /**
     * Test of setMotDePasse method, of class Membre.
     */
    @Test
    public void testSetMotDePasse() {
        try {
            String pseudo = "zzzzzzzzzzzzzz";
            String mdp1 = "XXXXXXXXXXXXX";
            String mdp2 = "xxxxxxxxxxxxx";
            Membre u = new Membre(pseudo, mdp1, false);
            assertTrue(u.connexion(mdp1));
            assertFalse(!u.connexion(mdp1));
            u.setMotDePasse(mdp2);
            assertTrue(u.connexion(mdp2));
            assertFalse(!u.connexion(mdp2));
        } catch (ConnexionException ex) {
            Logger.getLogger(MembreTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of incrementGagne method, of class Membre.
     */
    @Test
    public void testIncrementGagne() {

            /**
             * Le score change bien en augmentant de 1 les victoires et les
             * parties jouees de bataille navale.
             */
            Membre m = new Membre("XXXXXX", "XXXXXXX", false);
            String batailleNav = "BatailleNavale";

            int[] scoreTest = {0, 0};

            for (int i = 0; i < 10000; i++) {

                try {
                    int[] score = {m.getScore(batailleNav)[0], m.getScore(batailleNav)[1]};
                    
                    assertArrayEquals(scoreTest, score);
                    
                    scoreTest[0] += 1;
                    scoreTest[1] += 1;
                    
                    m.incrementGagne(batailleNav);
                } catch (ScoreException ex) {}
            }

            /**
             * Le score change bien en augmentant de 1 les victoires et les
             * parties jouees de pendu.
             */
            String pendu = "Pendu";

            scoreTest[0] = 0;
            scoreTest[1] = 0;

            for (int i = 0;
                    i < 10000; i++) {

                try {
                    int[] score = {m.getScore(pendu)[0], m.getScore(pendu)[1]};
                    assertArrayEquals(scoreTest, score);
                    
                    scoreTest[0] += 1;
                    scoreTest[1] += 1;
                    
                    m.incrementGagne(pendu);
                } catch (ScoreException ex) {}
            }
        

    }

    /**
     * Test of incrementPerdu method, of class Membre.
     */
    @Test
    public void testIncrementPerdu() {
        /**
         * Le score change bien en augmentant de 1 les parties jouees de
         * bataille navale.
         */
        try {
            Membre m = new Membre("XXXXXX", "XXXXXXX", false);
            String batailleNav = "BatailleNavale";

            int[] scoreTest = {0, 0};

            for (int i = 0; i < 10000; i++) {
                try {
                    int[] score = {m.getScore(batailleNav)[0], m.getScore(batailleNav)[1]};
                    assertArrayEquals(scoreTest, score);

                    scoreTest[0] += 1;

                    m.incrementPerdu(batailleNav);
                } catch (ScoreException ex) {}
            }
            /**
             * Le score change bien en augmentant de 1 les parties jouees
             * dependu.
             */
            String pendu = "Pendu";

            scoreTest[0] = 0;
            scoreTest[1] = 0;

            for (int i = 0; i < 10000; i++) {

                int[] score = {m.getScore(pendu)[0], m.getScore(pendu)[1]};
                assertArrayEquals(scoreTest, score);

                scoreTest[0] += 1;

                m.incrementPerdu(pendu);

            }
        } catch (ScoreException ex) {}
    }

    /**
     * Test of connexion method, of class Membre.
     */
    @Test
    public void testConnexion() {
        try {
            String pseudo = "XXXXXXX";
            String mdp = "XXXXXXX";

            Membre m = new Membre(pseudo, mdp, false);
            assertFalse(m.connexion(null));
            String s = "";
            while (s.length() < 1000) {
                if (mdp.equals(s)) {
                    assertTrue(m.connexion(s));
                } else {
                    assertFalse(m.connexion(s));
                }
                s += "X";
            }
        } catch (ConnexionException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of estMdpValide method, of class Membre.
     */
    @Test
    public void testEstMdpValide() {
        assertFalse(Membre.estMdpValide(null));
        final String SUFFIX = "XXXXXXXX";
        String ch = "";
        for (int i = 0; i <= 35; i++) {
            if (i <= 30 && i >= 6) {
                assertTrue(Membre.estMdpValide(ch));
            } else {
                assertFalse(Membre.estMdpValide(ch));
            }
            ch += 'X';
        }
        for (char c = '0'; c <= Character.MAX_HIGH_SURROGATE; c++) {
            ch = c + SUFFIX;
            if (ch.matches("[[a-z][A-Z][0-9]]*")) {
                assertTrue(Membre.estMdpValide(ch));
            } else {
                assertFalse(Membre.estMdpValide(ch));
            }
        }
    }

    /**
     * Test of estScoreValide method, of class Membre.
     */
    @Test
    public void testEstScoreValide() {
        assertFalse(Membre.estScoreValide(null));
        Integer[] scoreTrue = {0, 0};
        Integer[] scoreFalse = {0, 0};
        for (int i = 0; i < 10000; i++) {

            scoreTrue[0] = i;
            scoreFalse[1] = i;

            for (int j = 0; j < i; j++) {

                scoreTrue[1] = j;
                scoreFalse[0] = j;

                assertTrue(Membre.estScoreValide(scoreTrue));
                assertFalse(Membre.estScoreValide(scoreFalse));
            }
        }
    }
}
