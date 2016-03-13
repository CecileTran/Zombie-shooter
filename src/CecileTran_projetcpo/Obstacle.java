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
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;
import pfiches.MonLabel;

public class Obstacle extends NonJoueur {

    private static Image img;
    private boolean deja;

    Toolkit t = Toolkit.getDefaultToolkit();

    public Obstacle(int l, int c) {
        super(0, l, c);
        deja = false;
    }

    public void affichageImage(MonLabel monLabel) {

        if (deja == false) {
            Random rand = new Random();
            //Tire un nombre aléatoire entre 0 et 1 (compris)
            int nbreAlT = rand.nextInt(2);
            img = t.getImage(DOSS_IMAGES + "Obstacle" + nbreAlT + ".png");
            img = img.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(), Image.SCALE_DEFAULT);
            monLabel.setIcon(new ImageIcon(img));
        }
        deja = true;
    }
}
