/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dobby
 */
public class IdentificationTest {

    /**
     * Test of sauvegarde method, of class Identification.
     */
    @Test
    public void testSauvegarde() {

        String pseudo = "XXXXXXXXXXXXXXXX";
        String mdp = "xxxxxxxxxxxxxx";
        String path = "TestSauvegarde.save";

        try {
            Identification i = new Identification();
            Membre m = new Membre(pseudo, mdp);

            i.addMembre(pseudo, mdp);
            i.sauvegarde(path);

        } catch (ConnexionException ex) {
            System.out.println("Sauvegarde Exception");

        }

        try {
            Identification i2 = new Identification(path);
            Membre m2 = i2.connexion(pseudo, mdp);
            assertTrue(m2.getPseudo().equals(pseudo));
            File f = new File(path);
            f.delete();
        } catch (FileNotFoundException | ConnexionException ex) {
            System.out.println("Sauvegarde Exception");

        }

    }

    /**
     * Test of connexion method, of class Identification.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testConnexion() {

        String pseudo = "XXXXXXXXXXXXXXX";
        String mdp = "xxxxxxxxxxxxxxxx";
        Membre m;
        Identification i = new Identification();

        try {
            m = new Membre(pseudo, mdp);
            i.addMembre(m);
        } catch (ConnexionException ex) {
            System.out.println("Membre Exception");
        }

        try {
            m = new Membre(pseudo, mdp);
            assertEquals(i.connexion(pseudo, mdp), m);
            assertNotEquals(i.connexion(pseudo, mdp + "x"), m);
        } catch (ConnexionException ex) {
            System.out.println("Connexion Exception");
        }
    }

    /**
     * Test of addMembre method, of class Identification.
     */
    @Test
    public void testAddMembre_Membre() {
        try {
            String pseudo = "XXXXXXXXXXXXXXXX";
            String mdp = "xxxxxxxxxxxxxx";
            Membre m = new Membre(pseudo, mdp);

            Identification i = new Identification();
            assertTrue(i.addMembre(m));
        } catch (ConnexionException ex) {
            System.out.println("RemoveMembre_Membre Exception");
        }

    }

    /**
     * Test of addMembre method, of class Identification.
     */
    @Test
    public void testAddMembre_String_String() {
        String pseudo = "XXXXXXXXXXXXXXXX";
        String mdp = "xxxxxxxxxxxxxx";

        Identification i = new Identification();
        assertTrue(i.addMembre(pseudo, mdp));
    }

    /**
     * Test of removeMembre method, of class Identification.
     */
    @Test
    public void testRemoveMembre_Membre() {
        String pseudo = "XXXXXXXXXXXXXXXX";
        String mdp = "xxxxxxxxxxxxxx";

        try {
            Membre m = new Membre(pseudo, mdp);
            Membre m2 = new Membre(pseudo, mdp + "x");
            Membre m3 = new Membre(pseudo, mdp + "x");
            Identification i = new Identification();

            i.addMembre(m);

            assertFalse(i.removeMembre(m2));
            assertFalse(i.removeMembre(m3));
            assertTrue(i.removeMembre(m));
        } catch (ConnexionException ex) {
            System.out.println("RemoveMembre_Membre Exception");
        }
    }

    /**
     * Test of removeMembre method, of class Identification.
     */
    @Test
    public void testRemoveMembre_String_String() {
        String pseudo = "XXXXXXXXXX";
        String mdp = "xxxxxxxxxx";

        Identification i = new Identification();
        i.addMembre(pseudo, mdp);

        assertFalse(i.removeMembre(pseudo, mdp + "x"));
        assertFalse(i.removeMembre(pseudo + "X", mdp));
        assertFalse(i.removeMembre(pseudo + "X", mdp + "x"));

        assertTrue(i.removeMembre(pseudo, mdp));
        assertFalse(i.removeMembre(pseudo, mdp));
    }

}
