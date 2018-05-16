package BatailleNavale;

import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Utilisateur
 */
public class PanelCaseBNJ extends PanelCaseBN {
    private HashSet<Bateau> lesBateaux;
    
    public PanelCaseBNJ(CaseBN c, HashSet<Bateau> b) throws IOException {
        super(c);
        lesBateaux=b;
        //image.setImage(ImageIO.read(new File("BatailleNavaleIMG\\"+caseBN.getCase().toString()+".png")));
        //image.setImage(ImageIO.read(getClass().getResource("BatailleNavaleIMG/"+caseBN.getCase().toString()+".png")));
        image.setImage(ImageIO.read(getClass().getResourceAsStream("/BatailleNavaleIMG/"+caseBN.getCase().toString()+".png")));
        this.setIcon(image);
    }
    
    /**
     * mets à jour l'image de la case
     * @throws IOException 
     */
    @Override
    public void updateImage() throws IOException{
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
        //System.out.println(nomFic);
        //image.setImage(ImageIO.read(new File(nomFic)));
        image.setImage(ImageIO.read(getClass().getResourceAsStream(nomFic)));
        this.setIcon(image);
        redimensionner(lo);
        this.setVisible(true);
        this.repaint();
    }
    
    @Override
    public String toString(){
        return super.toString()+"Case Joueur";
    }
}
