/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

/**
 *
 * @author deux
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * Creates new form ConnexionGUI
     */
    public MainGUI() {
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
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
        setTitle("Boite De Jeux");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(640, 360));
        setPreferredSize(new java.awt.Dimension(854, 480));
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /*
    Initialisation de la fenetre de Connexion.
     */
    public void interfaceConnexion() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new ConnexionPanel());
        this.revalidate();
        this.pack();
    }

    /*
    Initialisation de la fenetre d'inscription.
     */
    public void interfaceInscription() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new InscriptionPanel());
        this.pack();
    }

    public void interfaceJeu() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new JeuPanel("Bataille Navale"));
        this.pack();
    }
}
