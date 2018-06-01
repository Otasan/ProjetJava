/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ProjetJava.Utilisateur;
import ProjetJava.Membre;
import java.util.Observable;

/**
 *
 * @author Utilisateur
 */
public class BatailleNavale extends Observable implements ProjetJava.Jeu{
    private GrilleBN gJoueur;
    private GrilleBN gIa;
    private PanelGrilleBNJ pJoueur;
    private PanelGrilleBNIA pIa;
    private BNIA ia;
    private volatile EtatsBN etat;
    private Utilisateur util;
    private JPanel panel;
    
    /**
     * Constructeur de BatailleNavale
     * @param user L'utilisateur ayant lancé la bataille navale
     * @param diff La difficulté (allant de 0 à 2)
     */
    public BatailleNavale(Utilisateur user,int diff){
        gJoueur = new GrilleBN();
        gIa = new GrilleBN();
        pJoueur = new PanelGrilleBNJ(gJoueur);
        pIa = new PanelGrilleBNIA(gIa);
        util=user;
        panel=new JPanel();
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
     * Initialise le JPanel (aide le constructeur, ne pas utiliser)
     */
    protected void init(){
        GridBagLayout layout=new GridBagLayout();
        layout.columnWeights = new double[]{(double)1/2,(double)1/2};
        layout.rowWeights = new double[]{(double)1/5,(double)4/5};
        panel.setLayout(layout);
        Dimension d = new Dimension();
        d.height=450;
        d.width=800;
        panel.setSize(800,450);
        panel.setPreferredSize(d);
        pJoueur.redimensionner(340);
        pIa.redimensionner(340);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        panel.add(new JLabel("Votre Grille"),c);
        c.gridx=1;
        panel.add(new JLabel("Grille de l'adversaire"),c);
        c.gridx=0;
        c.gridy=1;
        panel.add(pJoueur,c);
        c.gridx=1;
        panel.add(pIa,c);
        panel.setVisible(true);
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
     */
    public void jeu(){
        pJoueur.updateGrille();
        pIa.updateGrille();
        setTour(EtatsBN.placerBateau);
        ia.placerBateaux();
        synchronized(pJoueur){
            while(etat==EtatsBN.placerBateau){
                try{
                    pJoueur.wait();
                }
                catch(InterruptedException e){
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
                catch(InterruptedException e){
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
            JOptionPane.showMessageDialog(panel,util.getPseudo()+" a perdu!", "Perdu", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(util instanceof Membre){
                ((Membre)util).incrementGagne("Bataille Navale");
            }
            JOptionPane.showMessageDialog(panel,util.getPseudo()+" a gagné!", "Gagné", JOptionPane.INFORMATION_MESSAGE);
        }
        notifyObservers("Fin de la BatailleNavale");
    }
    
    /**
     * 
     * @return JPanel de la bataille navale
     */
    public JPanel getPanel(){
        return panel;
    }
    
    /**
     * renvoie une description du jeu
     * @return 
     */
    public static String description(){
        return "Placez vos bateaux stratégiquement et essayez de couler les navires ennemis\nPour jouer:\n\t-Dans une première phase, placez vos cinq bateaux sur votre grille\n\t-Puis cliquez sur la grille de l'adversaire pour tirer sur la case séléctionnée.\nVous gagnez si il vous reste au moins un bateau et que vous avez coulé tout les bateaux ennemis.\nBonne chance, commandant!";
    }

    /**
     * Override cette methode pour fermer la BatailleNavale
     */
    @Override
    public void quitter() {
        System.out.println("Override cette methode");
    }
}
