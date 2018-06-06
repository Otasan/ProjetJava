/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.Random;
/**
 *
 * @author aabdo
 */
public class EasyBNIA extends BNIA{

    public EasyBNIA(GrilleBN adv, GrilleBN j) {
        super(adv, j);
    }

    /**
     * essaie de placer les bateaux au hasard (sans toucher les bords de la grille) jusqu'a ce que tous soient places.
     */
    @Override
    public void placerBateaux() {
        Random rand = new Random();
        Direction dir=Direction.vertical;
        int x=0,y=0;
        int i=0, c=0;
        String bateau="";
        //place tout les bateaux a tour de rôle
        while(i<5){
            //essaie de placer un bateau a des positions possibles
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
            c++;
        }
    }

    /**
     * tire sur une case au hasard. si cette case etait deja touchee, recommence avec une nouvelle case au hasard
     * @return La case sur laquelle l'IA a tire
     */
    @Override
    public CaseBN tirer() {
        Random rand=new Random();
        int x=0,y=0;
        boolean aTire = false;
        //tire au hasard
        while(!aTire){
            x=rand.nextInt(10);
            y=rand.nextInt(10);
            aTire=true;
            try{
                joueur.tire(x, y);
            }
            catch(BNException e){
                aTire=false;
            }
        }
        return joueur.getCase(x,y);
    }
    
}
