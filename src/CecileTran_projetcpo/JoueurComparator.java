/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package CecileTran_projetcpo;

import java.util.Comparator;

public class JoueurComparator implements Comparator<Joueur> {

    @Override
    public int compare(Joueur a, Joueur b) {
        return -(a.getScore() - b.getScore());
    }
}
