/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

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

    public boolean addMembre(String pseudo, String mdp, boolean admin) {
        try {
            Membre m = new Membre(pseudo, mdp);
            comptes.put(pseudo, m);
            return true;
        } catch (ConnexionException ex) {
            return false;
        }

    }

    public boolean removeMembre(Membre m) {
        return comptes.remove(m.getPseudo(), m);
    }

    public boolean removeMembre(String pseudo) {
        return comptes.remove(pseudo)!=null;
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
