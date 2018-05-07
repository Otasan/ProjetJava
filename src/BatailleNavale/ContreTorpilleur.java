/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

/**
 *
 * @author Utilisateur
 */
public class ContreTorpilleur extends Bateau{
    
    /**
     * crée un bateau de taille 3, c'est à dire un Contre Torpilleur
     * @param cI la case la plus en haut à gauche du bateau
     * @param s la direction (horizontale ou verticale)
     */
    public ContreTorpilleur(CaseBatailleNavale cI, Direction s) {
        super(cI, s, 3);
    }
    
    /**
     * donne la taille, constante 3 pour ce bateau
     * @return taille
     */
    @Override
    public int getTaille(){
        return 3;
    }
}
