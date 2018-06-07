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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author aabdo
 */
public class BatailleNavale implements ProjetJava.Jeu{
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
        //initialisation des attributs
        gJoueur = new GrilleBN();
        gIa = new GrilleBN();
        pJoueur = new PanelGrilleBNJ(gJoueur);
        pIa = new PanelGrilleBNIA(gIa);
        util=user;
        panel=new JPanel();
        //choix de la BNIA selon la difficulte
        switch(diff){
            case 0:
                ia = new EasyBNIA(gIa, gJoueur);
                break;
            case 1:
                ia = new MediumBNIA(gIa, gJoueur);
                break;
            case 2:
                ia = new HardBNIA(gIa, gJoueur);
                break;
        }
        //empeche le lancement de la FrameChoixBateau
        setTour(EtatsBN.rien);
        init();
    }   
    
    /**
     * Initialise le JPanel (aide le constructeur, ne pas utiliser)
     */
    protected void init(){
        //creation du layout de la frame
        GridBagLayout layout=new GridBagLayout();
        //deux colonnes de largeur egale
        layout.columnWeights = new double[]{(double)1/2,(double)1/2};
        //deux lignes: la première est 5 fois plus petite que la deuxieme
        layout.rowWeights = new double[]{(double)1/5,(double)4/5};
        panel.setLayout(layout);
        Dimension d = new Dimension();
        d.height=450;
        d.width=800;
        //redimensionne le panel d'affichage
        panel.setSize(800,450);
        panel.setPreferredSize(d);
        //redimensionne les deux Grilles
        pJoueur.redimensionner(340);
        pIa.redimensionner(340);
        //ajout du texte sur la premiere ligne
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        panel.add(new JLabel("Votre Grille"),c);
        c.gridx=1;
        panel.add(new JLabel("Grille de l'adversaire"),c);
        //ajout de la grille du joueur (colonne 0, ligne 1)
        c.gridx=0;
        c.gridy=1;
        panel.add(pJoueur,c);
        //ajout de la grille de l'adversaire (colonne 1, ligne 1)
        c.gridx=1;
        panel.add(pIa,c);
        panel.setVisible(true);
        //ajout d'un evenement lorsque la fenetre est redimensionnee
        panel.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                redimensionner();
            }
        });
    }
    
    /**
     * defini l'état de la bataille navale
     * @param val le nouvel etat
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
        //affiche des messages au demarrage de la bataille navale
        JOptionPane.showMessageDialog(panel, "Bienvenu(e) dans la bataille navale", "[INFO]",JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(pJoueur, "Votre grille est sur la gauche.\nC'est là que vous allez placer vos bateaux", "[INFO]" ,JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(pIa, "La grille de l'adversaire est sur la droite.\nC'est là que vous allez pouvoir tirer sur l'ennemi.", "[INFO]", JOptionPane.INFORMATION_MESSAGE);
        //autorise le joueur a placer des bateaux
        setTour(EtatsBN.placerBateau);
        ia.placerBateaux();
        synchronized(pJoueur){
            //empeche le programme de continuer tant que les bateaux ne sont pas tous places
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
        //debut du jeu
        JOptionPane.showMessageDialog(pIa, "A l'attaque, commandant!!!", "[INFO]",JOptionPane.INFORMATION_MESSAGE);
        setTour(EtatsBN.tourj);
        synchronized(pIa){
            while(gJoueur.nbBateauRestant()>0 && gIa.nbBateauRestant()>0){
                try{
                    //attends une action du joueur sur la grille de l'adversaire
                    pIa.wait();
                }
                catch(InterruptedException e){
                    System.out.println(e);
                }
                //tour de l'adversaire
                setTour(pIa.getTour());
                if(etat==EtatsBN.touria && gIa.nbBateauRestant()>0){
                    int nb = gJoueur.nbBateauRestant();
                    CaseBN c =ia.tirer();
                    pJoueur.updateGrille();
                    //affiche un message selon la case touchee
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
                    //tour du joueur
                    setTour(EtatsBN.tourj);
                }
            }
        }
        //decide si l'utilisateur a gagne
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
        //appelle la fin de la bataille navale
        quitter();
    }
    
    /**
     * 
     * @return JPanel de la bataille navale
     */
    public JPanel getPanel(){
        return panel;
    }
    
    /**
     * 
     * @return une description du jeu
     */
    public static String description(){
        return "Placez vos bateaux stratégiquement et essayez de couler les navires ennemis\nPour jouer:\n  - Dans une première phase, placez vos cinq bateaux sur votre grille\n  - Puis cliquez sur la grille de l'adversaire pour tirer sur la case séléctionnée.\nVous gagnez si il vous reste au moins un bateau et que vous avez coulé tout les bateaux ennemis.\nBonne chance, commandant!";
    }

    /**
     * Méthode appelée en fin de partie
     * Override cette methode pour fermer la BatailleNavale
     */
    @Override
    public void quitter() {
        pJoueur.quitter();
    }
    
    /**
     * méthode appelée lorque la fenetre est redimensionnée
     */
    public void redimensionner(){
        //les grilles ont un cote de 4/5 de la hauteur du panel
        int h = (int)(panel.getHeight()*(double)4/5);
        pJoueur.redimensionner(h);
        pIa.redimensionner(h);
        panel.revalidate();
    }
}
