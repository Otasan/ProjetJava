/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author KREATURE
 */
public class JeuPendu {

    private ArrayList<Character> mot;
    private ArrayList<Character> value;
    private ArrayList<Character> lettresUtilisees;
    private ArrayList<Character> lettresMauvaises;
    private int difficulte;

    public JeuPendu(String motADeviner, int diff) {
        this.mot = new ArrayList<>();
        this.value = new ArrayList<>();
        this.lettresMauvaises = new ArrayList<>();
        this.lettresUtilisees = new ArrayList<>();
        difficulte = diff;

        char[] tableau = motADeviner.toCharArray();
        //System.out.println(tableau);
        for (int i = 0; i < motADeviner.length(); i++) {
            this.mot.add(tableau[i]);
        }
        int i = 0;
        for (char r : this.mot) {
            value.add('_');
            System.out.print(value.get(i));
            i += 1;
        }
    }

    @Override
    public String toString() {
        return this.mot + " " + this.value;
    }

    public boolean estLettreValide(char[] lettre) {
        if (lettre.length >= 2) {
            System.out.println("Veuillez ne saisir qu'une lettre à la fois.");
            return false;
        }

        for (char s : lettresUtilisees) {
            if (lettre[0] == s) {
                System.out.println("La lettre a déjà été utilisées.");
                JOptionPane.showMessageDialog(null, "La lettre a déjà été utilisées.");
                return false;
            }
        }

        if (lettre[0] >= 'A' && lettre[0] <= 'Z') {
            return true;
        } else {
            System.out.println("Le caractere saisi n'est pas valide.");
            JOptionPane.showMessageDialog(null, "Le caractere saisi n'est pas valide.");
        }
        return false;
    }

    public boolean perdu() {
        if (lettresMauvaises.size() > 7) {
            System.out.println("Vous avez perdu !\n" + "le mot a deviner était : " + mot);
            return true;
        }
        return false;
    }

    public void DemarerLeJeu() {
        boolean motTrouve = false;

        while (!motTrouve) {
            boolean lettreFausse = true;
            Scanner sc = new Scanner(System.in);
            System.out.print("\nVeuillez saisir une lettre : ");
            String str = sc.nextLine();
            str = str.toUpperCase();

            //Methode(char c){
            char[] lettreAComparer = str.toCharArray();

            int j = 0;
            if (estLettreValide(lettreAComparer)) {
                for (char c : mot) {

                    if (c == lettreAComparer[0]) {
                        lettreFausse = false;
                        System.out.println("good");
                        value.set(j, lettreAComparer[0]);
                        System.out.println(value);
                        j += 1;
                        if (mot.toString().equals(value.toString())) {
                            motTrouve = true;
                            System.out.println("Vous avez gagné !");
                        }
                    } else {
                        j += 1;

                    }
                    this.toString();
                }

                for (char c : lettreAComparer) {
                    lettresUtilisees.add(c);
                }
                if (lettreFausse == true) {
                    lettresMauvaises.add(lettreAComparer[0]);
                }
                // fin methode (char c)
                System.out.println("les lettres qui ne sont pas dans le mot à deviner : " + lettresMauvaises.toString());
                if (perdu()) {
                    break;
                }
            }
        }
    }

    public void etapeJeu(char c) {
        char[] lettre = {};
        if (estLettreValide(lettre)) {
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
        }
    }

    public int status() {
        if (value.equals(mot)) {
            return 1;
        } else if (lettresMauvaises.size() >= 11-difficulte) {
            return -1;
        } else {
            return 0;
        }
    }

    public int nbErreurs() {
        if (this.lettresMauvaises.isEmpty()) {
            return 0;
        } else {
            return lettresMauvaises.size();
        }
    }

    public String derniereLettreUtil() {
        if (lettresUtilisees.isEmpty()) {
            return null;
        } else {
            String lettre = "";
            if (!(this.lettresUtilisees.size() == 0)) {
                lettre += lettresUtilisees.get(lettresUtilisees.size() - 1);
            }
            return lettre;
        }
    }

    public String getMot() {
        String mot = "";
        for (char c : this.value) {
            mot += c;
        }
        return mot;
    }
}
