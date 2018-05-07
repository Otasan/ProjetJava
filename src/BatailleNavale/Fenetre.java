/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;
import javax.swing.JFrame;
/**
 *
 * @author aabdo
 */
public class Fenetre extends JFrame{
    public Fenetre (String nom, int hauteur, int largeur){
        this.setTitle(nom);
        this.setSize(largeur, hauteur);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//Peut être à modifier
    }
}
