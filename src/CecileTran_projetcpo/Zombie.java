/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package CecileTran_projetcpo;

import static CecileTran_projetcpo.Jeu.DOSS_IMAGES;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import pfiches.MonLabel;

public class Zombie extends NonJoueur {

    private int vitesse;
    private int vie;
    private int attaque;
    private int valeur;
    private int type;

    private static Image imgz0, imgz1, imgz2, imgzb;
    
      static {
        Toolkit t = Toolkit.getDefaultToolkit();
        //acces a l'image
        imgz0 = t.getImage(DOSS_IMAGES + "Zombie0.gif");
        imgz0 = imgz0.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        imgz1 = t.getImage(DOSS_IMAGES + "Zombie1.gif");
        imgz1 = imgz1.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        imgz2 = t.getImage(DOSS_IMAGES + "Zombie2.gif");
        imgz2 = imgz2.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        imgzb = t.getImage(DOSS_IMAGES + "ZombieB.gif");
        imgzb = imgzb.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    }
    
    ImageIcon img0 = new ImageIcon(imgz0);
    ImageIcon img1 = new ImageIcon(imgz1);
    ImageIcon img2 = new ImageIcon(imgz2);
    ImageIcon imgb = new ImageIcon(imgzb);

    public Zombie(int type, int vitesse, int vie, int attaque, int valeur) {
        super(0, 3, 0);
        this.vitesse = vitesse;
        this.vie = vie;
        this.attaque = attaque;
        this.valeur = valeur;
        this.type = type;
    }

    public Zombie(int type, int vitesse, int vie, int attaque, int valeur, int temps) {
        super(temps, 3, 0);
        this.vitesse = vitesse;
        this.vie = vie;
        this.attaque = attaque;
        this.valeur = valeur;
        this.type = type;
    }

  
    @Override
    public String toString() {
        return "Z{" + vie + '}';
    }

    public int getAttaque() {
        return attaque;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getVie() {
        return vie;
    }

    public int getType() {
        return type;
    }

    public int getValeur() {
        return valeur;
    }

    public boolean baisseVie(int attaque) {
        // Permet de baisser la vie du zombie lorsqu'attaque :
        vie = vie - attaque;
        if (vie <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public Zombie copierZombie(int temps) {
        return new Zombie(type, vitesse, vie, attaque, valeur, temps);
    }

    public boolean ralentit(int attaque) {
        vitesse = vitesse + attaque;
        return true;
    }

    public void reinitVitesse(int vitInit) {
        vitesse = vitInit;
    }

    public int getScore() {
        return valeur;
    }

//    public void affichageImage(MonLabel monLabel) {
//
//        if (type == 0) {
//            imgz0 = imgz0.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
//            monLabel.setIcon(new ImageIcon(imgz0));
//
//        } else if (type == 1) {
//            imgz1 = imgz1.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
//            monLabel.setIcon(new ImageIcon(imgz1));
//
//        } else if (type == 2) {
//            imgz2 = imgz2.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
//            monLabel.setIcon(new ImageIcon(imgz2));
//
//        } else if (type == 3) {
//            imgzb = imgzb.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
//            monLabel.setIcon(new ImageIcon(imgzb));
//        }
//    }
    
     public void affichageImage(MonLabel monLabel) {

        if (type == 0) {
            monLabel.setIcon(img0);

        } else if (type == 1) {
            monLabel.setIcon(img1);

        } else if (type == 2) {
            monLabel.setIcon(img2);

        } else if (type == 3) {
            monLabel.setIcon(imgb);
        }
    }

    public void affichageText(MonLabel monLabel) {
        int v = getVie();
        monLabel.setBackground(new Color(20, 114, 127));
        monLabel.setText("" + v);
        monLabel.setForeground(new Color(255, 255, 255));

        // Si le zombie est ralentit on change la couleur du texte
        if (type == 0) {
            if (vitesse > 400) {
                monLabel.setBackground(new Color(1, 111, 166));
                monLabel.setForeground(new Color(204, 255, 255));
            }
        } else if (type == 1) {
            if (vitesse > 800) {
                monLabel.setBackground(new Color(1, 111, 166));
                monLabel.setForeground(new Color(204, 255, 255));
            }
        } else if (type == 2) {
            if (vitesse > 1000) {
                monLabel.setBackground(new Color(1, 111, 166));
                monLabel.setForeground(new Color(204, 255, 255));
            }
        } else if (type == 3) {
            if (vitesse > 1200) {
                monLabel.setBackground(new Color(1, 111, 166));
                monLabel.setForeground(new Color(204, 255, 255));
            }
        }
        monLabel.setVerticalTextPosition(JLabel.BOTTOM);
        monLabel.setHorizontalTextPosition(JLabel.RIGHT);
        monLabel.setIconTextGap(-38);
    }
}
