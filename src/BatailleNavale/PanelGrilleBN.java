/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aabdo
 */
public abstract class PanelGrilleBN extends javax.swing.JPanel{
    protected ArrayList<PanelCaseBN> grilleB;
    protected GrilleBN grille;
    protected volatile EtatsBN etat;
    
    /**
     * crée un PanelGrille BN carrée de longueur 180 (redimensionable avec la methode redimentionner)
     * @param g grille a être affiché
     */
    public PanelGrilleBN(GrilleBN g){
        initComponents();
        grille=g;
        etat=EtatsBN.rien;
        grilleB=new ArrayList();
        GridLayout layout = new GridLayout(11,11);
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);
        setSize(180,180);
    }
    
    /**
     * modifie l'état de la bataille navale
     * @param t nouvel etat
     */
    public void setTour(EtatsBN t){
        etat=t;
    }
    
    /**
     * Donne l'état actuel de la grille
     * @return etat
     */
    public EtatsBN getTour(){
        return etat;
    }
    
    /**
     * génère la grille qui va être affichée. utile uniquement dans le constructeur
     */
    abstract protected void createGrille();
    
    /**
     * mets à jour toutes toutes les images de la grille (lent, à utiliser rarement)
     * préferer grilleB.get(i).updateImage(grille.getBateaux());
     */
    public void updateGrille(){
        for(PanelCaseBN g:grilleB){
            g.updateImage();
        }
        this.setVisible(true);
        this.repaint();
    }
    
    /**
     * change la longueur du coté de la grille par l et modifie la taille de chaque image automatiquement.
     * @param l 
     */
    public void redimensionner(int l){
        setSize(l, l);
        int nl = l/11;
        for(int i=0;i<100;i++){
            grilleB.get(i).redimensionner(nl);
        }
    }
    
    /**
     * permet de placer un bateau logique puis mets à jou la grille.
     * @param typeBateau string correspondant au nom de la classe du bateau voulu
     * @param cI case la plus en haut ou à gauche du bateau
     * @param sens horizontal ou vertical
     * @throws BNException 
     */
    public void placerBateau(String typeBateau, CaseBN cI, Direction sens) throws BNException{
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
