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
public class Cavalier extends Piece{
    
    public Cavalier(int x,int y,Couleur co) {
        super(x,y, co);
    }

    public Cavalier(Position p, Couleur co) {
        super(p, co);
    }

    @Override
    public ArrayList<Position> deplacementPossible() {
        ArrayList<Position> res = new ArrayList();
        res.add(new Position(pos.x+3,pos.y+1));
        res.add(new Position(pos.x+3,pos.y-1));
        res.add(new Position(pos.x-3,pos.y+1));
        res.add(new Position(pos.x-3,pos.y-1));
        res.add(new Position(pos.x+1,pos.y+3));
        res.add(new Position(pos.x+1,pos.y-3));
        res.add(new Position(pos.x-1,pos.y+3));
        res.add(new Position(pos.x-1,pos.y-3));
        res=dansLaGrille(res);
        return res;
    }
    
}
