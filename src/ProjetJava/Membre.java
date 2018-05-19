/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Dobby
 */
public class Membre extends Utilisateur implements Serializable{

    private HashMap<String, Integer[]> scores;
    private int mdp;
    private boolean admin;

    public Membre(String pseudo, String mdp, boolean admin) throws ConnexionException {
        super(pseudo);
        this.admin =admin;
        
        if (Membre.estMdpValide(mdp)) {
            scores = new HashMap<>();
            String[] jeu = {"BatailleNavale", "Pendu"};
            Integer[] tableauVide = new Integer[2];
            for (String j : jeu) {
                scores.put(j, tableauVide.clone());
                scores.get(j)[0] = 0;
                scores.get(j)[1] = 0;
            }
            this.mdp = this.keyGen(pseudo, mdp);
        } else {
            throw new ConnexionException();
        }
    }

    private int keyGen(String pseudo, String mdp) {
        String key = pseudo + mdp;
        return key.hashCode() * 73 + 37;
    }

    public static boolean estPseudoValide(String pseudo) {
        if (pseudo == null || pseudo.equals("")){
            return false;
        }
        if (pseudo.length() < 6 || pseudo.length() > 30) {
            return false;
        } else {
            return pseudo.matches("[[a-z][A-Z][0-9]]*");
        }
    }
        
    public static boolean estMdpValide(String mdp) {
        if (mdp == null || mdp.equals("")){
            return false;
        }
        if (mdp.length() < 6 || mdp.length() > 30) {
            return false;
        } else {
            return mdp.matches("[[a-z][A-Z][0-9]]*");
        }
    }

    public boolean estAdmin(){
        return admin;
    }
    
    public static boolean estScoreValide(Integer[] score) {
        if (score == null) {
            return false;
        } else if (score.length != 2) {
            return false;
        } else {
            return score[0] >= score[1];
        }
    }

    public Integer[] getScore(String jeu) throws ScoreException {
        Integer[] score = scores.get(jeu);
        if (Membre.estScoreValide(score)) {
            return score;
        } else {
            throw new ScoreException();
        }
    }

    public double getRatio(String jeu) throws ScoreException {
        int partiesJouees = scores.get(jeu)[0];
        if (partiesJouees > 0) {
            double ratio = scores.get(jeu)[1] / partiesJouees;
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

    public void setMotDePasse(String mdp) throws ConnexionException {
        if (Membre.estMdpValide(mdp)) {
            this.mdp = this.keyGen(this.getPseudo(), mdp);
        } else {
            throw new ConnexionException();
        }
    }

    @Override
    public String toString() {
        String retour = super.toString();
        if (admin){
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

    public void incrementGagne(String jeu) {
        scores.get(jeu)[0] += 1;
        scores.get(jeu)[1] += 1;
    }

    public void incrementPerdu(String jeu) {
        scores.get(jeu)[0] += 1;
    }

    public boolean connexion(String mdp) {

        if (Membre.estMdpValide(mdp)) {

            int testMdp = this.keyGen(this.getPseudo(), mdp);

            return testMdp == this.mdp;

        } else {
            return false;
        }
    }

}
