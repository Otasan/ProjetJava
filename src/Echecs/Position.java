/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echecs;

/**
 *
 * @author Utilisateur
 */
public class Position implements Comparable {
    public int x;
    public int y;
    
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Position(Position p){
        x=p.x;
        y=p.y;
    }
    
    @Override
    public boolean equals(Object o){
        Position p=(Position)o;
        return(p.x==x&&p.y==y);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        Position p=(Position)o;
        if(y==p.y){
            return x-p.x;
        }
        else{
            return y-p.y;
        }
    }
}
