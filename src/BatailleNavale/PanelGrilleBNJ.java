/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author aabdo
 */
public class PanelGrilleBNJ extends PanelGrilleBN {
    private FrameChoixBateau choix;
    
    
    public PanelGrilleBNJ(GrilleBN g){
        super(g);
        createGrille();
    }
    
    /**
     * modifie l'etat de la bataille navale
     * genère une FrameChoixBateau si val=placerBateau et que cette frame n'a pas encore ete cree.
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
     * genère la grille a afficher. utile uniquement dans le constructeur
     * affiche tout ce qui est present sur la grille.
     */
    @Override
    protected void createGrille(){
        TreeSet g = grille.getGrille();
        Iterator it = g.iterator();
        int i=0;
        int o=0;
        while(it.hasNext()){
            if(i==0){
                //une case vide en haut a gauche
                add(new JLabel(""));
            }
            else if(i<11){
                //des cases numerotees de A a J en haut
                add(new JLabel(Character.toString((char) (i+64)),SwingConstants.CENTER));
            }
            else if(i%11==0){
                //des cases numerotees de 1 a 10 a gauche
                add(new JLabel(Integer.toString(i/11),SwingConstants.CENTER));
            }
            else{
                //une grille de 10*10 PanelCaseBNJ ailleurs
                grilleB.add(new PanelCaseBNJ((CaseBN)it.next(), grille.getBateaux()));
                add(grilleB.get(o));
                PanelCaseBN caseB=grilleB.get(o);
                grilleB.get(o).addActionListener(event->caseClick(caseB));
                o++;
            }
            i++;
        }
    }
    
    public void quitter(){
        //force la FrameChoixBateau a se fermer lorsque la bataille navale est fermee
        choix.dispatchEvent(new WindowEvent(choix, WindowEvent.WINDOW_CLOSING));
    }
    
    /**
     * methode appelee lorsque l'utilisateaur clique sur une case.
     * Si l'utilisateur doit placer des bateaux, alors il pourra.
     * @param caseP la case sur laquelle l'utilisateur a clique
     */
    private synchronized void caseClick(PanelCaseBN caseP){
        if(etat==EtatsBN.placerBateau){
            //verifie si l'utilisateur a valide son choix
            if(choix.getValide()){
                try{
                    //essaie de placer un bateau ou l'utilisateur a clique
                    //affiche un message sinon
                    grille.placerBateau(choix.getBateau(),caseP.getCase(),choix.getSens());
                    updateGrille();
                }
                catch(BNException e){
                    JOptionPane.showMessageDialog(this, e.toString(), "Bateau mal placé", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(grille.getBateaux().size()<5){
                //demande un nouveau bateau si pas assez de bateaux ont etes places
                choix.nouveauBateau(grille.getBateaux());
                notify();
            }
            else{
                //force la fermeture de la FrameChoixBateau a la fin du placement des bateaux
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
