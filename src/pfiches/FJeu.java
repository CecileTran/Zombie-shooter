/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package pfiches;

import CecileTran_projetcpo.Jeu;
import static CecileTran_projetcpo.Jeu.DOSS_IMAGES;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class FJeu extends javax.swing.JDialog {

    final int FIN = 5;
    final int COLONNES = 15;
    final int LIGNES = 7;
    final int TAILLE_TERRAIN = 105;

    private Jeu jeu;
    private MonLabel tabLabel[][];
    private Image img;
    private int tab[] = new int[2];
    private boolean click = false;
    Timer timer;

    public FJeu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setTitle("Zombie Shooter");
        InitFond();
    }

    public void nouveauJeu(String nom, int chem) {
        tabLabel = new MonLabel[LIGNES][COLONNES];
        pTerrain.removeAll();
        GridLayout gest = new GridLayout(0, COLONNES);
        pTerrain.setLayout(gest);
        for (int i = 0; i < tabLabel.length; i++) {
            for (int j = 0; j < tabLabel[i].length; j++) {
                //creer un label
                MonLabel lab = new MonLabel(i, j);
                //définir la taille du label
                Dimension dim = new Dimension(60, 60);
                lab.setPreferredSize(dim);

                lab.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        monLabMouseClicked(evt);
                    }
                });

                //ajouter le label dans le tableau
                tabLabel[i][j] = lab;
                //ajouter le label dans le panel
                pTerrain.add(tabLabel[i][j]);
            }
        }

        pTerrain.setSize(900, 420);
        this.pack(); //mettre a jour l'affichage

        jeu = new Jeu(nom);
        jeu.choixNiveau(chem);
        jeu.chargerChemin(chem);
        jeu.chargerObstacle(chem);

        lNom.setText("" + nom);
        lVie.setText("" + jeu.getVie());
        lScore.setText("" + jeu.getScore());
        lArgent.setText("" + jeu.getArgent());
        lVague.setText("" + jeu.getNbrVagues());

        jeu.afficherTerrain(tabLabel);
    }

    public void chargerJeu(String nom) {

        jeu = new Jeu(nom);

        tabLabel = new MonLabel[LIGNES][COLONNES];
        pTerrain.removeAll();
        GridLayout gest = new GridLayout(0, COLONNES);
        pTerrain.setLayout(gest);
        for (int i = 0; i < tabLabel.length; i++) {
            for (int j = 0; j < tabLabel[i].length; j++) {
                //creer un label
                MonLabel lab = new MonLabel(i, j);
                //définir la taille du label
                Dimension dim = new Dimension(60, 60);
                lab.setPreferredSize(dim);

                lab.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        monLabMouseClicked(evt);
                    }
                });

                //ajouter le label dans le tableau
                tabLabel[i][j] = lab;
                //ajouter le label dans le panel
                pTerrain.add(tabLabel[i][j]);
            }
        }

        pTerrain.setSize(900, 420);
        this.pack(); //mettre a jour l'affichage

        jeu.chargerJoueur();
        int chem = jeu.getNiveau();
        jeu.chargerChemin(chem);
        jeu.chargerObstacle(chem);
        try {
            jeu.chargerTerrain();
        } catch (IOException ex) {
        }

        lNom.setText("" + jeu.getNom());
        lVie.setText("" + jeu.getVie());
        lScore.setText("" + jeu.getScore());
        lArgent.setText("" + jeu.getArgent());
        lVague.setText("" + jeu.getNbrVagues());

        jeu.afficherTerrain(tabLabel);
    }

    private void monLabMouseClicked(MouseEvent evt) {
        //click sur un bouton du plateau
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                tabLabel[i][j].setBorder(null);
            }
        }

        JLabel lab = (MonLabel) evt.getSource();

        lab.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        int i = ((MonLabel) evt.getSource()).getLig();
        int j = ((MonLabel) evt.getSource()).getCol();
        tab[0] = i;
        tab[1] = j;

        if (jeu.tour(i, j) == true) {
            lMessage.setText(jeu.DescriptionTours(i, j));
        }
    }

    public void jouer() {

        bVague.setVisible(false);
        bSauvegarder.setVisible(false);

        timer = new Timer();

        int delay = 200;
        int period = 200;

        timer.schedule(
                new TimerTask() {

                    public void run() { // Tâche à exécuter 

                        jeu.Jeu();
                        jeu.afficherTerrain(tabLabel);
                        lVie.setText("" + jeu.getVie());
                        lArgent.setText("" + jeu.getArgent());
                        lVague.setText("" + jeu.getNbrVagues());
                        lScore.setText("" + jeu.getScore());

                        if (jeu.terrainVide() == true) {

                            timer.cancel();
                            jeu.reinitTimer();

                            bVague.setVisible(true);
                            bSauvegarder.setVisible(true);
                        }

                        if (jeu.FinDePartie() == true) {
                            timer.cancel();
                            if (jeu.getNbrVagues() < FIN || jeu.getVie() <= 0) {
                                JOptionPane.showMessageDialog(null, "", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/Perdu.png"));
                            } else {
                                JOptionPane.showMessageDialog(null, "", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/Gagne.png"));
                            }

                            // Sauvegarder le score du joueur et ajouter au tableau des scores
                            jeu.sauvegarderScore();
                            
                            //Ferme la fenetre
                            System.exit(0);

                        }
                    }
                }, delay, period);
    }

    public void achat(int type) {

        if (jeu.acheterTours(type, tab[0], tab[1]) == true) {
            jeu.acheterTours(type, tab[0], tab[1]);
            //JOptionPane.showMessageDialog(this, "","Placer une tour",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/pimages/TourPlacee.png"));
            lMessage.setText("Tour Placée !");
        } else if (jeu.PossibleOuPas(type) == false) {
            JOptionPane.showMessageDialog(this, "", "Impossible !", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/PlusArgent.png"));
        } else {
            JOptionPane.showMessageDialog(this, "", "Impossible !", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/ErreurPlace.png"));
        }
        lArgent.setText("" + jeu.getArgent());
        jeu.afficherTerrain(tabLabel);
    }

    public void ameliorerTours() {

        if ((jeu.caseVide(tab[0], tab[1]) == false) && (jeu.ameliorerTour(tab[0], tab[1]) == true)) {
            lMessage.setText("Tour ameliorée !");
        } else {
            //Mettre un message d'erreur
            lMessage.setText("Impossible");
        }
        lArgent.setText("" + jeu.getArgent());
    }

    public void InitFond() {
        //acces au toolkit
        Toolkit t = Toolkit.getDefaultToolkit();
        //acces a l'image
        img = t.getImage(DOSS_IMAGES + "FondJeu.jpg");
        img = img.getScaledInstance(1200, 800, Image.SCALE_DEFAULT);

        layeredPane.setPreferredSize(new Dimension(1200, 800));

        this.setContentPane(layeredPane);
        layeredPane.add(lFond, 1);

        lFond.setIcon(new ImageIcon(img));
        lFond.setPreferredSize(new Dimension(1200, 800));
        lFond.setBounds(0, 0, 1200, 800);

        layeredPane.add(pTerrain, 0);
        layeredPane.add(bVague, 0);
        layeredPane.add(bSauvegarder, 0);
        layeredPane.add(bQuitter, 0);
        layeredPane.add(lMessage, 0);
        layeredPane.add(lVie, 0);
        layeredPane.add(lArgent, 0);
        layeredPane.add(lTour1, 0);
        layeredPane.add(lTour0, 0);
        layeredPane.add(lTour2, 0);
        layeredPane.add(lTour3, 0);
        layeredPane.add(bAcheter, 0);
        layeredPane.add(bAmeliorer, 0);
        layeredPane.add(bSupprimer, 0);
        layeredPane.add(bPause, 0);
        layeredPane.add(jLabel1, 0);
        layeredPane.add(jLabel2, 0);
        layeredPane.add(jLabel3, 0);
        layeredPane.add(lNom, 0);
        layeredPane.add(lScore, 0);
        layeredPane.add(lVague, 0);
        layeredPane.add(txtTours, 0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoutonGroupeTOUR = new javax.swing.ButtonGroup();
        layeredPane = new javax.swing.JLayeredPane();
        lVie = new javax.swing.JLabel();
        bVague = new javax.swing.JButton();
        pTerrain = new javax.swing.JPanel();
        lArgent = new javax.swing.JLabel();
        lFond = new javax.swing.JLabel();
        lMessage = new javax.swing.JLabel();
        bSauvegarder = new javax.swing.JButton();
        bQuitter = new javax.swing.JButton();
        lVague = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lScore = new javax.swing.JLabel();
        lNom = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bAcheter = new javax.swing.JButton();
        bAmeliorer = new javax.swing.JButton();
        bSupprimer = new javax.swing.JButton();
        lTour1 = new javax.swing.JLabel();
        lTour0 = new javax.swing.JLabel();
        lTour2 = new javax.swing.JLabel();
        bPause = new javax.swing.JButton();
        bTour0 = new javax.swing.JRadioButton();
        bTour1 = new javax.swing.JRadioButton();
        bTour2 = new javax.swing.JRadioButton();
        lTour3 = new javax.swing.JLabel();
        bTour3 = new javax.swing.JRadioButton();
        txtTours = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lVie.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        lVie.setForeground(new java.awt.Color(211, 157, 225));
        lVie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pimages/Coeur.gif"))); // NOI18N
        lVie.setText("Vie");

        bVague.setBackground(new java.awt.Color(255, 202, 41));
        bVague.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 20)); // NOI18N
        bVague.setForeground(new java.awt.Color(0, 102, 153));
        bVague.setText("LANCER LA VAGUE !");
        bVague.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVagueActionPerformed(evt);
            }
        });

        pTerrain.setBackground(new java.awt.Color(72, 137, 61));
        pTerrain.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pTerrain.setForeground(new java.awt.Color(240, 240, 240));
        pTerrain.setPreferredSize(new java.awt.Dimension(900, 420));

        javax.swing.GroupLayout pTerrainLayout = new javax.swing.GroupLayout(pTerrain);
        pTerrain.setLayout(pTerrainLayout);
        pTerrainLayout.setHorizontalGroup(
            pTerrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 896, Short.MAX_VALUE)
        );
        pTerrainLayout.setVerticalGroup(
            pTerrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        lArgent.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        lArgent.setForeground(new java.awt.Color(186, 228, 165));
        lArgent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pimages/Icone_Dollar.png"))); // NOI18N
        lArgent.setText("1000");

        lMessage.setBackground(new java.awt.Color(20, 114, 127));
        lMessage.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 24)); // NOI18N
        lMessage.setForeground(new java.awt.Color(4, 37, 41));
        lMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lMessage.setText("Description ICI");
        lMessage.setToolTipText("");
        lMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bSauvegarder.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bSauvegarder.setForeground(new java.awt.Color(11, 143, 77));
        bSauvegarder.setText("Sauvegarder");
        bSauvegarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSauvegarderActionPerformed(evt);
            }
        });

        bQuitter.setBackground(new java.awt.Color(255, 102, 102));
        bQuitter.setFont(new java.awt.Font("Impact", 0, 20)); // NOI18N
        bQuitter.setForeground(new java.awt.Color(98, 8, 8));
        bQuitter.setText("Quitter");
        bQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuitterActionPerformed(evt);
            }
        });

        lVague.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        lVague.setForeground(new java.awt.Color(4, 37, 41));
        lVague.setText("0");

        jLabel2.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(21, 59, 64));
        jLabel2.setText("Score :");

        jLabel3.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(21, 59, 64));
        jLabel3.setText("Vague");

        lScore.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        lScore.setForeground(new java.awt.Color(4, 37, 41));
        lScore.setText("0");

        lNom.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        lNom.setForeground(new java.awt.Color(4, 37, 41));
        lNom.setText("Nom");

        jLabel1.setFont(new java.awt.Font("Impact", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 59, 64));
        jLabel1.setText("Nom :");

        bAcheter.setBackground(new java.awt.Color(7, 94, 106));
        bAcheter.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bAcheter.setForeground(new java.awt.Color(206, 222, 233));
        bAcheter.setText("Acheter");
        bAcheter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAcheterActionPerformed(evt);
            }
        });

        bAmeliorer.setBackground(new java.awt.Color(7, 94, 106));
        bAmeliorer.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bAmeliorer.setForeground(new java.awt.Color(206, 222, 233));
        bAmeliorer.setText("Améliorer");
        bAmeliorer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAmeliorerActionPerformed(evt);
            }
        });

        bSupprimer.setBackground(new java.awt.Color(7, 94, 106));
        bSupprimer.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bSupprimer.setForeground(new java.awt.Color(206, 222, 233));
        bSupprimer.setText("Supprimer");
        bSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSupprimerActionPerformed(evt);
            }
        });

        lTour1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pimages/tour1.png"))); // NOI18N
        lTour1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lTour1MouseClicked(evt);
            }
        });

        lTour0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pimages/Tour0.png"))); // NOI18N
        lTour0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lTour0MouseClicked(evt);
            }
        });

        lTour2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pimages/tour2.png"))); // NOI18N
        lTour2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lTour2MouseClicked(evt);
            }
        });

        bPause.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bPause.setForeground(new java.awt.Color(11, 143, 77));
        bPause.setText("Pause");
        bPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPauseActionPerformed(evt);
            }
        });

        BoutonGroupeTOUR.add(bTour0);

        BoutonGroupeTOUR.add(bTour1);

        BoutonGroupeTOUR.add(bTour2);

        lTour3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pimages/TourRal0.png"))); // NOI18N
        lTour3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lTour3MouseClicked(evt);
            }
        });

        BoutonGroupeTOUR.add(bTour3);

        txtTours.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        txtTours.setForeground(new java.awt.Color(248, 214, 213));
        txtTours.setText("Tours :");

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(pTerrain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layeredPaneLayout.createSequentialGroup()
                                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lVague, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layeredPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lNom, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lScore, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addComponent(bTour0)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTour0)
                                .addGap(19, 19, 19)
                                .addComponent(bTour1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTour1))
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(txtTours)))
                        .addGap(18, 18, 18)
                        .addComponent(bTour2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lTour2)
                        .addGap(22, 22, 22)
                        .addComponent(bTour3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lTour3)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(bAcheter)
                                .addGap(32, 32, 32)
                                .addComponent(bAmeliorer)
                                .addGap(28, 28, 28)
                                .addComponent(bSupprimer))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                                .addGap(562, 562, 562)
                                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lVie, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lArgent, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFond, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(bVague, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(bPause, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(bSauvegarder, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layeredPaneLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lFond, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addContainerGap(107, Short.MAX_VALUE)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(lNom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layeredPaneLayout.createSequentialGroup()
                                        .addComponent(txtTours)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                                .addComponent(lTour3)
                                                .addGap(18, 18, 18))
                                            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bTour3)
                                                .addComponent(lTour2))
                                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lTour1)
                                                    .addComponent(bTour2))
                                                .addGap(10, 10, 10))))
                                    .addGroup(layeredPaneLayout.createSequentialGroup()
                                        .addComponent(lVie)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lArgent, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)
                                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bTour0, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lTour0, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(bAcheter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bAmeliorer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                                                .addComponent(bTour1)
                                                .addGap(27, 27, 27)))))
                                .addGap(19, 19, 19)))))
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(pTerrain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lScore))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lVague))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bPause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSauvegarder)
                        .addComponent(bQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bVague, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        layeredPane.setLayer(lVie, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bVague, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(pTerrain, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lArgent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lFond, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bSauvegarder, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bQuitter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lVague, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lScore, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lNom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bAcheter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bAmeliorer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bSupprimer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lTour1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lTour0, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lTour2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bPause, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bTour0, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bTour1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bTour2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lTour3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bTour3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(txtTours, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuitterActionPerformed

        System.exit(0);
    }//GEN-LAST:event_bQuitterActionPerformed

    private void bVagueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVagueActionPerformed

        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                tabLabel[i][j].setBorder(null);
            }
        }

        jeu.lancerVague();
        jouer();
        jeu.incremNbrVagues();
    }//GEN-LAST:event_bVagueActionPerformed

    private void bAcheterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAcheterActionPerformed

        if (bTour0.isSelected() == true) {
            achat(0);
        } else if (bTour1.isSelected() == true) {
            achat(1);
        } else if (bTour2.isSelected() == true) {
            achat(2);
        } else if (bTour3.isSelected() == true) {
            achat(3);
        }
    }//GEN-LAST:event_bAcheterActionPerformed

    private void bSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSupprimerActionPerformed

        if (jeu.verifTour(tab[0], tab[1]) == true) {
            jeu.supprimerTour(tab[0], tab[1]);
            JOptionPane.showMessageDialog(this, "", "Suppression", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/TourSuppr.png"));
            jeu.afficherTerrain(tabLabel);
        } else {
            JOptionPane.showMessageDialog(this, "Il n'y a pas de tour à cette case !");
        }
    }//GEN-LAST:event_bSupprimerActionPerformed

    private void lTour0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lTour0MouseClicked

        lMessage.setText("" + jeu.DescriptionTours(0));
        bTour0.setSelected(true);
        if (bTour0.isSelected()) {
            lTour0.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            lTour1.setBorder(null);
            lTour2.setBorder(null);
            lTour3.setBorder(null);
        }
    }//GEN-LAST:event_lTour0MouseClicked

    private void lTour1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lTour1MouseClicked

        lMessage.setText("" + jeu.DescriptionTours(1));
        bTour1.setSelected(true);
        if (bTour1.isSelected()) {
            lTour1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            lTour0.setBorder(null);
            lTour2.setBorder(null);
            lTour3.setBorder(null);
        }
    }//GEN-LAST:event_lTour1MouseClicked

    private void lTour2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lTour2MouseClicked

        lMessage.setText("" + jeu.DescriptionTours(2));
        bTour2.setSelected(true);
        if (bTour2.isSelected()) {
            lTour2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            lTour0.setBorder(null);
            lTour1.setBorder(null);
            lTour3.setBorder(null);
        }
    }//GEN-LAST:event_lTour2MouseClicked

    private void lTour3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lTour3MouseClicked

        lMessage.setText("" + jeu.DescriptionTours(3));
        bTour3.setSelected(true);
        if (bTour3.isSelected()) {
            lTour3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            lTour1.setBorder(null);
            lTour2.setBorder(null);
            lTour0.setBorder(null);
        }
    }//GEN-LAST:event_lTour3MouseClicked

    private void bSauvegarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSauvegarderActionPerformed

        jeu.sauvegarderJoueur();
        jeu.sauvegarderTerrain();
        JOptionPane.showMessageDialog(this, "", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/Sauvegarde.png"));
    }//GEN-LAST:event_bSauvegarderActionPerformed

    private void bPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPauseActionPerformed

        if (click == false) {
            timer.cancel();
            click = true;
        } else {

            timer = new Timer();
            int delay = 200;
            int period = 200;
            timer.schedule(
                    new TimerTask() {

                        public void run() { // Tâche à exécuter 
                            jeu.Jeu();
                            jeu.afficherTerrain(tabLabel);
                            lVie.setText("" + jeu.getVie());
                            lArgent.setText("" + jeu.getArgent());
                            lVague.setText("" + jeu.getNbrVagues());
                            lScore.setText("" + jeu.getScore());

                            if (jeu.terrainVide() == true) {

                                timer.cancel();
                                jeu.reinitTimer();

                                bVague.setVisible(true);
                                bSauvegarder.setVisible(true);
                            }

                            if (jeu.FinDePartie() == true) {
                                timer.cancel();
                                if (jeu.getNbrVagues() < FIN || jeu.getVie() <= 0) {
                                    JOptionPane.showMessageDialog(null, "", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/Perdu.png"));
                                } else {
                                    JOptionPane.showMessageDialog(null, "", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/pimages/Gagne.png"));
                                }

                                //setVisible(false);
                                //getParent().setVisible(true);
                                System.exit(0);

                                // Sauvegarder le score du joueur et ajouter au tableau des scores
                                jeu.sauvegarderScore();
                            }
                        }
                    }, delay, period);
            click = false;
        }
    }//GEN-LAST:event_bPauseActionPerformed

    private void bAmeliorerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAmeliorerActionPerformed

        ameliorerTours();
        jeu.afficherTerrain(tabLabel);
    }//GEN-LAST:event_bAmeliorerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                FJeu dialog = new FJeu(new javax.swing.JFrame(), true);

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BoutonGroupeTOUR;
    private javax.swing.JButton bAcheter;
    private javax.swing.JButton bAmeliorer;
    private javax.swing.JButton bPause;
    private javax.swing.JButton bQuitter;
    private javax.swing.JButton bSauvegarder;
    private javax.swing.JButton bSupprimer;
    private javax.swing.JRadioButton bTour0;
    private javax.swing.JRadioButton bTour1;
    private javax.swing.JRadioButton bTour2;
    private javax.swing.JRadioButton bTour3;
    private javax.swing.JButton bVague;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lArgent;
    private javax.swing.JLabel lFond;
    private javax.swing.JLabel lMessage;
    private javax.swing.JLabel lNom;
    private javax.swing.JLabel lScore;
    private javax.swing.JLabel lTour0;
    private javax.swing.JLabel lTour1;
    private javax.swing.JLabel lTour2;
    private javax.swing.JLabel lTour3;
    private javax.swing.JLabel lVague;
    private javax.swing.JLabel lVie;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JPanel pTerrain;
    private javax.swing.JLabel txtTours;
    // End of variables declaration//GEN-END:variables
}
