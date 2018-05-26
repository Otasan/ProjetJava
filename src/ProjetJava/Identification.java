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
 * Sauvegarde tous les utilisateurs.
 *
 * @author Dobby
 */
public class Identification {

    private HashMap<String, Membre> comptes;

    /**
     * Cree une hashmap vide de Membres et ajoute un compte admin.
     */
    public Identification() {
        this.comptes = new HashMap<>();
        this.addMembre("administrateur", "administrateur", true);
    }

    /**
     * Deserialize le contenu du fichier path.
     *
     * @param path Chemin vers le fichier contenant l'instance de Identification
     * Serializée.
     * @throws FileNotFoundException Si le fichier n'est pas trouvé, une hashmap
     * avec l'utilisateur administrateur est cree.
     */
    public Identification(String path) throws FileNotFoundException {
        this.comptes = new HashMap<>();

        try {
            File f = new File(path);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            this.comptes = (HashMap<String, Membre>) in.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            this.comptes = new HashMap<>();
            this.addMembre("administrateur", "administrateur", true);

        }
    }

    /**
     * Renvoie le Membre pour lequel le pseudo et le mot de passe ont ete
     * rentres.
     *
     * @param pseudo pseudo de l'utilisateur souhaitant se connecter.
     * @param mdp mot de passe de l'utilisateur souhaitant se connecter.
     * @return le Membre qui correspond à ce pseudo et ce mot de passe.
     * @throws ConnexionException Les informations fournies ne correspondent à
     * aucun utilisateur.
     */
    public Membre connexion(String pseudo, String mdp) throws ConnexionException {
        if (comptes.get(pseudo).connexion(mdp)) {
            return comptes.get(pseudo);
        } else {
            throw new ConnexionException("Erreur dans le mot de passe.");
        }
    }

    /**
     * Ajoute un nouveau Membre dans la liste de tous les membres.
     *
     * @param pseudo Pseudo du nouveau Membre.
     * @param mdp Mot de passe du nouveau Membre.
     * @param admin true si le nouveau Membre est un admin.
     * @return true si l'ajout à fonctionne, false si l'ajout a ete impossible.
     */
    public boolean addMembre(String pseudo, String mdp, boolean admin) {
        if (Membre.estPseudoValide(pseudo) && Membre.estMdpValide(mdp)) {
            Membre m = new Membre(pseudo, mdp, admin);
            if (comptes.containsKey(pseudo)) {
                return false;
            } else {
                comptes.put(pseudo, m);
                return true;
            }
        } else {
            return false;
        }

    }

    /**
     * Supprime le Membre de la liste des Membres.
     *
     * @param pseudo Pseudo du Membre à supprimer.
     * @return true si le Membre a bien ete supprime.
     */
    public boolean removeMembre(String pseudo) {
        return comptes.remove(pseudo) != null;
    }

    /**
     * Serialize la liste des comptes Membre.
     *
     * @param path Chemin du fichier dans lequel sauvegarder les donnees
     * Serializees.
     */
    public void sauvegarde(String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));) {

            oos.writeObject(this.comptes);

        } catch (IOException ex) {
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

    public String getScores(String jeu) {
        String score = "Pseudo\t\tVictoires\tRatio";
        for (Membre m : comptes.values()) {
            try {
                if (m.estAdmin()) {
                    score += "\n" + m.getPseudo() + "\t" + m.getScore(jeu)[1] + "\t" + m.getRatio(jeu);
                }
            } catch (ScoreException ex) {
                score += "\n" + m.getPseudo() + "\t####\t####";
            }
        }
        return score;
    }
}
