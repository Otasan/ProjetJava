/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu;

import ProjetJava.Membre;
import ProjetJava.Utilisateur;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author KREATURE
 */
public class JeuPendu extends Observable implements ProjetJava.Jeu {

    private ArrayList<Character> mot;
    private ArrayList<Character> value;
    private ArrayList<Character> lettresUtilisees;
    private ArrayList<Character> lettresMauvaises;
    private int difficulte;
    private Utilisateur u;

    /**
     * Recuperation des caracteres du mot choisi aleatoirement dans le
     * dictionnaire dans l'Arraylist mot Transforme les caracteres du mot en
     * symbole dans l'Arraylist value
     *
     * @param u utilisateur
     * @param diff niveau de difficulté
     * @throws FileNotFoundException
     */
    public JeuPendu(Utilisateur u, int diff) throws FileNotFoundException {
        super();
        Dictionnaire d = new Dictionnaire();
        String motAlea = d.motAleatoire();
        this.u = u;
        this.mot = new ArrayList<>();
        this.value = new ArrayList<>();
        this.lettresMauvaises = new ArrayList<>();
        this.lettresUtilisees = new ArrayList<>();
        difficulte = diff;

        char[] tableau = motAlea.toCharArray();
        for (int i = 0; i < motAlea.length(); i++) {
            this.mot.add(tableau[i]);
        }
        int i = 0;
        for (char r : this.mot) {
            value.add('*');
            i += 1;
        }
    }
    public int getDiff(){
        return difficulte;
    }

    @Override
    public String toString() {
        return this.mot + " " + this.value;
    }

    /**
     * Teste si le caractere choisi par l'utilisateur appartient a [A-Z] et n'a
     * jamais ete utilise
     *
     * @param lettre caractere choisi au clavier par l'utilisateur
     * @return true si le caractere est conforme
     */
    public boolean estLettreValide(char lettre) {
        for (char s : lettresUtilisees) {
            if (lettre == s) {
                JOptionPane.showMessageDialog(null, "La lettre a déjà été utilisées.");
                return false;
            }
        }

        if (lettre >= 'A' && lettre <= 'Z') {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Le caractere saisi n'est pas valide.");
        }
        return false;
    }

    /**
     * Verifie si l'utilisateur a perdu (9 chances en facile, 8 en moyenne, 7 en
     * difficile)
     *
     * @return true si l'utilisateur a perdu
     */
    public boolean perdu() {
        //La difficlte est un entier de 0 a 2 (difficulte facile 0 difficulte moyenne 1, difficute difficile 2)
        if (lettresMauvaises.size() + difficulte > 9) {
            return true;
        }
        return false;
    }

    public boolean gagne() {
        if (mot.equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * Verifie si la caractere appartient au mot a deviner et l'ajout a
     * l'Arraylist lettresUtilisees Si le caractere n'est pas dans le mot, il
     * est ajouter a l'Arraylist lettresMauvaises
     *
     * @param c caractere saisi au clavier par l'utilisateur
     */
    public void etapeJeu(char c) {
        if (estLettreValide(c)) {
            boolean estLettreFausse = true;

            for (int i = 0; i < mot.size(); i++) {
                if (c == mot.get(i)) {
                    value.set(i, c);
                    estLettreFausse = false;
                }
            }
            if (estLettreFausse) {
                lettresMauvaises.add(c);
            }
            lettresUtilisees.add(c);
            this.setChanged();
            status();
        }
    }

    /**
     * Verifie l'etat de la partie (en cours, perdue, gagnee)
     */
    public void status() {
        notifyObservers();
        if (perdu()) {
            if (u instanceof Membre) {
                Membre m = (Membre) u;
                m.incrementPerdu("Pendu");
            }
            JOptionPane.showMessageDialog(null, "Vous avez perdu ... Le mot à deviner était " + this.getMot());
            quitter();
        } else if (gagne()) {
            if (u instanceof Membre) {
                Membre m = (Membre) u;
                m.incrementGagne("Pendu");
            }
            JOptionPane.showMessageDialog(null, "Vous avez Gagné !!!");
            quitter();
        }
    }

    public int nbErreurs() {
        if (this.lettresMauvaises.isEmpty()) {
            return 0;
        } else {
            return lettresMauvaises.size(); //le nombre d'erreurs est egale au nombre de lettre saisi qui n'appartiennent pas au mot a deviner
        }
    }

    public String derniereLettreMauvaises() {
        if (lettresMauvaises.isEmpty()) {
            return null;
        } else {
            String lettre = "";
            if (!(lettresMauvaises.size() == 0)) {
                lettre += lettresMauvaises.get(lettresMauvaises.size() - 1);
            }
            return lettre;
        } 
    }

    public String getValue() {
        String mot = "";
        for (char c : this.value) {
            mot += c;
        }
        return mot;
    }

    public String getMot() {
        String mot = "";
        for (char c : this.mot) {
            mot += c;
        }
        return mot;
    }

    @Override
    public void quitter(){}

    public static String description() {
        return "Règles du jeu \n"
                + "Premièrement il ne faut surtout pas que ça dépasse du cadre, sinon ça vas être super relou de le recadrer";
    }
}
