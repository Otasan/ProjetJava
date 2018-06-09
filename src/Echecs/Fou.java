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
public class Fou extends Piece{

    public Fou(int x,int y,Couleur co){
        super(x,y,co);
    }
    
    public Fou(Position p, Couleur co) {
        super(p, co);
    }

    @Override
    public ArrayList<Position> deplacementPossible() {
        ArrayList<Position> res = new ArrayList();
        int i = 1;
        while(pos.x+i<8&&pos.y+i<8){
            res.add(new Position(pos.x+i,pos.y+i));
        }
        i = 1;
        while(pos.x+i<8&&pos.y-i>=0){
            res.add(new Position(pos.x+i,pos.y-i));
        }
        i = 1;
        while(pos.x-i>=0&&pos.y+i<8){
            res.add(new Position(pos.x-i,pos.y+i));
        }
        i = 1;
        while(pos.x-i>=0&&pos.y-i>=0){
            res.add(new Position(pos.x-i,pos.y-i));
        }
        res = dansLaGrille(res);
        return res;
    }
    
}
