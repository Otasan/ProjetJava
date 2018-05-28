/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Utilisateur
 */

//Source de l'algorithme http://datagenetics.com/blog/december32011/index.html
public class HardBNIA extends BNIA{
    private TreeMap<CaseBN,Integer> proba;

    public HardBNIA(GrilleBN adv, GrilleBN j) {
        super(adv, j);
        proba=new TreeMap();
        proba = ProbaTorpilleur();
    }

    /**
     * créé la carte de probabilité du placement d'un bateau sur une case
     */
    private TreeMap<CaseBN,Integer> ProbaTorpilleur(){
        TreeMap<CaseBN,Integer> res = new TreeMap();
        
        return res;
    }
    
    /**
     * définie la probabilité de la case [X;Y], mets 0 par defaut si négatif
     * @param x
     * @param y
     * @param val 
     */
    private void setProba(int x, int y, int val){
        if(val<0){
            proba.put(joueur.getCase(x, y), 0);
        }
        else{
            proba.put(joueur.getCase(x, y), val);
        }
    }
    
    /**
     * définie la probabilité de la case, mets 0 par defaut si négatif
     * @param c
     * @param val 
     */
    private void setProba(CaseBN c, int val){
        if(val<0){
            proba.put(joueur.getCase(c),0);
        }
        else{
            proba.put(joueur.getCase(c), val);
        }
    }
    
    /**
     * recursion pour calculer la probabilité d'avoir un torpilleur sur une case
     * @param carre
     * @return 
     */
    private TreeMap<CaseBN,Integer> recurTorpilleur(TreeMap<CaseBN,Integer> carre){
        if(carre.size()==1){
            if(carre.firstKey().getCase()==TypeCase.toucheVierge)
                carre.put(carre.firstKey(), 0);
            else
                carre.put(carre.firstKey(), 4);
        }
        else{
            TreeMap<CaseBN,Integer> hg = new TreeMap();
            for(int y=carre.firstKey().getY();y<Math.sqrt((double) carre.size())-1;y++){
                for(int x=carre.firstKey().getX();x<Math.sqrt((double) carre.size())-1;x++){
                    hg.put(carre.ceilingKey(new CaseBN(x,y)), 0);
                }
            }
            TreeMap<CaseBN,Integer> hd = new TreeMap();
            for(int y=carre.firstKey().getY();y<Math.sqrt((double) carre.size())-1;y++){
                for(int x=carre.firstKey().getX()+1;x<Math.sqrt((double) carre.size());x++){
                    hg.put(carre.ceilingKey(new CaseBN(x,y)), 0);
                }
            }
            TreeMap<CaseBN,Integer> bg = new TreeMap();
            for(int y=carre.firstKey().getY()+1;y<Math.sqrt((double) carre.size());y++){
                for(int x=carre.firstKey().getX();x<Math.sqrt((double) carre.size())-1;x++){
                    hg.put(carre.ceilingKey(new CaseBN(x,y)), 0);
                }
            }
            TreeMap<CaseBN,Integer> bd = new TreeMap();
            for(int y=carre.firstKey().getY()+1;y<Math.sqrt((double) carre.size());y++){
                for(int x=carre.firstKey().getX()+1;x<Math.sqrt((double) carre.size());x++){
                    hg.put(carre.ceilingKey(new CaseBN(x,y)), 0);
                }
            }
            hg=recurTorpilleur(hg);
            hd=recurTorpilleur(hd);
            bg=recurTorpilleur(bg);
            bd=recurTorpilleur(bd);
            
        }
        return carre;
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
