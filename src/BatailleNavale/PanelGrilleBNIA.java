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
public class PanelGrilleBNIA extends PanelGrilleBN{
    
    public PanelGrilleBNIA(GrilleBN g) throws Exception {
        super(g);
        createGrille();
        etat=EtatsGrilleBN.placerBateau;
    }
    
    @Override
    public void createGrille() throws IOException{
        TreeSet g = grille.getGrille();
        Iterator it = g.iterator();
        int i=0;
        while(it.hasNext()){
            grilleB.add(new PanelCaseBNIA((CaseBatailleNavale)it.next()));
            add(grilleB.get(i));
            PanelCaseBN caseB=grilleB.get(i);
            grilleB.get(i).addActionListener(event->caseClick(caseB));
            i++;
        }
    }
    
    /**
     * méthode appelée lorsque l'utilisateaur clique sur un bateau.
     * Si l'utilisateur doit tirer sur les bateaux ennemis, alors il pourra.
     * @param caseP 
     */
    @Override
    public void caseClick(PanelCaseBN caseP) {
        //System.out.println(caseP);
        if (etat == EtatsGrilleBN.tour) {
            try {
                grille.tire(caseP.getCase());
                caseP.updateImage();
                //updateGrille();
            }
            catch (BatailleNavaleException e) {
                System.out.println(e);
            }
            catch (IOException e) {
                System.out.println(e);
            }
            catch (Exception e) {
                System.out.println(e + " " + grille.getBateaux().toString());
            }
        }
    }
}
