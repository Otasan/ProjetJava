/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pendu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author KREATURE
 */
public class PenduPanel extends javax.swing.JPanel implements Observer {

    private JeuPendu jeu;

    /**
     *
     * @throws FileNotFoundException
     */
    public PenduPanel(JeuPendu jeu) throws FileNotFoundException {
        initComponents();
        this.jeu = jeu;
        motLabel.setText(this.jeu.getMot());
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                jeu.etapeJeu((char) evt.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
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

        motLabel = new javax.swing.JLabel();
        lettrePanel = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();

        setFocusable(true);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWeights = new double[] {0.5, 0.5};
        layout.rowWeights = new double[] {0.5, 0.5};
        setLayout(layout);

        motLabel.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        motLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        motLabel.setText("Mot");
        motLabel.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(motLabel, gridBagConstraints);

        lettrePanel.setBackground(new java.awt.Color(31, 31, 51));
        lettrePanel.setPreferredSize(new java.awt.Dimension(100, 100));
        lettrePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(lettrePanel, gridBagConstraints);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(imageLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            //imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProjetJava/images/pendu" + jeu.nbErreurs() + ".jpg")));
            this.motLabel.setText(jeu.getMot());
            if (jeu.derniereLettreUtil() != null) {
                this.lettrePanel.add(new JLabel(jeu.derniereLettreUtil()));
            }
            this.repaint();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel lettrePanel;
    private javax.swing.JLabel motLabel;
    // End of variables declaration//GEN-END:variables

}