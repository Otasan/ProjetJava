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
public class Equipe {
    private ArrayList<Piece> lesPieces;
    private Couleur c;
    
    public Equipe(Couleur co){
        c=co;
        lesPieces = new ArrayList();
        switch(c){
            case blanc:
                for(int i=0;i<8;i++){
                    lesPieces.add(new Pion(1,i,c));
                }
                lesPieces.add(new Tour(0,0,c));
                lesPieces.add(new Tour(0,7,c));
                lesPieces.add(new Cavalier(0,1,c));
                lesPieces.add(new Cavalier(0,6,c));
                lesPieces.add(new Fou(0,2,c));
                lesPieces.add(new Fou(0,5,c));
                lesPieces.add(new Reine(0,4,c));
                lesPieces.add(new Roi(0,3,c));
                break;
            case noir:
                for(int i=0;i<8;i++){
                    lesPieces.add(new Pion(6,i,c));
                }
                lesPieces.add(new Tour(7,0,c));
                lesPieces.add(new Tour(7,7,c));
                lesPieces.add(new Cavalier(7,1,c));
                lesPieces.add(new Cavalier(7,6,c));
                lesPieces.add(new Fou(7,2,c));
                lesPieces.add(new Fou(7,5,c));
                lesPieces.add(new Reine(7,4,c));
                lesPieces.add(new Roi(7,3,c));
                break;
        }
    }
    
    public Piece pieceA(Position pos) throws EchecsException{
        Piece pi = null;
        for(Piece p:lesPieces){
            if(pos.equals(p.getPos())){
                pi=p;
            }
        }
        if(pi!=null){
            return pi;
        }
        else{
            throw(new EchecsException("Pas de piece à cette position : "+pos));
        }
    }
    
    public void deplacer(Position pos, Position dep) throws EchecsException{
        pieceA(pos).deplacer(dep);
    }
    
    public ArrayList<Position> deplacementPossible(Position pos) throws EchecsException{
        return pieceA(pos).deplacementPossible();
    }
    
    public ArrayList<Position> mangerPossible(Position pos) throws EchecsException{
        return pieceA(pos).mangerPossible();
    }
    
    public ArrayList<Position> coupSpecialPossible(Position pos) throws EchecsException{
        return pieceA(pos).coupSpecial();
    }
    
    public ArrayList<Piece> getPieces(){
        return lesPieces;
    }
    
    public boolean aRoi(){
        boolean res = false;
        for(Piece p:lesPieces){
            if(p instanceof Roi){
                res=true;
            }
        }
        return res;
    }
    
    public Couleur getCouleur(){
        return c;
    }
}
