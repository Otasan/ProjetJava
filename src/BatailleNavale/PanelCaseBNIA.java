/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author aabdo
 */
public class PanelCaseBNIA extends PanelCaseBN{

    public PanelCaseBNIA(CaseBN c){
        super(c);
        try{
            if(caseBN.getCase()==TypeCase.bateau){
                image.setImage(ImageIO.read(getClass().getResourceAsStream("/BatailleNavaleIMG/vierge.png")));
            }
            else{
                image.setImage(ImageIO.read(getClass().getResourceAsStream("/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png")));
            }
        }
        catch(IOException e){
            Graphics g = this.getGraphics();
            if(caseBN.getCase()==TypeCase.bateau){
                JOptionPane.showMessageDialog(this, "L'image : '/BatailleNavaleIMG/vierge.png' est introuvable", "Erreur image", JOptionPane.INFORMATION_MESSAGE);
                g.setColor(Color.blue);
            }
            else{
                JOptionPane.showMessageDialog(this, "L'image : '/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png' est introuvable", "Erreur image", JOptionPane.INFORMATION_MESSAGE);
                switch(caseBN.getCase()){
                    case vierge:
                        g.setColor(Color.blue);
                        break;
                    case toucheVierge:
                        g.setColor(Color.green);
                        break;
                    case touche:
                        g.setColor(Color.red);
                        break;
                }
            }
            g.fillRect(0, 0, 20, 20);
            image.paintIcon(this.getComponent(0), g, 0, 0);
        }
        this.setIcon(image);
    }

    /**
     * mets à jour l'image de la case
     * affiche les bateaux comme des cases vierges
     */
    @Override
    public void updateImage(){
        int lo=this.getSize().height;
        try{
            if(caseBN.getCase()==TypeCase.bateau){
                image.setImage(ImageIO.read(getClass().getResourceAsStream("/BatailleNavaleIMG/vierge.png")));
            }
            else{
                image.setImage(ImageIO.read(getClass().getResourceAsStream("/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png")));
            }
        }
        catch(Exception e){
            Graphics g = this.getGraphics();
            if(caseBN.getCase()==TypeCase.bateau){
                JOptionPane.showMessageDialog(this, "L'image : '/BatailleNavaleIMG/vierge.png' est introuvable", "Erreur image", JOptionPane.INFORMATION_MESSAGE);
                g.setColor(Color.blue);
            }
            else{
                JOptionPane.showMessageDialog(this, "L'image : '/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png' est introuvable", "Erreur image", JOptionPane.INFORMATION_MESSAGE);
                switch(caseBN.getCase()){
                    case vierge:
                        g.setColor(Color.blue);
                        break;
                    case toucheVierge:
                        g.setColor(Color.green);
                        break;
                    case touche:
                        g.setColor(Color.red);
                        break;
                }
            }
            g.fillRect(0, 0, 20, 20);
            image.paintIcon(this.getComponent(0), g, 0, 0);
        }
        this.setIcon(image);
        redimensionner(lo);
        this.setVisible(true);
        this.repaint();
    }
    
    /**
     * 
     * @return "(x,y),TypeCase Case IA"
     */
    @Override
    public String toString(){
        return super.toString()+"Case IA";
    }
}
