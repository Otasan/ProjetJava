/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.TreeSet;

/**
 *
 * @author Utilisateur
 */
public abstract class Bateau {
    private CaseBN caseInitiale;
    private Direction sens;
    private int taille;
    //private boolean coule;
    
    /**
     * crée un bateau de case Initiale cI, direction s et taille t
     * @param cI la case la plus en haut ou à gauche du bateau
     * @param s la direction du bateau(horizontale ou verticale)
     * @param t la taille du bateau en case
     */
    public Bateau(CaseBN cI, Direction s, int t){
        cI.setCase(TypeCase.bateau);
        caseInitiale=cI;
        sens=s;
        taille=t;
        //coule=false;
    }
    
    /**
     * renvoie la taille du bateau en case
     * @return taille
     */
    public int getTaille(){
        return taille;
    }
    
    /**
     * renvoie un string de la forme:
     * [classe] case initiale: [toString de case Initiale] taille : [taille] sens :[sens]
     * @return 
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName()+" case initiale: "+caseInitiale.toString()+" taille :"+taille+" sens :"+sens;
    }
    
    /**
     * renvoie la case la plus en haut ou à gauche du bateau
     * @return caseInitiale
     */
    public CaseBN getCaseInitiale(){
        return caseInitiale;
    }
    
    /**
     * renvoie le sens du bateau
     * @return sens
     */
    public Direction getSens(){
        return sens;
    }
    
    /**
     * Verifie si 2 bateaux sont identiques.
     * puisqu'il n'y aura jamais 2 bateaux du même type, cette fonction compare juste les noms de classe des bateaux.
     * @param b
     * @return true si les objets sont identiques, false sinon
     */
    @Override
    public boolean equals(Object b){
        return this.getClass().getSimpleName().equals(((Bateau)b).getClass().getSimpleName());
    }
    
    /**
     * Génère un hash d'un bateau à partir du nom de la classe
     * @return hashCode
     */
    @Override
    public int hashCode(){
        return this.getClass().getSimpleName().hashCode();
    }
}
