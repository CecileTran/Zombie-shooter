/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package CecileTran_projetcpo;

import pfiches.MonLabel;

public class NonJoueur {

    private int temps;
    private int ligne;
    private int colonne;

    public NonJoueur(int temps, int ligne, int colonne) {

        this.temps = temps;
    }

    public void initCoord(int l, int c) {
        ligne = l;
        colonne = c;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public int getOrdre() {
        return 0;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public boolean isZombie() {
        if (this instanceof Zombie) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isChemin() {
        if (this instanceof Chemin) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isObstacle() {
        if (this instanceof Obstacle) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTour() {
        if (this instanceof Tour) {
            return true;
        } else {
            return false;
        }
    }

    public void modiftemps(int t) {
        temps = t;
    }

    public int getTemps() {
        return temps;
    }

    public int getVitesse() {
        return 0;
    }

    public void affichageImage(MonLabel monLabel) {
    }

    public void affichageText(MonLabel monLabel) {
    }
}
