/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.OutputStream;

/**
 *
 * @author Dobby
 */
public class Identification {

    private HashMap<String, Membre> comptes;

    public Identification(HashMap<String, Membre> membres) {
        this.comptes = membres;
    }

    public Identification() {
        this.comptes = new HashMap<>();
    }

    public Identification(String path) throws FileNotFoundException {
        this.comptes = new HashMap<>();

        try {
            File f = new File(path);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            HashMap<String, Membre> m = (HashMap<String, Membre>) in.readObject();
            this.comptes = m;
        } catch (IOException | ClassNotFoundException ex) {
            this.comptes = new HashMap<>();
        }
    }

    public Membre connexion(String pseudo, String mdp) throws ConnexionException {
        if (comptes.get(pseudo).connexion(mdp)) {
            return comptes.get(pseudo);
        } else {
            throw new ConnexionException("Erreur dans le mot de passe.");
        }
    }

    public void addMembre(Membre m) {
        comptes.put(m.getPseudo(), m);
    }

    public void removeMembre(Membre m) throws ConnexionException {
        if (!comptes.remove(m.getPseudo(), m)) {
            throw new ConnexionException("Le membre n'as pas ete trouve.");
        }
    }

    public void sauvegarde(String path) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));) {

            oos.writeObject(this.comptes);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String retour = "";
        for (Membre m : comptes.values()) {
            retour += m.toString() + "\n";
        }
        return retour;
    }
}
