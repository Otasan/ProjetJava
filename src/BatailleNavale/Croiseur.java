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
public class Croiseur extends Bateau{
    
    /**
     * crée un bateau de taille 4, c'est à dire un torpilleur
     * @param cI la case la plus en haut à gauche du bateau
     * @param s la direction (horizontale ou verticale)
     */
    public Croiseur(CaseBN cI, Direction s) {
        super(cI, s, 4);
    }
    
    /**
     * donne la taille, constante 4 pour ce bateau
     * @return 
     */
    @Override
    public int getTaille(){
        return 4;
    }
}
