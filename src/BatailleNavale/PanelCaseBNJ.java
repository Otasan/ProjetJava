package BatailleNavale;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aabdo
 */
public class PanelCaseBNJ extends PanelCaseBN {
    private HashSet<Bateau> lesBateaux;
    
    public PanelCaseBNJ(CaseBN c, HashSet<Bateau> b){
        super(c);
        lesBateaux=b;
        try{
            image.setImage(ImageIO.read(getClass().getResourceAsStream("/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png")));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "L'image : '/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png' est introuvable", "Erreur image", JOptionPane.INFORMATION_MESSAGE);
            Graphics g = this.getGraphics();
            switch(caseBN.getCase()){
                case vierge:
                    g.setColor(Color.blue);
                    break;
                case toucheVierge:
                    g.setColor(Color.green);
                    break;
                case bateau:
                    g.setColor(Color.gray);
                    break;
                case touche:
                    g.setColor(Color.red);
                    break;
            }
            g.fillRect(0, 0, 20, 20);
            image.paintIcon(this.getComponent(0), g, 0, 0);
        }
        this.setIcon(image);
    }
    
    /**
     * mets à jour l'image de la case
     * affiche les bateaux
     */
    @Override
    public void updateImage(){
        int lo=this.getSize().height;
        String nomFic = "/BatailleNavaleIMG/";
        if(caseBN.getCase()!=TypeCase.bateau){
            nomFic+=caseBN.getCase().toString();
        }
        else{
            Bateau bat = null;
            int i=0;
            for(Bateau b:lesBateaux){
                switch(b.getSens()){
                    case horizontal:
                        if(b.getCaseInitiale().getY()==caseBN.getY() && b.getCaseInitiale().getX()<=caseBN.getX() && b.getCaseInitiale().getX()+b.getTaille()>caseBN.getX()){
                           bat=b;
                           i=caseBN.getX()-b.getCaseInitiale().getX();
                        }
                        break;
                    case vertical:
                        if(b.getCaseInitiale().getX()==caseBN.getX() && b.getCaseInitiale().getY()<=caseBN.getY() && b.getCaseInitiale().getY()+b.getTaille()>caseBN.getY()){
                           bat=b;
                           i=caseBN.getY()-b.getCaseInitiale().getY();
                        }
                        break;
                }
            }
            if(bat!=null){
                nomFic+=bat.getClass().getSimpleName()+"_"+bat.getSens()+"_"+i;
            }
        }
        nomFic+=".png";
        try{
            image.setImage(ImageIO.read(getClass().getResourceAsStream(nomFic)));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "L'image : '"+nomFic+"' est introuvable", "Erreur image", JOptionPane.INFORMATION_MESSAGE);
            Graphics g = this.getGraphics();
            switch(caseBN.getCase()){
                case vierge:
                    g.setColor(Color.blue);
                    break;
                case toucheVierge:
                    g.setColor(Color.green);
                    break;
                case bateau:
                    g.setColor(Color.gray);
                    break;
                case touche:
                    g.setColor(Color.red);
                    break;
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
     * @return "(x,y),TypeCase Case Joueur"
     */
    @Override
    public String toString(){
        return super.toString()+"Case Joueur";
    }
}
