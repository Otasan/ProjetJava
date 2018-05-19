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
public class PorteAvion extends Bateau{
    
    /**
     * crée un bateau de taille 5, c'est à dire un porte avion
     * @param cI la case la plus en haut à gauche du bateau
     * @param s la direction (horizontale ou verticale)
     */
    public PorteAvion(CaseBN cI, Direction s) {
        super(cI, s, 5);
    }
    
    /**
     * donne la taille, constante 5 pour ce bateau
     * @return taille
     */
    @Override
    public int getTaille(){
        return 5;
    }
}
