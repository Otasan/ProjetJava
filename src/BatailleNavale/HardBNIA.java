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

//Source de l'algorithme http://datagenetics.com/blog/december32011/index.html
public class HardBNIA extends BNIA{

    public HardBNIA(GrilleBN adv, GrilleBN j) {
        super(adv, j);
    }
    
    /**
     * place les bateaux
     */
    @Override
    public void placerBateaux() {
        
    }

    /**
     * tire sur la case ou la probabilité de trouver un bateau est la plus élevée
     * @return 
     */
    @Override
    public CaseBN tirer() {
    }
    
}
