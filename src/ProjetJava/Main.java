/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetJava;

import BatailleNavale.BatailleNavale;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 * Gere les interractions entre les différents JPanel au sein de la mainFrame.
 *
 * @author deux
 */
public class Main {

    private Identification id;
    private javax.swing.JFrame mainFrame;
    private String[] jeux = {"Pendu", "Bataille Navale"};

    /**
     * Creates new form ConnexionGUI
     */
    public Main() {

        mainFrame = new javax.swing.JFrame();
        mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);

        //Tentative de lire dans le ficher de sauvegarde, sinon cree une liste 
        //d'utilisateurs vierge.
        try {
            id = new Identification(".save");
        } catch (FileNotFoundException ex) {
            id = new Identification();
        }

        //Nouvelle methode pour quitter permettant de sauvegarder avant de quitter.
        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("Boite De Jeux");
        mainFrame.setMaximumSize(new java.awt.Dimension(1280, 720));
        mainFrame.setMinimumSize(new java.awt.Dimension(640, 360));
        mainFrame.setPreferredSize(new java.awt.Dimension(854, 480));
        mainFrame.getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                id.sauvegarde();
                System.exit(0);
            }
        };

        mainFrame.addWindowListener(exitListener);
        interfaceConnexion();

        mainFrame.setVisible(true);
        mainFrame.requestFocus();

    }

    /**
     * Creation et affichage de la fenetre de connexion.
     */
    private void interfaceConnexion() {
        mainFrame.getContentPane().removeAll();
        ConnexionPanel connexion = new ConnexionPanel();
        //le bouton inscription renvoid vers l'interface inscription.
        connexion.getInscriptionButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceInscription(false);
        });

        connexion.getConnexionButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            String pseudo = connexion.getPseudoField();
            String mdp = connexion.getPasswordField();
            try {
                Membre m = id.connexion(pseudo, mdp);

                if (m.estAdmin()) {
                    interfaceAdmin(m);
                } else {
                    interfaceChoixJeu(m);
                }

            } catch (ConnexionException ex) {
                JOptionPane.showMessageDialog(null, "Connexion impossible, votre mot de passe ne correspond pas à ce compte.");
            }
        });
        connexion.getInviteButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            String pseudo = connexion.getPseudoField();
            if (Utilisateur.estPseudoValide(pseudo)) {
                this.interfaceChoixJeu(new Utilisateur(pseudo));
            } else {
                interfaceChoixJeu(new Utilisateur("Invite"));
            }
        }
        );

        mainFrame.getContentPane().add(connexion);

        mainFrame.pack();
    }

    /**
     * Initialisation de la fenetre d'inscription.
     *
     * @param admin true si on vient de l'interfaceAdmin.
     */
    private void interfaceInscription(boolean admin) {
        mainFrame.getContentPane().removeAll();
        InscriptionPanel inscription = new InscriptionPanel();
        inscription.getRetourButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceConnexion();
        });

        inscription.getInscriptionButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            String pseudo = inscription.getPseudoField();
            String mdp = inscription.getPasswordField();
            if (id.membreExiste(pseudo)) {
                JOptionPane.showMessageDialog(null, "Pseudo déjà utilisé");
            } else if (id.addMembre(pseudo, mdp, admin)) {
                id.sauvegarde();
                interfaceConnexion();

            } else {
                JOptionPane.showMessageDialog(null, "Votre pseudo doit contenir entre 5 et 15 charactères "
                        + "et votre mot de passe doivent contenir entre 6 et 30"
                        + "dont uniquement des lettres et des chiffres.");
            }
        });

        mainFrame.getContentPane().add(inscription);
        mainFrame.pack();
    }

    /**
     * Interface permettant de choisir un jeu ou de modifier son mot de passe.
     *
     * @param u Utilisateur connecte.
     */
    private void interfaceChoixJeu(Utilisateur u) {
        mainFrame.getContentPane().removeAll();
        ChoixJeuPanel choix = new ChoixJeuPanel();

        //Tableau contenant tous les jeux.
        //Ajout d'une image pour chaque jeu.
        for (String jeu : jeux) {
            ImageJeuPanel img = new ImageJeuPanel(jeu, true);
            img.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    interfaceInfoJeu(jeu, u);
                }
            });

            choix.getGroupeJeuPanel().add(img);
        }

        choix.getRetourButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceConnexion();
            if (u instanceof Membre) {
                Membre m = (Membre) u;
                if (m.estAdmin()) {
                    interfaceAdmin(m);
                }
            }
        });
        javax.swing.JButton chPwd = choix.getChgPwdButton();
        if (u instanceof Membre) {
            Membre m = (Membre) u;
            chPwd.addActionListener((java.awt.event.ActionEvent evt) -> {
                interfaceChMdp(m.estAdmin(), m);
            });
        } else {
            chPwd.setEnabled(false);
        }

        choix.getStatistiquesButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            new StatsFrame(id);
        });
        mainFrame.getContentPane().add(choix);
        mainFrame.pack();
    }

    /**
     * Interface donnant toutes les informations sur un jeu donne.
     *
     * @param jeu Nom du jeu duquel on veut les informations.
     * @param u Utilisateur connecte.
     */
    public void interfaceInfoJeu(String jeu, Utilisateur u) {
        mainFrame.getContentPane().removeAll();
        InfoJeuPanel info = new InfoJeuPanel(this.id, jeu);
        info.getJouerButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceJeu(jeu, u, info.getDifficulte());
        });

        info.getRetourButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceChoixJeu(u);
        });

        mainFrame.getContentPane().add(info);
        mainFrame.pack();
    }

    /**
     * Interface utilisateur permettant de jouer à un jeu.
     *
     * @param jeu Nom du jeu auquel on souhaite jouer.
     * @param u Utilisateur connecte.
     * @param diff Niveau de difficulté (de 0 a 2).
     */
    private void interfaceJeu(String jeu, Utilisateur u, int diff) {
        mainFrame.getContentPane().removeAll();
        JeuPanel GUI = new JeuPanel(jeu);
        switch (jeu) {
            case "Pendu":
                try {
                    Pendu.JeuPendu pendu = new Pendu.JeuPendu(u, diff) {
                        @Override
                        public void quitter() {
                            interfaceInfoJeu(jeu, u);
                        }
                    };
                    Pendu.PenduPanel panelJeu = new Pendu.PenduPanel(pendu) {
                        @Override
                        public void update(Observable o, Object arg) {
                            super.update(o, arg);
                            mainFrame.pack();
                        }
                    };
                    pendu.addObserver(panelJeu);

                    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
                    gridBagConstraints.gridx = 0;
                    gridBagConstraints.gridy = 1;
                    gridBagConstraints.gridwidth = 2;
                    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                    GUI.getQuitButton().addActionListener((java.awt.event.ActionEvent evt) -> {
                        if (u instanceof Membre) {
                            Membre m = (Membre) u;
                            m.incrementPerdu(jeu);
                        }
                        interfaceInfoJeu(jeu, u);
                    });
                    GUI.add(panelJeu, gridBagConstraints);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Dictionnaire absent.");
                    interfaceInfoJeu(jeu, u);
                }
                break;
            case "Bataille Navale":
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        BatailleNavale b = new BatailleNavale(u, diff) {
                            @Override
                            public void quitter() {
                                super.quitter();
                                interfaceInfoJeu(jeu, u);
                            }
                        };
                        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
                        gridBagConstraints.gridx = 0;
                        gridBagConstraints.gridy = 1;
                        gridBagConstraints.gridwidth = 2;
                        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                        GUI.getQuitButton().addActionListener((java.awt.event.ActionEvent evt) -> {
                            if (u instanceof Membre) {
                                Membre m = (Membre) u;
                                m.incrementPerdu(jeu);
                            }
                            b.quitter();
                            interfaceInfoJeu(jeu, u);
                        });
                        GUI.add(b.getPanel(), gridBagConstraints);
                        mainFrame.getContentPane().add(GUI);
                        mainFrame.pack();
                        b.jeu();
                    }
                };
                t.start();
                break;
        }

        GUI.getQuitButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            if (u instanceof Membre) {
                Membre m = (Membre) u;
                m.incrementPerdu(jeu);
            }
            interfaceInfoJeu(jeu, u);
        });
        mainFrame.getContentPane().add(GUI);
        mainFrame.pack();
    }

    /**
     * Cree et affiche l'interface Amin.
     *
     * @param m Membre administrateur qui c'est connecte.
     */
    private void interfaceAdmin(Membre m) {
        mainFrame.getContentPane().removeAll();
        AdminPanel admin = new AdminPanel(id);
        admin.getRetourButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceConnexion();
        });
        admin.getAddAdminButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceInscription(true);
        });
        admin.getChMdpButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceChMdp(true, m);
        });

        admin.getJouerButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            interfaceChoixJeu(m);
        });

        mainFrame.getContentPane().add(admin);

        mainFrame.pack();
    }

    /**
     * Interface permettant a un Membre de changer de mot de passe.
     *
     * @param admin true si le Membre viens de l'interfaceAdmin.
     * @param m Membre duquel on veut changer le mot de passe.
     */
    private void interfaceChMdp(boolean admin, Membre m) {
        mainFrame.getContentPane().removeAll();
        ChangerMdpPannel chMdp = new ChangerMdpPannel();
        if (admin) {
            chMdp.getRetourButton().addActionListener((java.awt.event.ActionEvent evt) -> {
                interfaceAdmin(m);
            });
        } else {
            chMdp.getRetourButton().addActionListener((java.awt.event.ActionEvent evt) -> {
                interfaceChoixJeu(m);
            });
        }

        chMdp.getValiderButton().addActionListener((java.awt.event.ActionEvent evt) -> {
            String newPwd = chMdp.getNewPassword();
            if (chMdp.isConfirmation() && m.connexion(chMdp.getOldPassword()) && Membre.estMdpValide(newPwd)) {
                m.setMotDePasse(newPwd);
                if (admin) {
                    interfaceAdmin(m);
                } else {
                    interfaceChoixJeu(m);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Les nouveaux mots de passe ne sont pas valides");
            }
        });

        mainFrame.getContentPane().add(chMdp);
        mainFrame.pack();
    }

}
