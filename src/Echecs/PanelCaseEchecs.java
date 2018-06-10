/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echecs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author Utilisateur
 */
public class PanelCaseEchecs extends JButton implements Comparable{
    private Position pos;
    
    public PanelCaseEchecs(Position p){
        pos = new Position(p);
        if(pos.estCasePaire()){
            this.setBackground(Color.BLACK);
            System.out.println("noir"+pos);
        }
        else{
            this.setBackground(Color.WHITE);
            System.out.println("blanc"+pos);
        }
        this.setPreferredSize(new Dimension(30,30));
        this.setSize(30,30);
        this.setOpaque(true);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        if(pos.estCasePaire()){
            this.setBackground(Color.BLACK);
        }
        else{
            this.setBackground(Color.WHITE);
        }
    }
    
    public Position getPos(){
        return pos;
    }

    @Override
    public int compareTo(Object o) {
        PanelCaseEchecs pC = (PanelCaseEchecs)o;
        return pos.compareTo(pC.pos);
    }
}
