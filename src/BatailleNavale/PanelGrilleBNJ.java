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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Utilisateur
 */
public class PanelGrilleBNJ extends PanelGrilleBN {
    private FrameChoixBateau choix;
    
    
    public PanelGrilleBNJ(GrilleBN g){
        super(g);
        createGrille();
    }
    
    /**
     * Identique à la méthode de la classe parent mais génère une FrameChoixBateau si val=placerBateau et que cette frame n'a pas encore été créé.
     * @param val 
     */
    @Override
    public void setTour(EtatsBN val){
        super.setTour(val);
        if(val==EtatsBN.placerBateau && choix == null){
            choix = new FrameChoixBateau();
        }
    }
    
    /**
     * génère la grille à afficher. utile uniquement dans le constructeur
     */
    @Override
    protected void createGrille(){
        TreeSet g = grille.getGrille();
        Iterator it = g.iterator();
        int i=0;
        int o=0;
        while(it.hasNext()){
            if(i==0){
                add(new JLabel(""));
            }
            else if(i<11){
                add(new JLabel(Character.toString((char) (i+64))));
            }
            else if(i%11==0){
                add(new JLabel(Integer.toString(i/11)));
            }
            else{
                grilleB.add(new PanelCaseBNJ((CaseBN)it.next(), grille.getBateaux()));
                add(grilleB.get(o));
                PanelCaseBN caseB=grilleB.get(o);
                grilleB.get(o).addActionListener(event->caseClick(caseB));
                o++;
            }
            i++;
        }
    }
    
    /**
     * méthode appelée lorsque l'utilisateaur clique sur un bateau.
     * Si l'utilisateur doit placer des bateaux, alors il pourra.
     * @param caseP 
     */
    private synchronized void caseClick(PanelCaseBN caseP){
        //System.out.println(etat);
        if(etat==EtatsBN.placerBateau){
            if(choix.getValide()){
                try{
                    grille.placerBateau(choix.getBateau(),caseP.getCase(),choix.getSens());
                    updateGrille();
                }
                catch(BNException e){
                    JOptionPane.showMessageDialog(this, e.toString(), "Bateau mal placé", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(grille.getBateaux().size()<5){
                choix.nouveauBateau(grille.getBateaux());
                notify();
            }
            else{
                choix.dispatchEvent(new WindowEvent(choix, WindowEvent.WINDOW_CLOSING));
                etat=EtatsBN.tourj;
                notify();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Vous ne pouvez pas/pas encore faire ça\n"+etat, "Erreur", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
