/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu;

import ProjetJava.AdminPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author KREATURE
 */
public class Pendu {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args){
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.getContentPane().removeAll();
        try {
            frame.getContentPane().add(new PenduPanel());
        } catch (FileNotFoundException ex) {
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
        //Dictionnaire d = new Dictionnaire();
        //System.out.println(d.motAleatoire());
        //System.out.print(d.toString());
        //MotPendu m = new MotPendu(d.motAleatoire());
        //System.out.println(m);
        //m.DemarerLeJeu();
        //JeuPendu p =new JeuPendu(d.motAleatoire());
        //p.DemarerLeJeu();
}
}


