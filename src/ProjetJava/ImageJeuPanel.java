/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import javax.swing.JOptionPane;

/**
 * Panel de l'image d'un jeu.
 *
 * @author edeux
 */
public class ImageJeuPanel extends javax.swing.JPanel {

    /**
     * Creation du Panel et changement du titre et de l'image en fonction du
     * jeu.
     */
    public ImageJeuPanel(String nomJeu, boolean titre) {
        initComponents();
        // Cherche les images dans le package ProjetJava.images
        // L'image doit être au format .jpg et avoir comme titre celui d'un jeu présent.
        javax.swing.ImageIcon icone = new javax.swing.ImageIcon(getClass().getResource("/ProjetJava/images/" + nomJeu + ".jpg"));
        if (icone != null) {
            imageLabel.setIcon(icone);
        } else {
            JOptionPane.showMessageDialog(null, "L'image du jeu " + nomJeu + "N'as pas pu être chargée");
        }
        if (!titre) {
            this.titleLabel = null;
        } else {
            titleLabel.setText(nomJeu);
            this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        imageLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();

        setFocusable(true);
        setMaximumSize(new java.awt.Dimension(300, 315));
        setMinimumSize(new java.awt.Dimension(220, 240));
        setPreferredSize(new java.awt.Dimension(220, 240));
        java.awt.GridBagLayout layout1 = new java.awt.GridBagLayout();
        layout1.columnWidths = new int[] {200};
        layout1.rowHeights = new int[] {180, 30};
        setLayout(layout1);

        imageLabel.setBackground(new java.awt.Color(0, 0, 0));
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageLabel.setMaximumSize(new java.awt.Dimension(200, 180));
        imageLabel.setMinimumSize(new java.awt.Dimension(200, 180));
        imageLabel.setPreferredSize(new java.awt.Dimension(200, 180));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(imageLabel, gridBagConstraints);

        titleLabel.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setLabelFor(imageLabel);
        titleLabel.setText("Titre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(titleLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
