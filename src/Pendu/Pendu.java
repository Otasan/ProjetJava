/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu;

import ProjetJava.Utilisateur;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author achelle
 */
public class Pendu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.getContentPane().removeAll();

        try {
            JeuPendu jeu = new JeuPendu(new Utilisateur("Invite"), 0);
            PenduPanel pen = new PenduPanel(jeu);
            jeu.addObserver(pen);
            frame.getContentPane().add(pen);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Impossible de lancer le jeu, le dictionnaire n'as pas été trouvé.");
        }

        frame.pack();
        frame.setVisible(true);
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(exitListener);
    }
}
