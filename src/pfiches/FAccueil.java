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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class FAccueil extends javax.swing.JFrame {

    private FJeu fichJeu;
    private FScores fichScores;
    private Jeu jeu;
    private Image img;

    public FAccueil() {

        initComponents();
        fichJeu = new FJeu(this, false);
        fichScores = new FScores(this, false);
        setSize(1000, 645);
        setTitle("Zombie Shooter");

        InitFond();

//        Audio son = new Audio();
//        son.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        layeredPane = new javax.swing.JLayeredPane();
        lFond = new javax.swing.JLabel();
        lNom = new javax.swing.JLabel();
        tfNom = new javax.swing.JTextField();
        lNiveaux = new javax.swing.JLabel();
        bFacile = new javax.swing.JRadioButton();
        bMoyen = new javax.swing.JRadioButton();
        bDifficile = new javax.swing.JRadioButton();
        bJouer = new javax.swing.JButton();
        bCharger = new javax.swing.JButton();
        bQuitter = new javax.swing.JButton();
        bScores = new javax.swing.JButton();
        bCommencer = new javax.swing.JButton();
        lMessage = new javax.swing.JLabel();
        bAnnuler = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lNom.setFont(new java.awt.Font("Impact", 0, 26)); // NOI18N
        lNom.setText("Nom :");

        tfNom.setToolTipText("");
        tfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomActionPerformed(evt);
            }
        });

        lNiveaux.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        lNiveaux.setText("Niveaux :");

        buttonGroup1.add(bFacile);
        bFacile.setFont(new java.awt.Font("Impact", 0, 20)); // NOI18N
        bFacile.setSelected(true);
        bFacile.setText("Facile");
        bFacile.setBorderPainted(true);

        buttonGroup1.add(bMoyen);
        bMoyen.setFont(new java.awt.Font("Impact", 0, 20)); // NOI18N
        bMoyen.setText("Moyen");
        bMoyen.setBorderPainted(true);

        buttonGroup1.add(bDifficile);
        bDifficile.setFont(new java.awt.Font("Impact", 0, 20)); // NOI18N
        bDifficile.setText("Difficile");
        bDifficile.setBorderPainted(true);

        bJouer.setBackground(new java.awt.Color(255, 0, 0));
        bJouer.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 32)); // NOI18N
        bJouer.setForeground(new java.awt.Color(245, 235, 235));
        bJouer.setText("JOUER");
        bJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bJouerActionPerformed(evt);
            }
        });

        bCharger.setBackground(new java.awt.Color(255, 0, 0));
        bCharger.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bCharger.setForeground(new java.awt.Color(245, 235, 235));
        bCharger.setText("Charger");
        bCharger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChargerActionPerformed(evt);
            }
        });

        bQuitter.setBackground(new java.awt.Color(51, 153, 255));
        bQuitter.setFont(new java.awt.Font("Impact", 0, 26)); // NOI18N
        bQuitter.setForeground(new java.awt.Color(237, 244, 255));
        bQuitter.setText("Quitter");
        bQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuitterActionPerformed(evt);
            }
        });

        bScores.setBackground(new java.awt.Color(51, 153, 255));
        bScores.setFont(new java.awt.Font("Impact", 0, 26)); // NOI18N
        bScores.setForeground(new java.awt.Color(237, 244, 255));
        bScores.setText("Scores");
        bScores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bScoresActionPerformed(evt);
            }
        });

        bCommencer.setBackground(new java.awt.Color(255, 0, 0));
        bCommencer.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        bCommencer.setForeground(new java.awt.Color(245, 235, 235));
        bCommencer.setText("Nouvelle partie");
        bCommencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCommencerActionPerformed(evt);
            }
        });

        lMessage.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 24)); // NOI18N

        bAnnuler.setBackground(new java.awt.Color(118, 83, 83));
        bAnnuler.setFont(new java.awt.Font("Impact", 0, 20)); // NOI18N
        bAnnuler.setForeground(new java.awt.Color(231, 217, 217));
        bAnnuler.setText("Annuler");
        bAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(bScores, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(bAnnuler)
                .addGap(33, 33, 33)
                .addComponent(bJouer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(bQuitter)
                .addGap(119, 119, 119))
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lFond, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(bFacile)
                        .addGap(18, 18, 18)
                        .addComponent(bMoyen)
                        .addGap(18, 18, 18)
                        .addComponent(bDifficile))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(lNom)
                        .addGap(18, 18, 18)
                        .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(bCommencer)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lNiveaux))
                            .addGroup(layeredPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCharger, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                .addComponent(lFond, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNom))
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bQuitter)
                            .addComponent(bScores))
                        .addGap(38, 38, 38))
                    .addGroup(layeredPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCommencer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCharger, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(lNiveaux)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bFacile)
                            .addComponent(bMoyen)
                            .addComponent(bDifficile))
                        .addGap(18, 18, 18)
                        .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bJouer, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(69, Short.MAX_VALUE))))
        );
        layeredPane.setLayer(lFond, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lNom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(tfNom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lNiveaux, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bFacile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bMoyen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bDifficile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bJouer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bCharger, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bQuitter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bScores, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bCommencer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(lMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(bAnnuler, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomActionPerformed

    }//GEN-LAST:event_tfNomActionPerformed

    private void bScoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bScoresActionPerformed

        this.setVisible(false); // Ferme la fiche accueil
        fichScores.setLocationRelativeTo(fichScores.getParent()); // on centre la fenêtre
        fichScores.setVisible(true); // Ouvre la fiche Scores
    }//GEN-LAST:event_bScoresActionPerformed

    private void bJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bJouerActionPerformed

        if (tfNom.getText().trim().length() != 0) {
            this.setVisible(false); // Ferme la fiche accueil
            fichJeu.setLocationRelativeTo(fichJeu.getParent()); // on centre la fenêtre
            fichJeu.setPreferredSize(new Dimension(1200, 845));
            fichJeu.setVisible(true); // Ouvre la fiche Jeu

            String nom = tfNom.getText();

            int chem = 0;

            if (bFacile.isSelected() == true) {
                chem = 0;
            } else if (bMoyen.isSelected() == true) {
                chem = 1;
            } else if (bDifficile.isSelected() == true) {
                chem = 2;
            }

            fichJeu.nouveauJeu(nom, chem);
        } else {
            lMessage.setForeground(new Color(255, 0, 0));
            lMessage.setText(" Veuillez entrer votre nom !");
        }
    }//GEN-LAST:event_bJouerActionPerformed

    private void bQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuitterActionPerformed

        System.exit(0);
    }//GEN-LAST:event_bQuitterActionPerformed

    private void bCommencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCommencerActionPerformed

        if (tfNom.getText().trim().length() != 0) {
            lNiveaux.setVisible(true);
            bFacile.setVisible(true);
            bMoyen.setVisible(true);
            bDifficile.setVisible(true);
            bJouer.setVisible(true);
            bCommencer.setVisible(false);
            bCharger.setVisible(false);
            bAnnuler.setVisible(true);
        } else {
            lMessage.setForeground(new Color(255, 0, 0));
            lMessage.setText(" Veuillez entrer votre nom !");
        }
    }//GEN-LAST:event_bCommencerActionPerformed

    private void bChargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChargerActionPerformed

        jeu = new Jeu("Accueil");

        String nom = tfNom.getText();

        jeu.chargerJoueur();

        if (nom.equals(jeu.getNom())) {
            fichJeu.chargerJeu(nom);
            this.setVisible(false); // Ferme la fiche accueil
            fichJeu.setLocationRelativeTo(fichJeu.getParent()); // on centre la fenêtre
            fichJeu.setPreferredSize(new Dimension(1200, 845));
            fichJeu.setVisible(true); // Ouvre la fiche Jeu*/
        } else {
            lMessage.setForeground(new Color(0, 0, 0));
            lMessage.setText(" Dernière sauvegarde : " + jeu.getNom());
        }
    }//GEN-LAST:event_bChargerActionPerformed

    private void bAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnnulerActionPerformed

        lNiveaux.setVisible(false);
        bFacile.setVisible(false);
        bMoyen.setVisible(false);
        bDifficile.setVisible(false);
        bJouer.setVisible(false);
        bCommencer.setVisible(true);
        bCharger.setVisible(true);
        bAnnuler.setVisible(false);
    }//GEN-LAST:event_bAnnulerActionPerformed

    private void InitFond() {
        //acces au toolkit
        Toolkit t = Toolkit.getDefaultToolkit();
        //acces a l'image
        img = t.getImage(DOSS_IMAGES + "Accueil.jpg");
        img = img.getScaledInstance(1000, 600, Image.SCALE_DEFAULT);

        layeredPane.setPreferredSize(new Dimension(1000, 600));

        this.setContentPane(layeredPane);
        layeredPane.add(lFond, 1);

        lFond.setIcon(new ImageIcon(img));
        //lFond.setPreferredSize(new Dimension(1000, 600));
        lFond.setBounds(0, 0, 1000, 600);

        layeredPane.add(lNom, 0);
        layeredPane.add(tfNom, 0);
        layeredPane.add(bCommencer, 0);
        layeredPane.add(bJouer, 0);
        layeredPane.add(bCharger, 0);
        layeredPane.add(bFacile, 0);
        layeredPane.add(bMoyen, 0);
        layeredPane.add(bDifficile, 0);
        layeredPane.add(lNiveaux, 0);
        layeredPane.add(bScores, 0);
        layeredPane.add(bQuitter, 0);
        layeredPane.add(lMessage, 0);
        layeredPane.add(bAnnuler, 0);

        lNiveaux.setVisible(false);
        bFacile.setVisible(false);
        bMoyen.setVisible(false);
        bDifficile.setVisible(false);
        bJouer.setVisible(false);
        bAnnuler.setVisible(false);

        lMessage.setText("Bienvenue sur Zombie Shooter !");
    }

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
            java.util.logging.Logger.getLogger(FAccueil.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FAccueil.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FAccueil.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FAccueil.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

                    FAccueil accueil = new FAccueil(); // on créé une instance de l'objet
                    accueil.setVisible(true);
                    accueil.setLocationRelativeTo(accueil.getParent()); // on centre la fenêtre
                } catch (Exception e) {
                }

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnnuler;
    private javax.swing.JButton bCharger;
    private javax.swing.JButton bCommencer;
    private javax.swing.JRadioButton bDifficile;
    private javax.swing.JRadioButton bFacile;
    private javax.swing.JButton bJouer;
    private javax.swing.JRadioButton bMoyen;
    private javax.swing.JButton bQuitter;
    private javax.swing.JButton bScores;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lFond;
    private javax.swing.JLabel lMessage;
    private javax.swing.JLabel lNiveaux;
    private javax.swing.JLabel lNom;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JTextField tfNom;
    // End of variables declaration//GEN-END:variables
}
