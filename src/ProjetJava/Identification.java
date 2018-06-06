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
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
/**
 * Sauvegarde tous les utilisateurs. Et gere les connexions.
 *
 * @author edeux
 */
public class Identification {

    private HashMap<String, Membre> comptes;

    /**
     * Cree une hashmap vide de Membres et ajoute un compte admin.
     */
    public Identification() {
        this.comptes = new HashMap<>();
        this.addMembre("admin", "administrateur", true);
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
            File f = new File(getClass().getResource("/ProjetJava/.save").toURI());
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            this.comptes = (HashMap<String, Membre>) in.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            this.comptes = new HashMap<>();
            this.addMembre("admin", "administrateur", true);

        } catch (URISyntaxException ex) {
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
        if (comptes.containsKey(pseudo)) {
            Membre m = comptes.get(pseudo);
            if (m.connexion(mdp)) {
                return m;
            } else {
                throw new ConnexionException();
            }
        } else {
            throw new ConnexionException();
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
    public void sauvegarde() {
        try {

            File f = new File(getClass().getResource("/ProjetJava/.save").toURI());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(this.comptes);

        } catch (IOException | URISyntaxException ex) {
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

    /**
     * Recupere la HashSet des Membres pour permettre de lister tous les
     * Membres.
     *
     * @return HashSet des Membres de l'objet.
     */
    public HashSet<Membre> getMembres() {
        HashSet<Membre> membres = new HashSet<>();
        for (String n : comptes.keySet()) {
            membres.add(comptes.get(n));
        }
        return membres;
    }

    /**
     * Vérifie si un pseudo correspond à un Membre ou non.
     *
     * @param pseudo Pseudo de l'utilisateur à tester.
     * @return le Membre si le pseudo correspond à un Membre ou null sinon.
     */
    public boolean membreExiste(String pseudo) {
        return comptes.containsKey(pseudo);
    }

    /**
     * Recupere le Membre avec le plus grand nombre de victoires.
     * @param jeu Nom du jeu pour lequel on veut le meilleur joueur ou "Total" pour avoir la 
     * somme de tous les jeux.
     * @return Le Membre avec le meilleur score ou null s'il n'y a aucun joueurs.
     */
    public Membre meilleurPartiesGagnees(String jeu) {
        Membre mr = new Membre("membrenull","membrenull",false);;
        if (!jeu.equals("Total")) {
            for (Membre m : this.getMembres()) {
                try {
                    // Le meilleur joueur devient celui qui a le meilleur score pour
                    // un jeu donne.
                    if (m.getScore(jeu)[1] >= mr.getScore(jeu)[1]) {
                        mr = m;
                    }
                } catch (ScoreException ex) {
                    //Si le Membre m n'as pas un score possible, on ne fait rien
                }
            }
            return mr;

        } else {
            String[] listeJeux = {"Pendu", "Bataille Navale"};
            int score = 0;
            int sc = 0;

            for (Membre m : this.getMembres()) {
                try {

                    // Additionne le nombre de parties gagnées pour chaque jeu.
                    for (String j : listeJeux) {
                        sc += m.getScore(j)[1];
                    }

                    // On garde le Membre avec le plus haut nombre de victoires en tout.
                    if (sc >= score) {
                        mr = m;
                        score = sc;
                    }
                } catch (ScoreException ex) {
                    //Si le Membre m n'as pas un score possible, on ne fait rien
                }
            }
            return mr;
        }
    }

    /**
     * Recupere le joueur avec le meilleur ratio.
     * @param jeu Nom du jeu pour lequel on veut le meilleur joueur ou "Total" pour avoir
     * le meilleur ratio de tous les jeux.
     * @return Le Membre avec le meilleur ratio ou null s'il n'y a aucun joueurs.
     */
    public Membre meilleurRatio(String jeu) {
        Membre mr = new Membre("membrenull","membrenull",false);
        if (!jeu.equals("Total")) {
            for (Membre m : this.getMembres()) {
                try {
                    // Le meilleur joueur devient celui qui a le meilleur ratio pour
                    // un jeu donne.
                    if (m.getRatio(jeu) >= mr.getRatio(jeu)) {
                        mr = m;
                    }
                } catch (ScoreException ex) {
                    //Si le Membre m n'as pas un score possible, on ne fait rien
                }
            }
            
        } else {
            for (Membre m : this.getMembres()) {
                try {

                    // On garde le Membre avec le plus haut ratio de victoire pour tous les jeux.
                    if (m.getRatioTotal()>= mr.getRatioTotal()) {
                        mr = m;
                    }
                } catch (ScoreException ex) {
                    //Si le Membre m n'as pas un score possible, on ne fait rien
                }
            }
        }
        return mr;
    }
}
