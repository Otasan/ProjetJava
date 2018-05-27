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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ProjetJava.Utilisateur;
import ProjetJava.Membre;
import javax.swing.Box;

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
    public BatailleNavale(Utilisateur user,int diff){
        GridBagLayout layout=new GridBagLayout();
        layout.columnWeights = new double[]{(double)7/16,(double)2/16,(double)7/16};
        layout.rowWeights = new double[]{(double)7/9,(double)2/9};
        setLayout(layout);
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
    protected void init(){
        Dimension d = new Dimension();
        d.height=450;
        d.width=800;
        setSize(800,450);
        setPreferredSize(d);
        
        pJoueur.redimensionner(340);
        pIa.redimensionner(340);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        this.add(pJoueur,c);
        
        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=0;
        add(Box.createHorizontalGlue(),c);
        
        c = new GridBagConstraints();
        c.gridx=2;
        c.gridy=0;
        this.add(pIa,c);
        
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=3;
        this.add(new JLabel("Sample text"),c);
        
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
    public synchronized void jeu(){
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
                if(etat==EtatsBN.touria && gIa.nbBateauRestant()>0){
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
        //System.out.println("fin");
        if(gJoueur.nbBateauRestant()==0){
            if(util instanceof Membre){
                ((Membre)util).incrementPerdu("Bataille Navale");
            }
            JOptionPane.showMessageDialog(this,util.getPseudo()+" a perdu!", "Perdu", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(util instanceof Membre){
                ((Membre)util).incrementGagne("Bataille Navale");
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
        return "Placez vos bateaux stratégiquement et essayez de couler les navires ennemis\nPour jouer:\n\t-Dans une première phase, placez vos cinq bateaux sur votre grille\n\t-Puis cliquez sur la grille de l'adversaire pour tirer sur la case séléctionnée.\nVous gagnez si il vous reste au moins un bateau et que vous avez coulé tout les bateaux ennemis.\nBonne chance, commandant!";
    }
}
