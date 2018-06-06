/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import ProjetJava.Identification;
import ProjetJava.ConnexionException;
import ProjetJava.Membre;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edeux
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

        Identification i = new Identification();
        Membre m = new Membre(pseudo, mdp, false);

        i.addMembre(pseudo, mdp, false);
        i.sauvegarde(path);

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
     * Test of addMembre method, of class Identification.
     */
    @Test
    public void testAddMembre_String_String() {
        String pseudo = "XXXXXXXXXXXXXXXX";
        String mdp = "xxxxxxxxxxxxxx";

        Identification i = new Identification();
        assertTrue(i.addMembre(pseudo, mdp, false));
    }

    /**
     * Test of removeMembre method, of class Identification.
     */
    @Test
    public void testRemoveMembre_String() {
        String pseudo = "XXXXXXXXXX";
        String mdp = "xxxxxxxxxx";

        Identification i = new Identification();
        i.addMembre(pseudo, mdp, false);

        assertFalse(i.removeMembre(pseudo + "X"));

        assertTrue(i.removeMembre(pseudo));
        assertFalse(i.removeMembre(pseudo));
    }
}
