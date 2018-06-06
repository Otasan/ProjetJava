/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

/**
 *
 * @author aabdo
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
     * change l'etat de la partie
     * @param state 
     */
    public void setTour(EtatsBN state){
        etat=state;
    }
    
    /**
     * demande a l'IA de placer les bateaux
     */
    abstract public void placerBateaux();
    
    /**
     * demande a l'IA de tirer sur une case
     * @return la case sur laquelle l'IA a tiree
     */
    abstract public CaseBN tirer();
}
