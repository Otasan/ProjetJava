/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import java.io.Serializable;

/**
 * Utilisateur ne possedant pas de compte ("Invite")
 *
 * @author edeux
 */
public class Utilisateur implements Serializable {

    private String pseudo;

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public String toString() {
        return pseudo;
    }

    /**
     * Teste si le pseudo est null et s'il contient entre 5 et 15 characteres
     * classiques ([a-z][A-Z][0-9]).
     *
     * @param pseudo Pseudo a tester pour savoir s'il est conforme.
     * @return true si le pseudo est conforme aux tests.
     */
    public static boolean estPseudoValide(String pseudo) {
        if (pseudo == null || pseudo.equals("") || pseudo.length() < 5 || pseudo.length() > 15) {
            return false;
        } else {
            return pseudo.matches("[[a-z][A-Z][0-9]]*");
        }
    }

    public boolean equals(Utilisateur u) {
        if (this.pseudo.equals(u.pseudo)) {
            return true;
        } else {
            return false;
        }
    }
}
