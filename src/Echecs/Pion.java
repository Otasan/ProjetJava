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
public class Pion extends Piece{
    public Pion(int x,int y,Couleur co){
        super(x,y,co);
    }
    public Pion(Position p,Couleur co){
        super(p,co);
    }

    @Override
    public ArrayList<Position> deplacementPossible() {
        ArrayList<Position> res = new ArrayList();
        switch (c) {
            case blanc:
                res.add(new Position(pos.x+1, pos.y));
                break;
            case noir:
                res.add(new Position(pos.x-1, pos.y));
                break;
        }
        res=dansLaGrille(res);
        return res;
    }
    
    @Override
    public ArrayList<Position> coupSpecial(){
        ArrayList<Position> res = new ArrayList();
        if(!bouge){
            switch(c){
                case blanc:
                    res.add(new Position(pos.x+2,pos.y));
                    break;
                case noir:
                    res.add(new Position(pos.x-2,pos.y));
                    break;
            }
        }
        return res;
    }
    
    @Override
    public ArrayList<Position> mangerPossible(){
        ArrayList<Position> res = new ArrayList();
        switch (c) {
            case blanc:
                res.add(new Position(pos.x+1, pos.y+1));
                res.add(new Position(pos.x+1, pos.y-1));
                break;
            case noir:
                res.add(new Position(pos.x-1, pos.y+1));
                res.add(new Position(pos.x-1, pos.y-1));
                break;
        }
        res=dansLaGrille(res);
        return res;
    }
}
