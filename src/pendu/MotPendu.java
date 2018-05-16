/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KREATURE
 */
public class MotPendu {

    private ArrayList<String> mot;
    private ArrayList<String> value;
    private ArrayList<String> lettres;

    public MotPendu() {
        mot = new ArrayList<>();
        value = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un mot à faire deviner :");
        String str = sc.nextLine();
        for (int i = 0; i < str.length(); i++) {
            mot.add(str.substring(i, i + 1));
        }
        int i = 0;
        for (String s : mot) {
            value.add("_");
            //System.out.print(value.get(i));
            i += 1;
        }
    }

    public MotPendu(String mot) {
        this.mot = new ArrayList<>();
        this.value = new ArrayList<>();
        for (int i = 0; i < mot.length(); i++) {
            this.mot.add(mot.substring(i, i + 1));
        }
        int i = 0;
        for (String s : this.mot) {
            value.add("_");
            //System.out.print(value.get(i));
            i += 1;
        }
    }

    @Override
    public String toString() {
        return this.mot + " " + this.value;
    }

    public boolean lettreEstValide(String lettre) {
        lettre = lettre.toUpperCase();
        /*for (String s : lettres) {
            if (s == lettre) {
                return false;
            }
        }
        if (lettre.length() == 1) {
            if (lettre.charAt(0) >= 'A' && lettre.charAt(0) <= 'Z') {
                return true;
            }
        }*/
        return true;
    }

    public void DemarerLeJeu() {
        boolean motTrouve = false;
        int i = 0;
        lettres = new ArrayList<>();
        while (motTrouve == false) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nVeuillez saisir une lettre : ");
            String str = sc.nextLine();
            String lettreAComparer = str.substring(i, i + 1);
            lettreAComparer = lettreAComparer.toUpperCase();
            if (lettreEstValide(lettreAComparer)){
                lettres.add(lettreAComparer);
            }
            System.out.println("les lettres deja utilisées : " + lettres.toString());
            int j = 0;
            int erreur=0;
            for (String s : mot) {

                if (lettreEstValide(lettreAComparer) && s.equals(lettreAComparer)) {
                    System.out.println("good");
                    value.set(j, lettreAComparer);
                    System.out.println(value);
                    j += 1;
                    if (mot.toString().equals(value.toString())) {
                        motTrouve = true;
                        System.out.println("Vous avez gagné !");
                    }
                } else {
                    j += 1;
                    /*erreur+=1;
                    if (erreur >7){
                        System.out.println(erreur);
                        System.out.println("Vous avez perdu !");
                        System.exit(0);
                    }*/
                }
                this.toString();
            }
        }
    }
}
