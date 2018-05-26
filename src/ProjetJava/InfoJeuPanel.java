/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Panel contenant les informations sur un jeu.
 *
 * @author deux
 */
public class InfoJeuPanel extends javax.swing.JPanel {

    /**
     * Creates new form InfoJeuPanel
     */
    public InfoJeuPanel(Identification id, String jeu) {
        initComponents();
        scoreTextArea.setText("");
        for (Membre m : id.getMembres()) {
            String victoires = "";
            try {
                victoires = padString(m.getScore(jeu)[1].toString(), 20);
            } catch (ScoreException ex) {
                victoires = "###";
            }
            String ratio = "";
            try {
                ratio = padString(m.getRatio(jeu) + "", 10);
            } catch (ScoreException ex) {
                ratio = "###";
            }
            scoreTextArea.append(""
                    + padString(m.getPseudo(), 30) + "\t"
                    + victoires + "\t\t" + ratio + "\n");
        }
        this.imagePanel.add(new ImageJeuPanel(jeu, false));
        nomJeuLabel.setText(jeu);
    }

    private String padString(String str, int n) {
        if (str.length() < n) {
            for (int j = str.length(); j < n; j++) {
                str += " ";
            }
        }
        return str;
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

        nomJeuLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        imagePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reglesTextArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        scoreTextArea = new javax.swing.JTextArea();
        scoreLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        difficulteComboBox = new javax.swing.JComboBox<>();
        jouerButton = new javax.swing.JButton();
        retourButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(640, 360));
        setPreferredSize(new java.awt.Dimension(854, 480));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWeights = new double[] {0.4, 0.6};
        layout.rowWeights = new double[] {0.15, 0.7, 0.15};
        setLayout(layout);

        nomJeuLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        nomJeuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomJeuLabel.setText("Choix du Jeu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(nomJeuLabel, gridBagConstraints);

        jPanel1.setForeground(new java.awt.Color(51, 51, 31));
        jPanel1.setMinimumSize(new java.awt.Dimension(640, 298));
        jPanel1.setPreferredSize(new java.awt.Dimension(854, 384));
        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 10, 10));
        jPanel1.add(imagePanel);

        jScrollPane1.setBorder(null);

        reglesTextArea.setEditable(false);
        reglesTextArea.setBackground(new java.awt.Color(238, 238, 238));
        reglesTextArea.setColumns(20);
        reglesTextArea.setRows(5);
        reglesTextArea.setBorder(null);
        jScrollPane1.setViewportView(reglesTextArea);

        jPanel1.add(jScrollPane1);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWeights = new double[] {1.0};
        jPanel2Layout.rowWeights = new double[] {0.1, 0.9};
        jPanel2.setLayout(jPanel2Layout);

        scoreTextArea.setEditable(false);
        scoreTextArea.setColumns(3);
        scoreTextArea.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        scoreTextArea.setRows(5);
        jScrollPane2.setViewportView(scoreTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        scoreLabel.setBackground(new java.awt.Color(255, 255, 255));
        scoreLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        scoreLabel.setText(padString("Pseudo", 30) +
            padString("Victoires", 30) +
            padString("Ratio", 10)
        );
        scoreLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(scoreLabel, gridBagConstraints);

        jPanel1.add(jPanel2);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWeights = new double[] {0.5, 0.5};
        jPanel3.setLayout(jPanel3Layout);

        difficulteComboBox.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        difficulteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facile", "Moyen", "Difficile" }));
        jPanel3.add(difficulteComboBox, new java.awt.GridBagConstraints());

        jouerButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jouerButton.setText("Jouer");
        jPanel3.add(jouerButton, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        add(jPanel1, gridBagConstraints);

        retourButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        retourButton.setText("Retour");
        retourButton.setMargin(new java.awt.Insets(3, 20, 3, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(retourButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JButton getRetourButton() {
        return retourButton;
    }

    public javax.swing.JButton getJouerButton() {
        return jouerButton;
    }

    public int getDifficulte() {
        return difficulteComboBox.getSelectedIndex();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> difficulteComboBox;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jouerButton;
    private javax.swing.JLabel nomJeuLabel;
    private javax.swing.JTextArea reglesTextArea;
    private javax.swing.JButton retourButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JTextArea scoreTextArea;
    // End of variables declaration//GEN-END:variables
}
