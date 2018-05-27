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
public class PanelGrilleBNIA extends PanelGrilleBN{
    
    public PanelGrilleBNIA(GrilleBN g) throws IOException {
        super(g);
        createGrille();
        etat=EtatsBN.placerBateau;
    }
    
    @Override
    protected void createGrille() throws IOException{
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
     * méthode appelée lorsque l'utilisateaur clique sur un bateau.
     * Si l'utilisateur doit tirer sur les bateaux ennemis, alors il pourra.
     * @param caseP 
     */
    private synchronized void caseClick(PanelCaseBN caseP) {
        //System.out.println(caseP);
        if (etat == EtatsBN.tourj) {
            try {
                int nb = grille.nbBateauRestant();
                grille.tire(caseP.getCase());
                caseP.updateImage();
                switch(grille.getCase(caseP.getCase()).getCase()){
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
                //updateGrille();
                etat=EtatsBN.touria;
                notify();
            }
            catch (BNException e) {
                etat=EtatsBN.tourj;
                JOptionPane.showMessageDialog(this, e, "Case déjà touchée", JOptionPane.INFORMATION_MESSAGE);
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
