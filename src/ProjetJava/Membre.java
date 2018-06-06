/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Identification du joueur avec memorisation de ses scores et de ses
 * identifiants.
 *
 * @author Dobby
 */
public class Membre extends Utilisateur implements Serializable {

    private HashMap<String, Integer[]> scores;
    private int mdp;
    private boolean admin;
    private String[] jeux = {"Bataille Navale", "Pendu"};

    /**
     * Creation d'un Membre avec un score null.
     *
     * @param pseudo Pseudo permettant au Membre de se connecter.
     * @param mdp Mot de passe permettant au Membre de se connecter.
     * @param admin true si me Membre est un Administrateur.
     */
    public Membre(String pseudo, String mdp, boolean admin) {
        super(pseudo);
        this.admin = admin;

        scores = new HashMap<>();
        resetScore(jeux);
        this.mdp = this.keyGen(pseudo, mdp);

    }

    /**
     * Generateur du mdp hashé du Membre.
     *
     * @param pseudo Pseudo du Membre.
     * @param mdp Mot de passe du Membre.
     * @return Entier résultat du hashage du mot de passe.
     */
    private int keyGen(String pseudo, String mdp) {
        String key = pseudo + mdp + pseudo;
        return key.hashCode() * 73 + 37;
    }

    /**
     * Teste si le pseudo est null et s'il contient entre 6 et 30 characteres
     * classiques ([a-z][A-Z][0-9]).
     *
     * @param mdp Mot de passe a tester pour savoir s'il est conforme.
     * @return true si le mot de passe est conforme aux tests.
     */
    public static boolean estMdpValide(String mdp) {
        if (mdp == null || mdp.equals("")) {
            return false;
        }
        if (mdp.length() < 6 || mdp.length() > 30) {
            return false;
        } else {
            return mdp.matches("[[a-z][A-Z][0-9]]*");
        }
    }

    public boolean estAdmin() {
        return admin;
    }

    /**
     * Teste le score pour savoir s'il n'y a pas plus de 2 valeurs dans le
     * tablea, s'il n'est pas null, si le nombre de parties gagnees n'est pas
     * supperieur au nombre de parties jouees.
     *
     * @param score Tableau des scores du joueur a tester.
     * @return true si le score est conforme.
     */
    public static boolean estScoreValide(Integer[] score) {
        if (score == null) {
            return false;
        } else if (score.length != 2) {
            return false;
        } else {
            return score[0] >= score[1];
        }
    }

    /**
     * Renvoie le score du Membre sous forme de tableau pour un jeu donne.
     *
     * @param jeu Nom du jeu pour lequel on veut recuperer le score.
     * @return un tableau [ nbPartiesJouees, nbPartiesGagnees].
     * @throws ScoreException Le score n'est pas valide.
     */
    public Integer[] getScore(String jeu) throws ScoreException {
        Integer[] score = scores.get(jeu);
        if (Membre.estScoreValide(score)) {
            return score;
        } else {
            throw new ScoreException();
        }
    }

    /**
     * Renvoie le ratio (partiesJouees/partiesGagnees)
     *
     * @param jeu Nom du jeu pour lequel on veut recuperer le ratio.
     * @return Le ratio de victoires <1.
     * @throws ScoreException Le score n'est pas conforme.
     */
    public double getRatio(String jeu) throws ScoreException {
        int partiesJouees = scores.get(jeu)[0];
        if (partiesJouees > 0) {
            double ratio = (double) scores.get(jeu)[1] / (double) partiesJouees;
            if (ratio >= 0 && ratio <= 1) {
                return ratio;
            } else {
                throw new ScoreException("Le ratio n'est pas conforme.");
            }
        } else if (partiesJouees == 0) {
            return 0;
        } else {
            throw new ScoreException("Le nombre de parties jouees est inferieur a 0.");
        }
    }

    /**
     * Renvoie le ratio (partiesJouees/partiesGagnees) de tous les jeux du Membre.
     * @return Double entre 0 et 1.
     * @throws ScoreException Le score est impossible.
     */
    public double getRatioTotal() throws ScoreException {
        int partiesJouees = 0;
        int partiesGagnees = 0;
        // Calcule le total des nombre de parties jouees et nombre de parties gagnees.
        for (String j : jeux) {
            partiesJouees += this.getScore(j)[0];
            partiesGagnees += this.getScore(j)[1];
        }
        if (partiesJouees > 0) {
            double ratio =  partiesGagnees/partiesJouees ;
            // Teste si le ratio est bien entre 0 et 1;
            if (ratio >= 0 && ratio <= 1) {
                return ratio;
            } else {
                throw new ScoreException("Le ratio n'est pas conforme.");
            }
        } else if (partiesJouees == 0) {
            return 0;
        } else {
            throw new ScoreException("Le nombre de parties jouees est inferieur a 0.");
        }
    }

    /**
     * Change le mot de passe du Membre.
     *
     * @param mdp Nouveau mot de passe du Membre.
     */
    public void setMotDePasse(String mdp) {
        this.mdp = this.keyGen(this.getPseudo(), mdp);
    }

    @Override
    public String toString() {
        String retour = super.toString();
        if (admin) {
            retour += " admin";
        }
        for (String jeu : scores.keySet()) {
            try {
                retour += " " + jeu + ": [" + this.getScore(jeu)[0] + ", " + this.getScore(jeu)[1] + "]";
            } catch (ScoreException ex) {
            }
        }
        return retour;
    }

    /**
     * Change le score du Membre lorsqu'il a gagne.
     *
     * @param jeu Nom du jeu que le Membre viens de gagner.
     */
    public void incrementGagne(String jeu) {
        scores.get(jeu)[0] += 1;
        scores.get(jeu)[1] += 1;
    }

    /**
     * Change le score du Membre lorsqu'il a perdu.
     *
     * @param jeu Nom du jeu que le Membre viens de perdre.
     */
    public void incrementPerdu(String jeu) {
        scores.get(jeu)[0] += 1;
    }

    /**
     * Teste si le mot de passe entre pour se connecter est le bon.
     *
     * @param mdp Mot de passe entre par le Membre.
     * @return true si le mot de passe est celui du Membre.
     */
    public boolean connexion(String mdp) {

        if (Membre.estMdpValide(mdp)) {

            int testMdp = this.keyGen(this.getPseudo(), mdp);

            return testMdp == this.mdp;

        } else {
            return false;
        }
    }

    /**
     * Remet le score du Membre a zero pour les jeux entres en parametre.
     *
     * @param jeux Tableau contenant tous les jeux a remetre a zero.
     */
    public void resetScore(String[] jeux) {
        Integer[] tableauVide = {0, 0};
        for (String j : jeux) {
            scores.put(j, tableauVide.clone());
        }
    }
}
