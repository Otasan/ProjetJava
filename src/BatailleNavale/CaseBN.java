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
public class CaseBN implements Comparable {
    private TypeCase zone;
    private int x;
    private int y;
    
    /**
     * Crée une case de type "vierge" à la position [x,y]
     * @param x position x ([0;9] de gauche à droite)
     * @param y position y ([0;9] de haut en bas)
     */
    public CaseBN(int x, int y){
        this.x=x;
        this.y=y;
        zone=TypeCase.vierge;
    }
    
    /**
     * Change une case "vierge" en "toucheVierge" et une case "bateau" en "touche" et return le nouveau type de la case
     * lève une BNException si une case est de type "touche" ou "toucheVierge"
     * @return le nouveau type de la case
     * @throws BNException 
     */
    public TypeCase touche() throws BNException{
        if(zone != TypeCase.touche && zone != TypeCase.toucheVierge){
            switch(zone){
                case vierge:
                    zone=TypeCase.toucheVierge;
                    break;
                case bateau:
                    zone=TypeCase.touche;
                    break;
            }
            return zone;
        }
        else{
            throw new BNException("Case déjà touchée "+zone);
        }
    }
    
    /**
     * renvoie le type de la case
     * @return zone
     */
    public TypeCase getCase(){
        return zone;
    }
    
    /**
     * donne la position x ([0;9] de gauche à droite)
     * @return x
     */
    public int getX(){
        return x;
    }
    
    /**
     * donne la position y ([0;9] de haut en bas)
     * @return y
     */
    public int getY(){
        return y;
    }
    
    /**
     * change le type de la case en val
     * @param val 
     */
    public void setCase(TypeCase val){
        zone=val;
    }
    
    /**
     * compare une case à une autre, d'abord selon y puis selon x.
     * permets de ranger les cases de gauche à droite puis de haut en bas.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Object o) {
        CaseBN c = (CaseBN)o;
        if(y == c.y){
            return x-c.x;
        }
        else{
            return y-c.y;
        }
    }
    
    /**
     * Crée un string de la forme:
     * "[x,y] [zone]"
     * @return 
     */
    @Override
    public String toString(){
        return "["+x+", "+y+"] "+zone;
    }
}
