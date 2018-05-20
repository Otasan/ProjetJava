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
    public CaseBN tirer() {
        CaseBN res = null;
        nbBateauRestant=joueur.nbBateauRestant();
        if(bateauTrouve){
            switch(direct){
                case nord:
                    try{
                        joueur.tire(caseTrouvee.getX(), caseTrouvee.getY()-1);
                        res=joueur.getCase(caseTrouvee.getX()+deplacement, caseTrouvee.getY());
                        if(joueur.getCase(caseTrouvee.getX(), caseTrouvee.getY()-1).getCase()==TypeCase.touche){
                            direct=Direction.vertical;
                            deplacement=-1;
                        }
                        else{
                            direct=Direction.ouest;
                        }
                    }
                    catch(BNException e){
                        direct=Direction.ouest;
                        res=tirer();
                    }
                    break;
                case ouest:
                    try{
                        joueur.tire(caseTrouvee.getX()-1, caseTrouvee.getY());
                        res=joueur.getCase(caseTrouvee.getX()-1, caseTrouvee.getY());
                        if(joueur.getCase(caseTrouvee.getX()-1, caseTrouvee.getY()).getCase()==TypeCase.touche){
                            direct=Direction.horizontal;
                            deplacement=-1;
                        }
                        else{
                            direct=Direction.sud;
                        }
                    }
                    catch(BNException e){
                        direct=Direction.sud;
                        res=tirer();
                    }
                    break;
                case sud:
                    try{
                        joueur.tire(caseTrouvee.getX(), caseTrouvee.getY()+1);
                        res=joueur.getCase(caseTrouvee.getX(), caseTrouvee.getY()+1);
                        if(joueur.getCase(caseTrouvee.getX(), caseTrouvee.getY()+1).getCase()==TypeCase.touche){
                            direct=Direction.vertical;
                            deplacement=1;
                        }
                        else{
                            direct=Direction.est;
                        }
                    }
                    catch(BNException e){
                        direct=Direction.est;
                        res=tirer();
                    }
                    break;
                case est:
                    try{
                        joueur.tire(caseTrouvee.getX()+1, caseTrouvee.getY());
                        res=joueur.getCase(caseTrouvee.getX()+1, caseTrouvee.getY());
                        if(joueur.getCase(caseTrouvee.getX()+1, caseTrouvee.getY()).getCase()==TypeCase.touche){
                            direct=Direction.horizontal;
                            deplacement=1;
                        }
                        else{
                            bateauTrouve=false;
                        }
                    }
                    catch(BNException e){
                        bateauTrouve=false;
                        res=tirer();
                    }
                    break;
                default:
                    res=tireTrouve();
                    break;
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
            res = joueur.getCase(x, y);
            if(joueur.getCase(x, y).getCase()==TypeCase.touche){
                caseTrouvee=joueur.getCase(x,y);
                deplacement=0;
                direct = Direction.nord;
                bateauTrouve=true;
            }
        }
        return res;
    }
    
    private CaseBN tireTrouve(){
        CaseBN res=null;
        switch(direct){
            case vertical:
                if(deplacement<0){
                    deplacement--;
                }
                else{
                    deplacement++;
                }
                try{
                    joueur.tire(caseTrouvee.getX(), caseTrouvee.getY()+deplacement);
                    res=joueur.getCase(caseTrouvee.getX(),caseTrouvee.getY()+deplacement);
                }
                catch(BNException e){
                    if(deplacement<0){
                        deplacement=0;
                        res=tireTrouve();
                    }
                    else{
                        direct=Direction.est;
                        res=tirer();
                    }
                }
                if(joueur.getCase(caseTrouvee.getX(), caseTrouvee.getY()+deplacement).getCase()==TypeCase.touche){
                    if(nbBateauRestant!=joueur.nbBateauRestant()){
                        bateauTrouve=false;
                    }
                }
                else{
                    if(deplacement<0){
                        deplacement=0;
                    }
                    else{
                        direct=Direction.est;
                    }
                }
                break;
            case horizontal:
                if(deplacement<0){
                    deplacement--;
                }
                else{
                    deplacement++;
                }
                try{
                    joueur.tire(caseTrouvee.getX()+deplacement, caseTrouvee.getY());
                    res=joueur.getCase(caseTrouvee.getX()+deplacement, caseTrouvee.getY());
                }
                catch(BNException e){
                    if(deplacement<0){
                        deplacement=0;
                        res=tireTrouve();
                    }
                    else{
                        direct=Direction.sud;
                        res=tirer();
                    }
                }
                if(joueur.getCase(caseTrouvee.getX()+deplacement, caseTrouvee.getY()).getCase()==TypeCase.touche){
                    if(nbBateauRestant!=joueur.nbBateauRestant()){
                        bateauTrouve=false;
                    }
                }
                else{
                    if(deplacement<0){
                        deplacement=0;
                    }
                    else{
                        direct=Direction.sud;
                    }
                }
                break;
            default:
                bateauTrouve=false;
                res=tirer();
                break;
        }
        return res;
    }
    
}
