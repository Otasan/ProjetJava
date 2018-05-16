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
public abstract class BNIA {
    protected GrilleBN joueur;
    protected GrilleBN ia;
    protected volatile EtatsBN etat;
    
    public BNIA(GrilleBN adv, GrilleBN j){
        joueur=j;
        ia=adv;
        etat=EtatsBN.placerBateau;
    }
    
    /**
     * change l'état de la partie
     * @param state 
     */
    public void setTour(EtatsBN state){
        etat=state;
    }
    
    /**
     * demande à l'IA de placer les bateaux
     */
    abstract public void placerBateaux();
    
    /**
     * demande à l'IA de tirer sur une case
     */
    abstract public void tirer();
}
