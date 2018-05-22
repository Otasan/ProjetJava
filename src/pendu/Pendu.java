/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 *
 * @author KREATURE
 */
public class Pendu {

    /**
     * @param args the command line arguments
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


