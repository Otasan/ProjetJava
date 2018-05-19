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
import javax.swing.JPanel;

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
    
    /**
     * 
     * @param diff La difficulté (allant de 0 à 2)
     * @throws Exception 
     */
    public BatailleNavale(int diff) throws Exception{
        setLayout(new GridBagLayout());
        gJoueur = new GrilleBN();
        gIa = new GrilleBN();
        pJoueur = new PanelGrilleBNJ(gJoueur);
        pIa = new PanelGrilleBNIA(gIa);
        switch(diff){
            case 0:
                ia = new EasyBNIA(gIa, gJoueur);
                break;
            case 1:
                break;
            case 2:
                break;
        }
        setTour(EtatsBN.rien);
        init();
    }   
    
    /**
     * Initialise le panel (aide le constructeur, ne pas utiliser)
     * @throws Exception 
     */
    protected void init() throws Exception{
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
     * @return 
     * @throws java.io.IOException 
     */
    public int jeu() throws IOException{
        //pJoueur.updateGrille();
        pIa.updateGrille();
        setTour(EtatsBN.placerBateau);
        ia.placerBateaux();
        while(etat==EtatsBN.placerBateau){
            setTour(pJoueur.getTour());
            System.out.println(5-gJoueur.nbBateauRestant()+" Bateaux à placer");
        }
        setTour(EtatsBN.tourj);
        while(gJoueur.nbBateauRestant()>0 && gIa.nbBateauRestant()>0){
            while(etat==EtatsBN.tourj){
                setTour(pIa.getTour());
                System.out.println("Tire");
            }
            ia.tirer();
            pJoueur.updateGrille();
            setTour(EtatsBN.tourj);
        }
        System.out.println("fin");
        return 1;
    }
}
