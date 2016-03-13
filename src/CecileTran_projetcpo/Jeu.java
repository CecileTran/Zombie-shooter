/*
 EPF 2A
 Auteurs : Nicolas Breton et Cecile Tran
 Zombie shooter
 Groupe A
 Date : 02/02/15
 Edit : 15/05/15
 */
package CecileTran_projetcpo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import pfiches.MonLabel;

public class Jeu {

    final int CHEMIN = 2;
    final int COLONNES = 15; // Nombre de colonnes du plateau
    final int LIGNES = 7; // Nombre de lignes du plateau
    final int CASES = 105; // Nombre de cases dans le plateau
    final int FIN = 3; // Nombre de vagues maximum
    final String NOM_FICHIER = "Terrain.txt"; // Fichier de sauvegarde des tours
    public static final String DOSS_IMAGES = "src/pimages/";

    // Linked List a chaque case du tableau a 2 dimensions
    private LinkedList<NonJoueur> terrain[][] = new LinkedList[LIGNES][COLONNES];
    private LinkedList<Zombie> listeZombies;// Liste des differents types de Zombie
    private LinkedList<Tour> listeTours;// Liste des differentes tours
    private LinkedList<TourOff> listeToursTerrain;// Liste des tours offensives sur le terrain
    private LinkedList<TourRal> listeToursTerrain2;// Liste des tours ralentissantes sur le terrain
    private LinkedList<Zombie> listeZombTerrain;//Liste des zombies sur le terrain
    private LinkedList<Zombie> listeZombSuppr;//Liste des zombies sur le terrain

    private Joueur joueur;
    private int timer = 0; // Compteur
    private int nbreAlZ, nbreAjoutes = 0; // Valeurs tirees aleatoirement
    private Zombie terrainAv[][] = new Zombie[LIGNES][COLONNES];
    public int vagues = 0;

    public Jeu(String nom) {

        joueur = new Joueur(nom);

        // Initialisation de toutes les linked list du terrain
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain[i].length; j++) {
                LinkedList<NonJoueur> maListe = new LinkedList<>();
                terrain[i][j] = maListe;
            }
        }

        this.listeToursTerrain = new LinkedList<>();
        this.listeToursTerrain2 = new LinkedList<>();
        this.listeZombTerrain = new LinkedList<>();
        this.listeZombSuppr = new LinkedList<>();
        this.listeZombies = new LinkedList<>();

        // Type, Vitesse (ms), vie, attaque, valeur
        Zombie z = new Zombie(0, 400, 800, 1, 100); // Zombie rapide et faible
        Zombie z2 = new Zombie(1, 800, 1000, 2, 200); // Zombie moyen
        Zombie z3 = new Zombie(2, 1000, 1800, 3, 300); // Zombie costaud
        Zombie zb = new Zombie(3, 1200, 20000, 20, 1000); // Zombie boss

        listeZombies.add(z);
        listeZombies.add(z2);
        listeZombies.add(z3);
        listeZombies.add(zb);

        this.listeTours = new LinkedList<>();

        //Type, portee, cout, vitesse(ms), attaque, ligne, colonne
        TourOff to1 = new TourOff(0, 1, 100, 400, 40, 0, 0);
        TourOff to2 = new TourOff(1, 1, 200, 800, 80, 0, 0);
        TourOff to3 = new TourOff(2, 2, 100, 400, 60, 0, 0);
        TourRal tr = new TourRal(3, 1, 100, 2000, 200, 0, 0);
        TourOff to4 = new TourOff(4, 1, 200, 400, 60, 0, 0);
        TourOff to5 = new TourOff(5, 1, 400, 800, 100, 0, 0);
        TourOff to6 = new TourOff(6, 2, 600, 400, 80, 0, 0);
        TourRal tr2 = new TourRal(7, 1, 400, 1200, 200, 0, 0);

        listeTours.add(to1);
        listeTours.add(to2);
        listeTours.add(to3);
        listeTours.add(tr);
        listeTours.add(to4);
        listeTours.add(to5);
        listeTours.add(to6);
        listeTours.add(tr2);
    }

    @Override
    public String toString() {

        String ch = "";
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain[i].length; j++) {
                ch = ch + terrain[i][j] + " ";
            }
            ch = ch + System.lineSeparator();
        }
        return ch;
    }

    public void chargerChemin(int chem) {

        FileReader fich = null;
        try {
            fich = new FileReader("Chemin" + chem + ".txt");
            BufferedReader br = new BufferedReader(fich);
            String ligne; // IOException
            String tab[] = new String[CASES];
            Chemin Lechemin;
            int l, c;
            int i = 0;
            do {
                // On lit chaque ligne du fichier texte :
                ligne = br.readLine();
                i++;
                if (ligne != null) {
                    // On entre les coordonnees dans un tableau :
                    tab = ligne.split("/");
                    l = Integer.valueOf(tab[0]);
                    c = Integer.valueOf(tab[1]);
                    Lechemin = new Chemin(i, l, c);
                    // On ajoute le chemin aux coordonnees donnees :
                    terrain[l][c].addFirst(Lechemin);
                }
            } while (ligne != null);
            fich.close(); // IOException
        } catch (IOException ex) {
        }
    }

    public void chargerObstacle(int chem) {

        FileReader fich = null;
        try {
            fich = new FileReader("Foret" + chem + ".txt");
            BufferedReader br = new BufferedReader(fich);
            String ligne;// IOException
            String tab[] = new String[CASES];
            Obstacle Obs;
            int l, c;

            do {
                // On lit chaque ligne du fichier texte :
                ligne = br.readLine();
                if (ligne != null) {
                    // On entre les coordonnees dans un tableau :
                    tab = ligne.split("/");
                    l = Integer.valueOf(tab[0]);
                    c = Integer.valueOf(tab[1]);
                    Obs = new Obstacle(l, c);
                    // On ajoute le chemin aux coordonnees donnees :
                    terrain[l][c].addFirst(Obs);
                }
            } while (ligne != null);
            fich.close();//IOException
        } catch (IOException ex) {
        }
    }

    public void choixNiveau(int niv) {
        joueur.choixNiveau(niv);
    }

    public boolean PossibleOuPas(int type) {
        Tour nomTour = listeTours.get(type);
        if (joueur.getArgent() - nomTour.getCout() < 0) {
            return false;
        }
        return true;
    }

    public boolean acheterTours(int type, int ligne, int colonne) {
        // type : numero de la tour dans la liste 
        Tour nomTour = listeTours.get(type);
        Tour newTour = nomTour.copierTour();

        // Si le joueur peut acheter la tour :
        if (joueur.acheterTours(newTour) == true) {
            // Si la case est vide (pas de tour)
            if (terrain[ligne][colonne].isEmpty() == false) {
                NonJoueur PNJ = terrain[ligne][colonne].getFirst();
                if (PNJ.isTour() == true) {
                    Tour tour = (Tour) terrain[ligne][colonne].getFirst();
                    if (tour.getType() == type - 4) {
                        supprimerTours(tour);
                        boolean placer = placerTour(newTour, ligne, colonne);
                        if (placer == true) {
                            joueur.PassageALaCaisse(newTour);
                        }
                        return placer;
                    }
                }

            } else {
                boolean placer = placerTour(newTour, ligne, colonne);
                if (placer == true) {
                    joueur.PassageALaCaisse(newTour);
                }
                return placer;
            }
        }
        return false;
    }

    public boolean placerTour(Tour tour, int ligne, int colonne) {

        if (isTourOff(tour) == true) {
            TourOff tourOff = new TourOff(tour.getType(), tour.getPortee(), tour.getCout(), tour.getVitesse(), tour.getAttaque(), tour.getLigne(), tour.getColonne());
            tourOff.setTemps(timer);// On fait correspondre le temps de la tour avec le timer
            // Lorsque l'objet a placer est une tour, on verifie que la case est vide :
            if (terrain[ligne][colonne].isEmpty() == true) {
                tourOff.initCoord(ligne, colonne);
                listeToursTerrain.add(tourOff);  // On ajoute la tour dans la liste des tours du même type sur le terrain
                terrain[ligne][colonne].add(tourOff);
                return true;
            }
            return false;
        } else {
            TourRal tourRal = new TourRal(tour.getType(), tour.getPortee(), tour.getCout(), tour.getVitesse(), tour.getAttaque(), tour.getLigne(), tour.getColonne());
            tourRal.setTemps(timer);// On fait correspondre le temps de la tour avec le timer
            if (terrain[ligne][colonne].isEmpty() == true) {
                tourRal.initCoord(ligne, colonne);
                listeToursTerrain2.add(tourRal);  // On ajoute la tour dans la liste des tours du même type sur le terrain
                terrain[ligne][colonne].add(tourRal);
                return true;
            }
            return false;
        }
    }

    public void creerZombie(int type) {

        Zombie nomZombie = listeZombies.get(type);
        Zombie newZombie = nomZombie.copierZombie(timer);
        placerZombie(newZombie, 3, 0);
    }

    public void placerZombie(Zombie z, int ligne, int colonne) {

        z.initCoord(ligne, colonne);
        listeZombTerrain.add(z);
        // Si l'objet a placer est un zombie on verifie qu'il est bien place sur un chemin:
        NonJoueur NJ = terrain[ligne][colonne].getFirst();

        if (terrain[ligne][colonne].isEmpty() != true && NJ.isChemin() == true) {
            terrain[ligne][colonne].add(z);
        }
    }

    public boolean testCase(int ligne, int colonne, NonJoueur ch, Zombie zombie) {
        // On verifie si la case possede un chemin : 
        if ((ligne >= 0 && ligne < LIGNES && colonne >= 0 && colonne < COLONNES) && (terrain[ligne][colonne].isEmpty() == false)) {
            NonJoueur ch2 = terrain[ligne][colonne].getFirst();

            // Si oui, on fait avancer le zombie :
            if ((ch2.isChemin() == true) && (ch2.getOrdre() > ch.getOrdre())) {
                terrain[ligne][colonne].add(zombie);// On ajoute le zombie sur le prochaine case
                supprimerZombie(zombie);// On le supprime sur l'ancienne
                zombie.initCoord(ligne, colonne);// On reinitialise ces coordonnees  
                return true;
            }
        }
        return false;
    }

    public void avancerZombie() {

        Iterator<Zombie> it = listeZombTerrain.iterator();

        // S'il y a un zombie sur le terrain
        if (listeZombTerrain.isEmpty() == false) {
            while (it.hasNext()) {
                Zombie zomb = it.next();

                // Si la vitesse + le temps du zombie = timer
                if ((zomb.getTemps() + zomb.getVitesse()) == timer) {
                    int ligne = zomb.getLigne();
                    int colonne = zomb.getColonne();

                    //Si le zombie est arrive au bout du chemin
                    if (ligne == 3 && colonne == 14) {
                        joueur.perdVie(zomb);
                        listeZombSuppr.add(zomb);
                    }

                    if (timer % 500 == 0) { // Toutes les 0.5 secondes
                        int type = zomb.getType();
                        Zombie ZombieInit = listeZombies.get(type);
                        // On reinitalise la vitesse du zombie après etre ralenti
                        zomb.reinitVitesse(ZombieInit.getVitesse());
                    }

                    NonJoueur ch = terrain[ligne][colonne].getFirst();

                    try {
                        // On verifie si les cases aux alentours sont des chemins :
                        if (testCase(ligne + 1, colonne, ch, zomb) == true) {

                        } else if (testCase(ligne, colonne + 1, ch, zomb) == true) {

                        } else if (testCase(ligne, colonne - 1, ch, zomb) == true) {

                        } else if (testCase(ligne - 1, colonne, ch, zomb) == true) {

                        }
                    } catch (NoSuchElementException ex) {
                        System.out.println("Déplacement impossible\n");
                    }
                    zomb.modiftemps(timer);
                }
            }
        }
    }

    public void supprimerZombie(Zombie z) {

        int ligne = z.getLigne();
        int colonne = z.getColonne();

        Iterator<NonJoueur> it = terrain[ligne][colonne].iterator();
        while (it.hasNext()) {
            NonJoueur PNJ2 = it.next();
            if (PNJ2 == z) {// Si le personnage correspond
                // On le supprime de la case:
                it.remove();
            }
        }

        Iterator<Zombie> it2 = listeZombTerrain.iterator();
        while (it2.hasNext()) {
            Zombie z2 = it2.next();
            if ((z2 == z && z2.getVie() <= 0) || (z2 == z && z2.getLigne() == 3 && z2.getColonne() == 14)) {// Si le zombie est mort ou a atteint la fin du terrain 
                // On le supprime du terrain:
                it2.remove();
            }
        }
    }

    public boolean verifTour(int ligne, int colonne) {
        if (terrain[ligne][colonne].isEmpty() == false) {
            return true;
        } else {
            return false;
        }
    }

    public void supprimerTour(int ligne, int colonne) {
        //Vu qu'il n'y a qu'une tour au plus dans la linked list on peut la vider
        terrain[ligne][colonne].clear();

        Iterator<TourOff> it = listeToursTerrain.iterator();
        Iterator<TourRal> it2 = listeToursTerrain2.iterator();

        while (it.hasNext()) {
            TourOff t = it.next();
            int i = t.getLigne();
            int j = t.getColonne();
            if ((i == ligne) && (j == colonne)) {// Si le personnage correspond
                // On le supprime :
                it.remove();
            }
        }

        while (it2.hasNext()) {
            TourRal t2 = it2.next();
            int i = t2.getLigne();
            int j = t2.getColonne();
            if ((i == ligne) && (j == colonne)) {// Si le personnage correspond
                // On le supprime :
                it2.remove();
            }
        }
    }

    public void supprimerTours(Tour t) {

        int ligne = t.getLigne();
        int colonne = t.getColonne();

        Iterator<NonJoueur> it = terrain[ligne][colonne].iterator();
        while (it.hasNext()) {
            NonJoueur PNJ2 = it.next();
            if (PNJ2 == t) {// Si le personnage correspond
                // On le supprime :
                it.remove();
            }
        }

        if (isTourOff(t) == true) {
            Iterator<TourOff> it2 = listeToursTerrain.iterator();
            while (it2.hasNext()) {
                Tour t2 = it2.next();
                if (t2 == t) {// Si le personnage correspond
                    // On le supprime :
                    it2.remove();
                }
            }
        } else {
            Iterator<TourRal> it2 = listeToursTerrain2.iterator();
            while (it2.hasNext()) {
                Tour t2 = it2.next();
                if (t2 == t) {// Si le personnage correspond
                    // On le supprime :
                    it2.remove();
                }
            }
        }
    }

    // On verifie s'il y a un zombie dans le champ de la tour :
    public Zombie verifZombie(Tour tour) {
        int ligne = 0;
        int colonne = 0;

        int i = tour.getLigne();
        int j = tour.getColonne();

        if (terrain[i][j].contains(tour)) {
            ligne = i;
            colonne = j;

            int inc = tour.getPortee();// On recupere la portee de la tour

            // On verifie si la portee de la tour n'est pas hors terrain :
            int minL = ligne - inc;
            if (minL < 0) {
                minL = 0;
            }

            int maxL = ligne + inc;
            if (maxL > LIGNES - 1) {
                maxL = LIGNES - 1;
            }

            int minC = colonne - inc;
            if (minC < 0) {
                minC = 0;
            }

            int maxC = colonne + inc;
            if (maxC > COLONNES - 1) {
                maxC = COLONNES - 1;
            }

            int ordre1 = 0;
            int l = 0;
            int c = 0;
            int ordre2;

            // On parcourt le champ d'attaque de la tour :
            for (int k = minL; k <= maxL; k++) {
                for (int w = minC; w <= maxC; w++) {
                    if (terrain[k][w].isEmpty() == false) {

                        NonJoueur NJ = terrain[k][w].getFirst();
                        NonJoueur NJ2 = terrain[k][w].getLast();

                        if (NJ.isChemin() == true) {
                            ordre2 = NJ.getOrdre();
                            // Si le zombie est le plus avance
                            if (NJ2.isZombie() == true && ordre2 > ordre1) {
                                ordre1 = ordre2;
                                // On sauvegarde sa position
                                l = k;
                                c = w;
                            }
                        }
                    }
                }
            }

            // On attaque le zombie le plus avance sur le chemin
            if (terrain[l][c].isEmpty() == false) {
                NonJoueur NJ3 = terrain[l][c].getLast();
                if (NJ3.isZombie() == true) {
                    return (Zombie) NJ3;
                }
            }
        }
        return null;
    }

    public void attaque() {

        if (listeToursTerrain.isEmpty() == false) {
            Iterator<TourOff> it = listeToursTerrain.iterator();
            while (it.hasNext()) {

                TourOff tourOff = it.next();

                if ((tourOff.getTemps() + tourOff.getVitesse()) == timer) {
                    Zombie zombie = verifZombie(tourOff);

                    if (zombie != null) {
                        // Si le zombie n'a plus de vie :
                        if (tourOff.attaque(zombie) != true) {
                            // On le supprime :
                            supprimerZombie(zombie);
                            joueur.incremArgent(zombie.getValeur());
                            joueur.incremScore(zombie.getScore());
                        }
                    }
                    tourOff.modiftemps(timer);
                }
            }
        }

        if (listeToursTerrain2.isEmpty() == false) {
            Iterator<TourRal> it2 = listeToursTerrain2.iterator();
            while (it2.hasNext()) {
                TourRal tourRal = it2.next();

                if ((tourRal.getTemps() + tourRal.getVitesse()) == timer) {
                    Zombie zombie = verifZombie(tourRal);

                    if (zombie != null) {
                        // Si le zombie n'a plus de vie :
                        if (tourRal.attaque(zombie) != true) {
                            joueur.incremArgent(zombie.getValeur());
                            joueur.incremScore(zombie.getScore());
                        }
                    }
                    tourRal.modiftemps(timer);
                }
            }
        }
    }

    // On sauvegarde les cases contenants une tour :
    public void sauvegarderTerrain() {
        try {
            FileWriter fich = new FileWriter(NOM_FICHIER);

            for (int i = 0; i < LIGNES; i++) {
                for (int j = 0; j < COLONNES; j++) {
                    if (terrain[i][j].isEmpty() == false) { // si la case n'est pas vide
                        NonJoueur PNJ = terrain[i][j].getFirst();

                        // Si ce n'est pas un chemin et un obstacle
                        if (PNJ.isChemin() == false && PNJ.isObstacle() == false) {
                            fich.write(i + "," + j);// On ecrit les coordonnees de la case
                            Iterator it = terrain[i][j].iterator();
                            // Tant que l'iterateur n'est pas arrivé à la fin de la liste :
                            while (it.hasNext() == true) {
                                // On ecrit le type de la tour :
                                fich.write("," + it.next());
                            }
                            fich.write(System.lineSeparator());
                        }
                    }
                }
            }
            fich.close();
        } catch (IOException ex) {
            System.out.println("La partie n'a pas pu etre sauvegardee ");
        }
    }

    public void chargerTerrain() throws FileNotFoundException, IOException {

        FileReader fich = new FileReader(NOM_FICHIER);// FileNotFoundException
        BufferedReader br = new BufferedReader(fich);
        String ligne = "";// IOException

        String[] tab = new String[8];

        while (ligne != null) {
            ligne = br.readLine();
            if (ligne != null) {// si la ligne n'est pas vide
                // On ajoute les elements de la ligne dans un tableau :
                tab = ligne.split(",");
                // On recupere les coordonnees :
                int l = Integer.valueOf(tab[0]);
                int c = Integer.valueOf(tab[1]);
                // Et le type :
                int type = Integer.valueOf(tab[2]);
                // Puis on recreer la tour correspondante :
                Tour nomTour = listeTours.get(type);

                Tour newTour = nomTour.copierTour();
                if (isTourOff(newTour) == true) {
                    TourOff tourOff = new TourOff(newTour.getType(), newTour.getPortee(), newTour.getCout(), newTour.getVitesse(), newTour.getAttaque(), newTour.getLigne(), newTour.getColonne());
                    listeToursTerrain.add(tourOff);
                } else {
                    TourRal tourRal = new TourRal(newTour.getType(), newTour.getPortee(), newTour.getCout(), newTour.getVitesse(), newTour.getAttaque(), newTour.getLigne(), newTour.getColonne());
                    listeToursTerrain2.add(tourRal);
                }
                // Et la replace sur le terrain aux coordonnees indiquees :
                placerTour(newTour, l, c);
            }
        }
        fich.close();//IOException
    }

    public void afficherJoueur() {
        joueur.afficher();
    }

    public void sauvegarderJoueur() {
        joueur.sauvegarderJoueur();
    }

    public void chargerJoueur() {

        try {
            joueur.chargerJoueur();
        } catch (IOException ex) {
        }
    }

    public void sauvegarderScore() {
        try {
            joueur.lireJoueur();
            joueur.sauvegarderScore();
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherTerrain(MonLabel tabLabel[][]) {

        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain[i].length; j++) {
                if (terrain[i][j].isEmpty() == false) {

                    // Si la case est un chemin :
                    if (terrain[i][j].getFirst().isChemin() == true && terrain[i][j].getLast().isChemin() == true) {
                        terrain[i][j].getFirst().affichageImage(tabLabel[i][j]);
                    }
                    // Si la case est un obstacle :
                    if (terrain[i][j].getFirst().isObstacle() == true && terrain[i][j].getLast().isObstacle() == true) {
                        terrain[i][j].getFirst().affichageImage(tabLabel[i][j]);
                    }

                    NonJoueur NJ = terrain[i][j].getLast();

                    // S'il y a un zombie sur la case et qu'il vient de se déplacer sur la case :
                    if ((terrain[i][j].getLast().isChemin() == false && NJ.getTemps() == timer) && (NJ.isZombie() == true) || (terrain[i][j].getLast().isChemin() == false && terrainAv[i][j] != null && terrainAv[i][j].getTemps() == timer)) {

                        NJ.affichageImage(tabLabel[i][j]);
                        terrain[i][j].getLast().affichageImage(tabLabel[i][j]);
                        terrainAv[i][j] = (Zombie) NJ;

                    } else if (NJ.isTour() == true) { // Si c'est une tour
                        NJ.affichageImage(tabLabel[i][j]);
                        NJ.affichageText(tabLabel[i][j]);
                    } else if (NJ.isZombie() == true) { // Si c'est un Zombie
                        // On affiche le texte correspondant à l'image :
                        terrain[i][j].getLast().affichageText(tabLabel[i][j]);
                    }
                } else {
                    tabLabel[i][j].setIcon(null);
                    tabLabel[i][j].setText(null);
                }
            }
        }
    }

    public void trierScore(JLabel tabLabel[][]) {
        joueur.trierScores(tabLabel);
    }

    public int getVie() {
        return joueur.getVie();
    }

    public int getArgent() {
        return joueur.getArgent();
    }

    public int getNbrVagues() {
        return joueur.getNbrVagues();
    }

    public int getScore() {
        return joueur.getScore();
    }

    public int getNiveau() {
        return joueur.getNiveau();
    }

    public String getNom() {
        return joueur.getNom();
    }

    public void lancerVague() {

        Random rand = new Random();
        int i = joueur.getNbrVagues();

        if (i < FIN -1) {
            nbreAlZ = rand.nextInt((8 + i) - (5 + i) + 1) + (5 + i); // nextInt(max-min+1)+min
            nbreAjoutes = 0;
            ajouterZombie();
        } else {
            creerZombie(3);
        }
    }

    // Ajout aleatoire de Zombie :
    public void ajouterZombie() {

        if (nbreAjoutes < nbreAlZ) {
            Random rand = new Random();
            // Tire un nombre aleatoire entre 0 et 2 (compris) => type du zombie creer :
            int nbreAlT = rand.nextInt(3);
            this.creerZombie(nbreAlT);
            nbreAjoutes++;
        }
    }

    public void incremNbrVagues() {
        joueur.incremNbrVagues();
    }

    // On verifie s'il n'y a plus de zombies sur le terrain :
    public boolean terrainVide() {
        if (listeZombTerrain.isEmpty() == true) {
            return true;
        }
        return false;
    }

    // On verifie si une des case du terrain est vide :
    public boolean caseVide(int l, int c) {
        if (terrain[l][c].isEmpty() == true) {
            return true;
        }
        return false;
    }

    // On reinitialise le timer à 0 :
    public void reinitTimer() {
        timer = 0;
        Iterator<TourOff> it = listeToursTerrain.iterator();
        // On reinitialise le timer de toutes les tours
        while (it.hasNext()) {
            TourOff tourOff = it.next();
            tourOff.modiftemps(0);
        }

        Iterator<TourRal> it2 = listeToursTerrain2.iterator();
        // On reinitialise le timer de toutes les tours
        while (it2.hasNext()) {
            TourRal tourRal = it2.next();
            tourRal.modiftemps(0);
        }
    }

    // Boucle de jeu utilise dans le run :
    public void Jeu() {

        timer = timer + 200;

        if (timer % 2000 == 0 ) { // Toutes les 2 secondes
            ajouterZombie();
        }

        avancerZombie();
        attaque();

        Iterator<Zombie> it = listeZombSuppr.iterator();
        while (it.hasNext()) {
            Zombie z = it.next();
            supprimerZombie(z);
            it.remove();
        }
    }

    // Description des tours de départ :
    public String DescriptionTours(int type) {

        Tour tour = listeTours.get(type);

        String txt = "";
        String Desc;

        if (type != 3 && type != 7) {
            txt = "- Offensive -";
        } else {
            txt = "- Ralentisseur -";
        }

        String t = " Tour " + type;
        String c = " Coût : " + tour.getCout();
        String a = " Attaque : " + tour.getAttaque();
        String p = " Portée : " + tour.getPortee();
        String v = " Vitesse : " + tour.getVitesse();

        Desc = "<html>" + t + "<br>" + txt + "<br>" + c + "<br>" + p + "<br>" + a + "<br>" + v + "</html>";

        return Desc;

    }

    // Description des ameliorations des tours sur le terrain :
    public String DescriptionTours(int l, int c) {
        int type = 0;

        if (terrain[l][c].isEmpty() == false) {

            Tour tour0 = (Tour) terrain[l][c].getFirst();
            type = tour0.getType();
            if (type < 4){
                type = type + 4;

            Tour tour = listeTours.get(type);

            String txt = "";
            String Desc;

            if (type != 3 && type != 7) {
                txt = "- Offensive -";
            } else {
                txt = "- Ralentisseur -";
            }

            String co = " Coût : " + tour.getCout();
            String a = " Attaque : " + tour.getAttaque();
            String p = " Portée : " + tour.getPortee();
            String v = " Vitesse : " + tour.getVitesse();

            Desc = "<html>" + "Amélioration" + "<br>" + txt + "<br>" + co + "<br>" + p + "<br>" + a + "<br>" + v + "</html>";

            return Desc;
            } 
        }
        return "";
    }

    public boolean ameliorerTour(int ligne, int colonne) {

        if (terrain[ligne][colonne].getFirst().isTour()) {
            Tour tour = (Tour) terrain[ligne][colonne].getFirst();
            int type = tour.getType();

            if (type < 4) { // Si la tour est ameliorable (4 premiere tours de la liste)
                type = type + 4;
                // On achete l'amelioration si possible :
                if (acheterTours(type, ligne, colonne) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    // On verifie si la case du tableau est une tour :
    public boolean tour(int l, int c) {

        if (terrain[l][c].isEmpty() == false) {
            NonJoueur nj = terrain[l][c].getFirst();
            if (nj.isTour() == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isTourOff(Tour tour) {
        if ((tour.getType() == 3) || (tour.getType() == 7)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean FinDePartie() {
        if (joueur.getVie() <= 0 || (joueur.getNbrVagues() == FIN && terrainVide() == true)) {
            return true;
        } else {
            return false;
        }
    }
}
