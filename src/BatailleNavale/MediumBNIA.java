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
public class MediumBNIA extends BNIA {
    private boolean bateauTrouve;
    private Direction direct;
    private int deplacement;
    private CaseBN caseTrouvee;
    private int nbBateauRestant;
    
    public MediumBNIA(GrilleBN adv, GrilleBN j) {
        super(adv, j);
        bateauTrouve=false;
        direct=null;
        deplacement=0;
        caseTrouvee=null;
        nbBateauRestant=joueur.nbBateauRestant();
    }

    /**
     * essaie de placer les bateaux au hasard (sans toucher les bords de la grille) jusqu'à ce que tous soient placés.
     */
    @Override
    public void placerBateaux() {
        Random rand = new Random();
        Direction dir=Direction.vertical;
        int x=0,y=0;
        int i=0, c=0;
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
            c++;
        }
        System.out.println("Effectué en "+c+" tours");
    }

    @Override
    public void tirer() {
        if(bateauTrouve){
            if(deplacement==0){
                
            }
        }
        else{
            Random rand=new Random();
            int x=0,y=0;
            boolean aTire = false;
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
            System.out.println(joueur.getCase(x, y).toString());
            if(joueur.getCase(x, y).getCase()==TypeCase.touche){
                caseTrouvee=joueur.getCase(x,y);
                deplacement=0;
                bateauTrouve=true;
            }
        }
    }
    
}
