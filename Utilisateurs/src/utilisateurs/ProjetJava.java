/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dobby
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Membre m = new Membre("DeadSpace", "Plop");
            Membre m2 = new Membre("MaAuyane", "Plop");
            Membre m3 = new Membre("Mauyane", "Plop");
            HashMap<String, Membre> h = new HashMap<>();
            h.put(m.getPseudo(), m);
            h.put(m2.getPseudo(), m2);
            String path = "test.jeu";
            Identification c = new Identification(h);
            c.sauvegarde(path);
            Identification c1;
            try {
                c1 = new Identification(path);
                System.out.println(c1.toString());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (ConnexionException ex) {
            Logger.getLogger(ProjetJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
