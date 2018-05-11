/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.Random;
/**
 *
 * @author Utilisateur
 */
public class EasyBNIA extends BNIA{

    public EasyBNIA(GrilleBN adv, GrilleBN j) {
        super(adv, j);
    }

    /**
     * essaie de placer les bateaux au hasard (sans toucher les bords de la grille) jusqu'à ce que tous soient placés.
     */
    @Override
    public void placerBateaux() {
        Random rand = new Random();
        Direction dir=Direction.vertical;
        int x=0,y=0;
        int i=0;
        String bateau="";
        while(i<5){
            switch(i){
                case 0:
                    bateau="porteavion";
                    if(rand.nextBoolean()){
                        dir=Direction.vertical;
                        x=rand.nextInt(10);
                        y=rand.nextInt(6);
                    }
                    else{
                        dir=Direction.horizontal;
                        y=rand.nextInt(10);
                        x=rand.nextInt(6);
                    }
                    break;
                case 1:
                    bateau="croiseur";
                    if(rand.nextBoolean()){
                        dir=Direction.vertical;
                        x=rand.nextInt(10);
                        y=rand.nextInt(7);
                    }
                    else{
                        dir=Direction.horizontal;
                        y=rand.nextInt(10);
                        x=rand.nextInt(7);
                    }
                    break;
                case 2:
                    bateau="sousmarin";
                    if(rand.nextBoolean()){
                        dir=Direction.vertical;
                        x=rand.nextInt(10);
                        y=rand.nextInt(8);
                    }
                    else{
                        dir=Direction.horizontal;
                        y=rand.nextInt(10);
                        x=rand.nextInt(8);
                    }
                    break;
                case 3:
                    bateau="contretorpilleur";
                    if(rand.nextBoolean()){
                        dir=Direction.vertical;
                        x=rand.nextInt(10);
                        y=rand.nextInt(8);
                    }
                    else{
                        dir=Direction.horizontal;
                        y=rand.nextInt(10);
                        x=rand.nextInt(8);
                    }
                    break;
                case 4:
                    bateau="torpilleur";
                    if(rand.nextBoolean()){
                        dir=Direction.vertical;
                        x=rand.nextInt(10);
                        y=rand.nextInt(9);
                    }
                    else{
                        dir=Direction.horizontal;
                        y=rand.nextInt(10);
                        x=rand.nextInt(9);
                    }
                    break;
            }
            try{
                ia.placerBateau(bateau, new CaseBN(x,y), dir);
            }
            catch(BNException e){
                i--;
            }
            i++;
        }
    }

    /**
     * tire sur une case au hasard. si cette case était déja touchée, recommence avec une nouvelle case au hasard
     */
    @Override
    public void tirer() {
        Random rand=new Random();
        int x=rand.nextInt(10),y=rand.nextInt(10);
        boolean aTire = false;
        while(!aTire){
            aTire=true;
            try{
                joueur.tire(x, y);
            }
            catch(BNException e){
                aTire=false;
            }
        }
    }
    
}
