/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Utilisateur
 */
public class BatailleNavale extends javax.swing.JPanel{
    private GrilleBN gJoueur;
    private GrilleBN gIa;
    private PanelGrilleBNJ pJoueur;
    private PanelGrilleBNIA pIa;
    private BNIA ia;
    private EtatsBN etat;
    
    public BatailleNavale(int diff) throws Exception{
        setLayout(new GridBagLayout());
        gJoueur = new GrilleBN();
        gIa = new GrilleBN();
        etat = EtatsBN.placerBateau;
        switch(diff){
            case 0:
                ia = new EasyBNIA(gIa, gJoueur);
                break;
            case 1:
                break;
            case 2:
                break;
        }
        ia.placerBateaux();
        init();
    }   
    
    protected void init() throws Exception{
        pJoueur = new PanelGrilleBNJ(gJoueur);
        pIa = new PanelGrilleBNIA(gIa);
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
    
    public void setTour(EtatsBN val){
        etat = val;
        pJoueur.setTour(val);
        pIa.setTour(val);
        ia.setTour(val);
    }
}
