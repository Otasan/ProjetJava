/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author aabdo
 */
public class PanelGrilleBNIA extends PanelGrilleBN{
    
    public PanelGrilleBNIA(GrilleBN g){
        super(g);
        createGrille();
        etat=EtatsBN.placerBateau;
    }
    
    /**
     * genère la grille à afficher. utile uniquement dans le constructeur
     * affiche les bateaux comme des cases vierges.
     */
    @Override
    protected void createGrille(){
        TreeSet g = grille.getGrille();
        Iterator it = g.iterator();
        int i=0;
        int o=0;
        //genere la grille
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
                //une grille de 10*10 PanelCaseBNIA ailleurs
                grilleB.add(new PanelCaseBNIA((CaseBN)it.next()));
                add(grilleB.get(o));
                PanelCaseBN caseB=grilleB.get(o);
                grilleB.get(o).addActionListener(event->caseClick(caseB));
                o++;
            }
            i++;
        }
    }
    
    /**
     * methode appelee lorsque l'utilisateaur clique sur une case
     * Si l'utilisateur doit tirer sur les bateaux ennemis, alors il pourra.
     * @param caseP la case sur laquelle l'utilisateur a clique
     */
    private synchronized void caseClick(PanelCaseBN caseP) {
        if (etat == EtatsBN.tourj) {
            try {
                //essaie de tirer la ou le joueur a clique
                int nb = grille.nbBateauRestant();
                grille.tire(caseP.getCase());
                //mets a jour les images de la grille
                updateGrille();
                switch(grille.getCase(caseP.getCase()).getCase()){
                    //choisi le message selon la case touchee
                    case toucheVierge:
                        JOptionPane.showMessageDialog(this, "Raté", "Tour du joueur", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case touche:
                        if(nb==grille.nbBateauRestant()){
                            JOptionPane.showMessageDialog(this, "Touché", "Tour du joueur", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Coulé", "Tour du joueur", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                }
                //change l'etat de la grille et resume l'exectution de la bataille navale
                etat=EtatsBN.touria;
                notify();
            }
            catch (BNException e) {
                etat=EtatsBN.tourj;
                JOptionPane.showMessageDialog(this, e, "Case déjà touchée", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
