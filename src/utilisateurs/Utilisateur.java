/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

import java.io.Serializable;

/**
 *
 * @author Dobby
 */
public class Utilisateur implements Serializable {

    private String pseudo;

    public Utilisateur(String pseudo) throws ConnexionException {
        if (Utilisateur.estPseudoValide(pseudo)) {
            this.pseudo = pseudo;
        } else {
            throw new ConnexionException();
        }
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ' ' + pseudo;
    }

    public static boolean estPseudoValide(String pseudo) {
        if (pseudo==null){
            return false;
        }
        int length = pseudo.length();
        if (pseudo.equals("") || length < 6 || length > 30) {
            return false;
        } else {
            return pseudo.matches("[[a-z][A-Z][0-9]]*");
        }
    }

    public boolean equals(Utilisateur u){
        if(this.pseudo.equals(u.pseudo)){
            return true;
        } else {
            return false;
        }
    }
}
