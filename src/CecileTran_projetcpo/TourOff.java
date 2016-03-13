/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package CecileTran_projetcpo;

public class TourOff extends Tour {

    public TourOff(int type, int portee, int cout, int vitesse, int attaque, int ligne, int colonne) {
        super(type, portee, cout, vitesse, attaque, ligne, colonne);
    }

    public boolean attaque(Zombie z) {
        // On utilise l'attaque de la tour pour affaiblir le zombie :
        return z.baisseVie(this.getAttaque());
    }
}
