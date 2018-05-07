/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author aabdo
 */
public class PanelGrilleBN extends javax.swing.JPanel{
    private ArrayList<PanelCaseBN> grilleB;
    private GrilleBN grille;
    //private boolean monTour;
    private EtatsGrilleBN etat;
    private FrameChoixBateau choix;
    /**
     * crée un PanelGrille BN carrée de longueur 160 (redimensionable avec la methode redimentionner)
     */
    public PanelGrilleBN(GrilleBN g, boolean estJoueur) throws Exception {
        initComponents();
        grille=g;
        grilleB=new ArrayList();
        setLayout(new GridLayout(10,10));
        createGrille();
        setSize(160,160);
        if(estJoueur){
            choix=new FrameChoixBateau();
            etat=EtatsGrilleBN.jPlacerBateau;
        }
        else{
            etat=EtatsGrilleBN.iAPlacerBateau;
        }
        /*placerBateau("porteavion", new CaseBatailleNavale(0,0), Direction.horizontal);
        placerBateau("Torpilleur", new CaseBatailleNavale(5,7), Direction.vertical);
        placerBateau("SousMarin", new CaseBatailleNavale(9,5), Direction.vertical);
        placerBateau("ContreTorpilleur", new CaseBatailleNavale(5,5), Direction.horizontal);
        placerBateau("Croiseur", new CaseBatailleNavale(0,9), Direction.horizontal);
        
        //test pour placer des bateaux sur la grille*/
        
    }
    
    /**
     * autorise ou non le joueur à cliquer sur la grille
     * TODO: remplacer par une variable etat qui permet de placer des bateaux
     * @param t 
     */
    public void setTour(EtatsGrilleBN t){
        etat=t;
    }
    
    /**
     * génère la grille qà être affichée. utile uniquement dans le constructeur
     * @throws IOException 
     */
    public void createGrille() throws IOException{
        TreeSet g = grille.getGrille();
        Iterator it = g.iterator();
        int i=0;
        while(it.hasNext()){
            grilleB.add(new PanelCaseBN((CaseBatailleNavale)it.next()));
            add(grilleB.get(i));
            PanelCaseBN caseB=grilleB.get(i);
            grilleB.get(i).addActionListener(event->caseClick(caseB));
            i++;
        }
    }
    
    /**
     * tes à jour toutes toutes les images de la grille (lent, à utiliser rarement)
     * préferer grilleB.get(i).updateImage(grille.getBateaux());
     * @throws IOException 
     */
    public void updateGrille() throws IOException{
        for(PanelCaseBN g:grilleB){
            g.updateImage(grille.getBateaux());
        }
        this.setVisible(true);
    }
    
    /**
     * méthode appelée lorsque l'utilisateaur clique sur un bateau.
     * TODO: ajouter un moyen de placer les bateau.
     * @param caseP 
     */
    public void caseClick(PanelCaseBN caseP){
        //System.out.println(caseP);
        switch(etat){
            case tourJ:
                try{
                    grille.tire(caseP.getCase());
                    caseP.updateImage(grille.getBateaux());
                    //updateGrille();
                }
                catch(BatailleNavaleException e){
                    System.out.println(e);
                }
                catch(IOException e){
                    System.out.println(e);
                }
                catch(Exception e){
                    System.out.println(e+" "+grille.getBateaux().toString());
                }
                break;
            case jPlacerBateau:
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
                    etat=EtatsGrilleBN.tourJ;
                }
                break;
        }
    }
    
    /**
     * change la longueur du coté de la grille par l et modifie la taille de chaque image automatiquement.
     * @param l 
     */
    public void redimensionner(int l){
        setSize(l, l);
        for(int i=0;i<100;i++){
            grilleB.get(i).redimensionner((int)(l/10));
        }
    }
    
    /**
     * permet de placer un bateau logique puis mets à jou la grille.
     * @param typeBateau
     * @param cI
     * @param sens
     * @throws Exception 
     */
    public void placerBateau(String typeBateau, CaseBatailleNavale cI, Direction sens) throws Exception{
        grille.placerBateau(typeBateau, cI, sens);
        updateGrille();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
