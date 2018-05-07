/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.util.HashSet;

/**
 *
 * @author Utilisateur
 */
public class FrameChoixBateau extends javax.swing.JDialog {
    private Direction sens;
    private String bateauChoisi;
    private boolean aValide;
    /**
     * Creates new form FrameChoixBateau
     */
    public FrameChoixBateau() {
        //super(parent, modal);
        initComponents();
        aValide=false;
        sens=Direction.vertical;
        bateauChoisi="porteavion";
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        choixBateau = new javax.swing.ButtonGroup();
        choixSens = new javax.swing.ButtonGroup();
        selPorteAvion = new javax.swing.JRadioButton();
        selCroiseur = new javax.swing.JRadioButton();
        selContreTorpilleur = new javax.swing.JRadioButton();
        selSousMarin = new javax.swing.JRadioButton();
        selTorpilleur = new javax.swing.JRadioButton();
        selVertical = new javax.swing.JRadioButton();
        selHorizontal = new javax.swing.JRadioButton();
        valider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        choixBateau.add(selPorteAvion);
        selPorteAvion.setSelected(true);
        selPorteAvion.setText("Porte Avion");
        selPorteAvion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selPorteAvionActionPerformed(evt);
            }
        });

        choixBateau.add(selCroiseur);
        selCroiseur.setText("Croiseur");
        selCroiseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selCroiseurActionPerformed(evt);
            }
        });

        choixBateau.add(selContreTorpilleur);
        selContreTorpilleur.setText("Contre Torpilleur");
        selContreTorpilleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selContreTorpilleurActionPerformed(evt);
            }
        });

        choixBateau.add(selSousMarin);
        selSousMarin.setText("Sous Marin");
        selSousMarin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selSousMarinActionPerformed(evt);
            }
        });

        choixBateau.add(selTorpilleur);
        selTorpilleur.setText("Torpilleur");
        selTorpilleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selTorpilleurActionPerformed(evt);
            }
        });

        choixSens.add(selVertical);
        selVertical.setSelected(true);
        selVertical.setText("Vertical");
        selVertical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selVerticalActionPerformed(evt);
            }
        });

        choixSens.add(selHorizontal);
        selHorizontal.setText("Horizontal");
        selHorizontal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selHorizontalActionPerformed(evt);
            }
        });

        valider.setText("Valider");
        valider.setActionCommand("valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selCroiseur)
                    .addComponent(selTorpilleur)
                    .addComponent(selSousMarin)
                    .addComponent(selPorteAvion)
                    .addComponent(selContreTorpilleur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selVertical)
                    .addComponent(selHorizontal)
                    .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selVertical)
                        .addGap(18, 18, 18)
                        .addComponent(selHorizontal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selPorteAvion)
                        .addGap(18, 18, 18)
                        .addComponent(selCroiseur)
                        .addGap(18, 18, 18)
                        .addComponent(selContreTorpilleur)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selSousMarin)
                        .addGap(18, 18, 18)
                        .addComponent(selTorpilleur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(valider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selCroiseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selCroiseurActionPerformed
        // TODO add your handling code here:
        bateauChoisi="croiseur";
    }//GEN-LAST:event_selCroiseurActionPerformed

    private void selPorteAvionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selPorteAvionActionPerformed
        // TODO add your handling code here:
        bateauChoisi="porteavion";
    }//GEN-LAST:event_selPorteAvionActionPerformed

    private void selContreTorpilleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selContreTorpilleurActionPerformed
        // TODO add your handling code here:
        bateauChoisi="contretorpilleur";
    }//GEN-LAST:event_selContreTorpilleurActionPerformed

    private void selSousMarinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selSousMarinActionPerformed
        // TODO add your handling code here:
        bateauChoisi="sousmarin";
    }//GEN-LAST:event_selSousMarinActionPerformed

    private void selTorpilleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selTorpilleurActionPerformed
        // TODO add your handling code here:
        bateauChoisi="torpilleur";
    }//GEN-LAST:event_selTorpilleurActionPerformed

    private void selVerticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selVerticalActionPerformed
        // TODO add your handling code here:
        sens=Direction.vertical;
    }//GEN-LAST:event_selVerticalActionPerformed

    private void selHorizontalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selHorizontalActionPerformed
        // TODO add your handling code here:
        sens=Direction.horizontal;
    }//GEN-LAST:event_selHorizontalActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        // TODO add your handling code here:
        aValide=true;
        this.setVisible(false);
    }//GEN-LAST:event_validerActionPerformed

    public void nouveauBateau(HashSet<Bateau> lesBateaux){
        aValide=false;
        for(Bateau b:lesBateaux){
            switch(b.getClass().getSimpleName()){
                case"PorteAvion":
                    selPorteAvion.setEnabled(false);
                    break;
                case"Croiseur":
                    selCroiseur.setEnabled(false);
                    break;
                case"Torpilleur":
                    selTorpilleur.setEnabled(false);
                    break;
                case"ContreTorpilleur":
                    selContreTorpilleur.setEnabled(false);
                    break;
                case"SousMarin":
                    selSousMarin.setEnabled(false);
                    break;
            }
        }
        this.setVisible(true);
    }
    
    public boolean getValide(){
        return aValide;
    }
    
    public String getBateau(){
        return bateauChoisi;
    }
    
    public Direction getSens(){
        return sens;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup choixBateau;
    private javax.swing.ButtonGroup choixSens;
    private javax.swing.JRadioButton selContreTorpilleur;
    private javax.swing.JRadioButton selCroiseur;
    private javax.swing.JRadioButton selHorizontal;
    private javax.swing.JRadioButton selPorteAvion;
    private javax.swing.JRadioButton selSousMarin;
    private javax.swing.JRadioButton selTorpilleur;
    private javax.swing.JRadioButton selVertical;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}
