/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

/**
 *
 * @author edeux
 */
public class ChangerMDPPannel extends javax.swing.JPanel {
    private boolean admin;
    
    /**
     * Creates new form ChangerMDPPannel
     */
    public ChangerMDPPannel(boolean admin) {
        initComponents();
        this.admin=admin;
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

        jLabel4 = new javax.swing.JLabel();
        oldPassword = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        newPasswordValidation = new javax.swing.JPasswordField();
        ValiderButton = new javax.swing.JButton();
        RetourButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Ancien mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(48, 57, 0, 0);
        add(jLabel4, gridBagConstraints);

        oldPassword.setText("jPasswordField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(46, 21, 0, 24);
        add(oldPassword, gridBagConstraints);

        newPassword.setText("jPasswordField2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 21, 0, 24);
        add(newPassword, gridBagConstraints);

        jLabel3.setText("Nouveau mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(43, 50, 0, 0);
        add(jLabel3, gridBagConstraints);

        jLabel5.setText("Confirmation du mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(43, 24, 0, 0);
        add(jLabel5, gridBagConstraints);

        newPasswordValidation.setText("jPasswordField3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 21, 0, 24);
        add(newPasswordValidation, gridBagConstraints);

        ValiderButton.setText("Changer de mot de passe");
        ValiderButton.setMargin(new java.awt.Insets(3, 20, 3, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 86, 47, 0);
        add(ValiderButton, gridBagConstraints);

        RetourButton.setText("Retour");
        RetourButton.setMargin(new java.awt.Insets(3, 20, 3, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(RetourButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public boolean isAdmin(){
        return admin;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RetourButton;
    private javax.swing.JButton ValiderButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JPasswordField newPasswordValidation;
    private javax.swing.JPasswordField oldPassword;
    // End of variables declaration//GEN-END:variables
}
