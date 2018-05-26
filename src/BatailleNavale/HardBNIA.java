/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.TreeMap;

/**
 *
 * @author Utilisateur
 */

//Source de l'algorithme http://datagenetics.com/blog/december32011/index.html
public class HardBNIA extends BNIA{
    private TreeMap<CaseBN,Double> proba;

    public HardBNIA(GrilleBN adv, GrilleBN j) {
        super(adv, j);
        proba=new TreeMap();
        createProba();
    }

    /**
     * créé la carte de probabilité du placement d'un bateau sur une case
     */
    private void createProba(){
        int[] porteAvion={1,2,3,4,5,5,4,3,2,1};
        int[] croiseur={1,2,3,4,4,4,4,3,2,1};
        int[] sousContre={2,4,6,6,6,6,6,6,4,2};
        int[] torpilleur={1,2,2,2,2,2,2,2,2,1};
        for(int y=0;y<10;y++){
            for(int x=0;x<10;x++){
                double val=(porteAvion[x]+porteAvion[y]+croiseur[x]+croiseur[y]+sousContre[x]+sousContre[y]+torpilleur[x]+torpilleur[y])/500;
                setProba(x,y,val);
            }
        }
    }
    
    private void updateProba(CaseBN c){
        
    }
    
    /**
     * définie la probabilité de la case [X;Y], mets 0 par defaut si négatif
     * @param x
     * @param y
     * @param val 
     */
    private void setProba(int x, int y, Double val){
        if(val<0){
            proba.put(new CaseBN(x,y), 0.0);
        }
        else{
            proba.put(new CaseBN(x,y), val);
        }
    }
    
    /**
     * place les bateaux
     */
    @Override
    public void placerBateaux() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * tire sur la case ou la probabilité de trouver un bateau est la plus élevée
     * @return 
     */
    @Override
    public CaseBN tirer() {
        double max=0;
        int nb=joueur.nbBateauRestant();
        CaseBN caseProbable=null;
        for(CaseBN c:proba.keySet()){
            if(proba.get(c)>max){
                max=proba.get(c);
                caseProbable=c;
            }
        }
        try{
            joueur.tire(caseProbable);
            updateProba(caseProbable);
            if(nb!=joueur.nbBateauRestant()){
                //TODO recalculer la totalité des probabilités sans le bateau tombé
            }
        }
        catch(BNException e){
            System.out.println(e);
            proba.put(caseProbable,0.0);
            caseProbable = tirer();
        }
        return caseProbable;
    }
    
}
