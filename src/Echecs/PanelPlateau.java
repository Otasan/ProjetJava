/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echecs;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.TreeSet;
import javax.swing.JPanel;

/**
 *
 * @author Utilisateur
 */
public class PanelPlateau extends Observable{
    private TreeSet<PanelCaseEchecs> lesCases;
    private Equipe blancs;
    private Equipe noirs;
    private JPanel panel;
    
    public PanelPlateau(Equipe e1, Equipe e2) throws EchecsException{
        if(e1.getCouleur()==Couleur.blanc&&e2.getCouleur()==Couleur.noir){
            blancs=e1;
            noirs=e2;
        }
        else if(e1.getCouleur()==Couleur.noir&&e2.getCouleur()==Couleur.blanc){
            blancs=e2;
            noirs=e1;
        }
        else{
            throw(new EchecsException("Deux équipes de couleur différentes sont nécessaires"));
        }
        lesCases = new TreeSet();
        panel=new JPanel();
        panel.setLayout(new GridLayout(8,8));
        panel.setPreferredSize(new Dimension(240,240));
        panel.setSize(240, 240);
        for(int i=0;i<64;i++){
            PanelCaseEchecs p = new PanelCaseEchecs(new Position(i%8,i/8));
            lesCases.add(p);
            panel.add(p);
            p.addActionListener(event->clique(p));
        }
        panel.setVisible(true);
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
    private void clique(PanelCaseEchecs p){
        
    }
}
