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

public class Tour extends NonJoueur {

    private int portee;
    private int cout; // Lors de l'achat
    private int vitesse; // Vitesse de tir (degats par seconde)
    private int type;
    private int attaque;

    private static Image imgt0, imgt1, imgt2, imgt3, imgt4, imgt5, imgt6, imgt7;
    ImageIcon img0 = new ImageIcon (imgt0);
    ImageIcon img1 = new ImageIcon (imgt1);
    ImageIcon img2 = new ImageIcon (imgt2);
    ImageIcon img3 = new ImageIcon (imgt3);
    ImageIcon img4 = new ImageIcon (imgt4);
    ImageIcon img5 = new ImageIcon (imgt5);
    ImageIcon img6 = new ImageIcon (imgt6);
    ImageIcon img7 = new ImageIcon (imgt7);
    
    public Tour(int type, int portee, int cout, int vitesse, int attaque, int ligne, int colonne) {
        super(0, ligne, colonne);
        this.type = type;
        this.portee = portee;
        this.cout = cout;
        this.vitesse = vitesse;
        this.attaque = attaque;
    }

    static {
        // acces au toolkit
        Toolkit t = Toolkit.getDefaultToolkit();
        // acces a l'image        
        imgt0 = t.getImage(DOSS_IMAGES + "Tour0.png");
        imgt1 = t.getImage(DOSS_IMAGES + "tour1.png");
        imgt2 = t.getImage(DOSS_IMAGES + "tour2.png");
        imgt3 = t.getImage(DOSS_IMAGES + "TourRal0.png");
        imgt4 = t.getImage(DOSS_IMAGES + "tourEv0.png");
        imgt5 = t.getImage(DOSS_IMAGES + "TourEv1.png");
        imgt6 = t.getImage(DOSS_IMAGES + "TourEv2.png");
        imgt7 = t.getImage(DOSS_IMAGES + "TourRalEv0.png");
    }

    public Tour copierTour() {
        return new Tour(type, portee, cout, vitesse, attaque, 0, 0);
    }

    public int getType() {
        return type;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getVitesse() {
        return vitesse;
    }

    public String toString() {
        return type + "";
    }

    public int getCout() {
        return cout;
    }

    public int getPortee() {
        return portee;
    }

    public boolean isTourOff() {
        if (this instanceof TourOff) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTourRal() {
        if (this instanceof TourRal) {
            return true;
        } else {
            return false;
        }
    }

    public void affichageImage(MonLabel monLabel) {

        if (type == 0) {
            imgt0 = imgt0.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img0);

        } else if (type == 1) {
            imgt1 = imgt1.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img1);

        } else if (type == 2) {
            imgt2 = imgt2.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img2);

        }
        if (type == 3) {
            imgt3 = imgt3.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img3);

        } else if (type == 4) {
            imgt4 = imgt4.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img4);

        } else if (type == 5) {
            imgt5 = imgt5.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img5);

        } else if (type == 6) {
            imgt6 = imgt6.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img5);

        } else if (type == 7) {
            imgt7 = imgt7.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(img7);
        }
    }

    public void affichageText(MonLabel monLabel) {
        int v = getAttaque();
        monLabel.setText("" + v);
        monLabel.setForeground(new Color(255, 255, 255));
        monLabel.setVerticalTextPosition(JLabel.BOTTOM);
        monLabel.setHorizontalTextPosition(JLabel.RIGHT);
        monLabel.setIconTextGap(-26);
    }
}
