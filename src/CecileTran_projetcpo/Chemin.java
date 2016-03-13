/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package CecileTran_projetcpo;

import java.awt.Color;
import pfiches.MonLabel;

public class Chemin extends NonJoueur {

    private int ordre; //Valeur qui augmente de 1 de case en case

    public Chemin(int ordre, int l, int c) {
        super(0, l, c);
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return "C" + ordre + "";
    }

    public int getOrdre() {
        return ordre;
    }

    public void affichageImage(MonLabel monLabel) {
        monLabel.setBackground(new Color(20, 114, 127));
        monLabel.setOpaque(true);
        monLabel.setIcon(null);
        monLabel.setText(null);
    }

}
