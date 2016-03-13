/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package pfiches;

import javax.swing.JLabel;

public class MonLabel extends JLabel {

    private int lig, col;

    public MonLabel(int lig, int col) {
        super();
        this.lig = lig;
        this.col = col;
    }

    public int getLig() {
        return lig;
    }

    public int getCol() {
        return col;
    }
}
