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
public abstract class PanelGrilleBN extends javax.swing.JPanel{
    protected ArrayList<PanelCaseBN> grilleB;
    protected GrilleBN grille;
    protected EtatsBN etat;
    /**
     * crée un PanelGrille BN carrée de longueur 160 (redimensionable avec la methode redimentionner)
     */
    public PanelGrilleBN(GrilleBN g) throws Exception {
        initComponents();
        grille=g;
        etat=EtatsBN.rien;
        grilleB=new ArrayList();
        setLayout(new GridLayout(11,11));
        setSize(180,180);
    }
    
    /**
     * autorise ou non le joueur à cliquer sur la grille
     * TODO: remplacer par une variable etat qui permet de placer des bateaux
     * @param t 
     */
    public void setTour(EtatsBN t){
        etat=t;
    }
    
    /**
     * Donne l'état actuel de la grille
     * @return 
     */
    public EtatsBN getTour(){
        return etat;
    }
    
    /**
     * génère la grille qui va être affichée. utile uniquement dans le constructeur
     * @throws IOException 
     */
    abstract protected void createGrille() throws IOException;
    
    /**
     * tes à jour toutes toutes les images de la grille (lent, à utiliser rarement)
     * préferer grilleB.get(i).updateImage(grille.getBateaux());
     * @throws IOException 
     */
    public void updateGrille() throws IOException{
        for(PanelCaseBN g:grilleB){
            g.updateImage();
        }
        this.setVisible(true);
        this.repaint();
    }
    
    /**
     * méthode appelée lorsque l'utilisateaur clique sur un bateau.
     * TODO: ajouter un moyen de placer les bateau.
     * @param caseP 
     */
    abstract public void caseClick(PanelCaseBN caseP);
    
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
    public void placerBateau(String typeBateau, CaseBN cI, Direction sens) throws Exception{
        grille.placerBateau(typeBateau, cI, sens);
        System.out.println(grille.getGrille().toString());
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
