package BatailleNavale;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Utilisateur
 */
public class JframeTest extends javax.swing.JFrame {
    private PanelGrilleBN grille;
    /**
     * Creates new form JframeTest
     * me permet de tester mes panels, ignore cette classe.
     */
    public JframeTest() throws Exception {
        initComponents();
        GrilleBN g = new GrilleBN();
        //g.placerBateau("PorteAvion", new CaseBatailleNavale(1,0), Direction.horizontal);
        grille = new PanelGrilleBNJ(g);
        add(grille, BorderLayout.CENTER);
        grille.redimensionner(380);
        this.setVisible(true);
        //grille.placerBateau("Torpilleur", new CaseBatailleNavale(0,0), Direction.horizontal);
        //grille.setTour(EtatsGrilleBN.tour);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
