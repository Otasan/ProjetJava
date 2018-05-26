/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ProjetJava.Utilisateur;
import ProjetJava.Membre;

/**
 *
 * @author Utilisateur
 */
public class BatailleNavale extends JPanel{
    private GrilleBN gJoueur;
    private GrilleBN gIa;
    private PanelGrilleBNJ pJoueur;
    private PanelGrilleBNIA pIa;
    private BNIA ia;
    private volatile EtatsBN etat;
    private Utilisateur util;
    
    /**
     * 
     * @param diff La difficulté (allant de 0 à 2)
     * @throws Exception 
     */
    public BatailleNavale(Utilisateur user,int diff) throws IOException{
        setLayout(new GridBagLayout());
        gJoueur = new GrilleBN();
        gIa = new GrilleBN();
        pJoueur = new PanelGrilleBNJ(gJoueur);
        pIa = new PanelGrilleBNIA(gIa);
        util=user;
        switch(diff){
            case 0:
                ia = new EasyBNIA(gIa, gJoueur);
                break;
            case 1:
                ia = new MediumBNIA(gIa, gJoueur);
                break;
            case 2:
                ia = new MediumBNIA(gIa, gJoueur);//TODO: Modifier par la HardBNIA quand elle sera finie
                break;
        }
        setTour(EtatsBN.rien);
        init();
    }   
    
    /**
     * Initialise le panel (aide le constructeur, ne pas utiliser)
     * @throws Exception 
     */
    protected void init() throws IOException{
        pJoueur.redimensionner(340);
        pIa.redimensionner(340);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=7;
        c.gridheight=7;
        c.weightx=0.5;
        c.weighty=0.5;
        this.add(pJoueur,c);
        
        c.gridx=8;
        c.gridy=1;
        this.add(pIa,c);
        
        c.gridheight=2;
        c.gridwidth=16;
        c.gridx=0;
        c.gridy=7;
        this.add(new JLabel("Sample text"),c);
        
        Dimension d = new Dimension();
        d.height=450;
        d.height=800;
        setSize(800,450);
        setPreferredSize(d);
        setVisible(true);
    }
    
    /**
     * defini l'état de la bataille navale
     * @param val 
     */
    public void setTour(EtatsBN val){
        etat = val;
        pJoueur.setTour(val);
        pIa.setTour(val);
        ia.setTour(val);
    }
    
    /**
     * Lance le Jeu
     * @throws java.io.IOException 
     */
    public synchronized void jeu() throws IOException{
        pJoueur.updateGrille();
        pIa.updateGrille();
        this.setVisible(true);
        setTour(EtatsBN.placerBateau);
        ia.placerBateaux();
        synchronized(pJoueur){
            while(etat==EtatsBN.placerBateau){
                try{
                    pJoueur.wait();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                setTour(pJoueur.getTour());
            }
        }
        setTour(EtatsBN.tourj);
        synchronized(pIa){
            while(gJoueur.nbBateauRestant()>0 && gIa.nbBateauRestant()>0){
                try{
                    pIa.wait();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                setTour(pIa.getTour());
                if(etat==EtatsBN.touria){
                    int nb = gJoueur.nbBateauRestant();
                    CaseBN c =ia.tirer();
                    pJoueur.updateGrille();
                    switch(c.getCase()){
                        case toucheVierge:
                            JOptionPane.showMessageDialog(pJoueur, "Raté", "Tour de l'adversaire", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case touche:
                            if(nb==gJoueur.nbBateauRestant()){
                                JOptionPane.showMessageDialog(pJoueur, "Touché", "Tour de l'adversaire", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(pJoueur, "Coulé", "Tour de l'adversaire", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;
                    }
                    setTour(EtatsBN.tourj);
                }
            }
        }
        System.out.println("fin");
        if(gJoueur.nbBateauRestant()==0){
            if(util instanceof Membre){
                ((Membre)util).incrementPerdu("BatailleNavale");
            }
            JOptionPane.showMessageDialog(this,util.getPseudo()+" a perdu!", "Perdu", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(util instanceof Membre){
                ((Membre)util).incrementGagne("BatailleNavale");
            }
            JOptionPane.showMessageDialog(this,util.getPseudo()+" a gagné!", "Gagné", JOptionPane.INFORMATION_MESSAGE);
        }
        notify();
    }
    
    /**
     * renvoie une description du jeu
     * @return 
     */
    public static String description(){
        return "\n";
    }
}
