/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Utilisateur
 */
public class PanelCaseBNIA extends PanelCaseBN{

    public PanelCaseBNIA(CaseBN c) throws IOException {
        super(c);
        if(caseBN.getCase()==TypeCase.bateau){
            image.setImage(ImageIO.read(new File("BatailleNavaleIMG\\vierge.png")));
        }
        else{
            image.setImage(ImageIO.read(new File("BatailleNavaleIMG\\"+caseBN.getCase().toString()+".png")));
        }
        this.setIcon(image);
    }

    /**
     * mets à jour l'image de la case
     * @throws IOException 
     */
    @Override
    public void updateImage() throws IOException{
        int lo=this.getSize().height;
        if(caseBN.getCase()==TypeCase.bateau){
            image.setImage(ImageIO.read(new File("BatailleNavaleIMG\\vierge.png")));
        }
        else{
            image.setImage(ImageIO.read(new File("BatailleNavaleIMG\\"+caseBN.getCase().toString()+".png")));
        }
        this.setIcon(image);
        redimensionner(lo);
        this.setVisible(true);
        this.repaint();
    }
    
    @Override
    public String toString(){
        return super.toString()+"Case IA";
    }
}
