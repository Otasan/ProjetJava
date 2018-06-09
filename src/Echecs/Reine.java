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
public class Reine extends Piece{

    public Reine(int x, int y, Couleur co) {
        super(x, y, co);
    }

    public Reine(Position p, Couleur co) {
        super(p, co);
    }
    
    @Override
    public ArrayList<Position> deplacementPossible() {
        ArrayList<Position> res = new ArrayList();
        for(int i=0;i<8;i++){
            if(pos.x!=i){
                res.add(new Position(pos.x,i));
            }
            if(pos.y!=i){
                res.add(new Position(i,pos.y));
            }
        }
        res = dansLaGrille(res);
        return res;
    }
    
}
