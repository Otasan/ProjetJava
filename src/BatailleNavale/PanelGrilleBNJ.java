/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Utilisateur
 */
public class PanelGrilleBNJ extends PanelGrilleBN {
    private FrameChoixBateau choix;
    
    
    public PanelGrilleBNJ(GrilleBN g) throws Exception {
        super(g);
        createGrille();
        choix = new FrameChoixBateau();
        etat=EtatsGrilleBN.placerBateau;
    }
    
    /**
     * génère la grille qà être affichée. utile uniquement dans le constructeur
     * @throws IOException 
     */
    @Override
    public void createGrille() throws IOException{
        TreeSet g = grille.getGrille();
        Iterator it = g.iterator();
        int i=0;
        while(it.hasNext()){
            grilleB.add(new PanelCaseBNJ((CaseBatailleNavale)it.next(), grille.getBateaux()));
            add(grilleB.get(i));
            PanelCaseBN caseB=grilleB.get(i);
            grilleB.get(i).addActionListener(event->caseClick(caseB));
            i++;
        }
    }
    
    /**
     * méthode appelée lorsque l'utilisateaur clique sur un bateau.
     * Si l'utilisateur doit placer des bateaux, alors il pourra.
     * @param caseP 
     */
    @Override
    public void caseClick(PanelCaseBN caseP){
        //System.out.println(caseP);
        if(etat==EtatsGrilleBN.placerBateau){
            if(choix.getValide()){
                try{
                    grille.placerBateau(choix.getBateau(),caseP.getCase(),choix.getSens());
                    updateGrille();
                }
                catch(Exception e){
                    System.out.println(e+" TODO gerer les exceptions caseClick");
                }
            }
            if(grille.getBateaux().size()<5){
                choix.nouveauBateau(grille.getBateaux());
            }
            else{
                choix.dispatchEvent(new WindowEvent(choix, WindowEvent.WINDOW_CLOSING));
                etat=EtatsGrilleBN.tour;
            }
        }
        //TODO: ajouter fenetre erreur sinon
    }
}
