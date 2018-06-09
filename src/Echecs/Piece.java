/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echecs;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public abstract class Piece {
    protected Position pos;
    protected Couleur c;
    protected boolean bouge;
    
    public Piece(int x, int y,Couleur co){
        pos.x=x;
        pos.y=y;
        c=co;
        bouge=false;
    }
    
    public Piece(Position p,Couleur co){
        pos = new Position(p);
        c=co;
        bouge=false;
    }
    
    public Position getPos(){
        return pos;
    }
    
    public ArrayList<Position> mangerPossible(){
        return deplacementPossible();
    }
    
    public ArrayList<Position> dansLaGrille(ArrayList<Position> a){
        for(Position p:a){
            if(p.x>=8||p.y>=8||p.x<0||p.y<0){
                a.remove(p);
            }
        }
        return a;
    }
    
    public void deplacer(Position p) throws EchecsException{
        ArrayList<Position> possible=new ArrayList(deplacementPossible());
        possible.addAll(mangerPossible());
        if(possible.contains(p)){
            pos=new Position(p);
        }
        else{
            throw(new EchecsException("Déplacement Impossible"));
        }
    }
    
    public boolean aBouge(){
        return bouge;
    }
    
    public abstract ArrayList<Position> deplacementPossible();
}
