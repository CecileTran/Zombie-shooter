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
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;

public class Joueur {

    final String NOM_FICHIER = "Joueur.txt"; // Sauvegarde des donnees joueur
    final String NOM_FICHIER2 = "Scores.txt"; // Fichier Liste de scores 
    final int ARGENT_DEPART = 600;

    private String nom;
    private int score;
    private static int argent;
    private int vie; // Depend de la difficulte
    private int nbrVagues;
    private int niveau; // Facile, moyen ou dur (pour le chemin choisi)

    private LinkedList<Joueur> listeScore;// Liste des scores

    public Joueur(String nom) {
        this.nom = nom;
        score = 0;
        this.argent = ARGENT_DEPART;
        nbrVagues = 0;
        this.vie = 20;
        this.listeScore = new LinkedList<>();
    }

    public Joueur(String nom, int score) {
        this.nom = nom;
        this.score = score;
        this.listeScore = new LinkedList<>();
    }

    // La methode renvoie vrai si le joueur peut acheter la tour
    public boolean acheterTours(Tour nomTour) {
        if ((argent - nomTour.getCout()) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void choixNiveau(int niv) {
        niveau = niv;
    }

    public int getNiveau() {
        return niveau;
    }

    @Override
    public String toString() { // Pour le fichier de sauvegarde
        return nom + "/" + score + "/" + argent + "/" + vie + "/" + nbrVagues + "/" + niveau;
    }

    public void afficher() { // Pour les tests
        System.out.println("Nom = " + nom + "\nScore = " + score + "\nArgent = " + argent + "\nVie = " + vie + "\nNombre de vagues = " + nbrVagues + "\nNiveau = " + niveau);
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public int getVie() {
        return vie;
    }

    public int getNbrVagues() {
        return nbrVagues;
    }

    public static int getArgent() {
        return argent;
    }

    public void PassageALaCaisse(Tour nomTour) {
        argent = argent - nomTour.getCout();
    }

    public void sauvegarderJoueur() {
        try {
            FileWriter fich = new FileWriter(NOM_FICHIER);
            fich.write(this.toString());
            fich.close();
        } catch (IOException ex) {
            System.out.println("La partie n'a pas pu etre sauvegardee ");
        }

    }

    public void chargerJoueur() throws IOException { // Throws : pour propager l'exception

        FileReader fich = new FileReader(NOM_FICHIER); // FileNotFoundException
        BufferedReader br = new BufferedReader(fich);
        String ligne;// IOException

        String[] tab = new String[10];

        ligne = br.readLine();

        // On place les elements de la ligne dans un tableau
        tab = ligne.split("/");

        // On definit les attributs du joueur avec les elements du fichier
        nom = tab[0];
        score = Integer.valueOf(tab[1]);
        argent = Integer.valueOf(tab[2]);
        vie = Integer.valueOf(tab[3]);
        nbrVagues = Integer.valueOf(tab[4]);
        niveau = Integer.valueOf(tab[5]);

        fich.close(); // IOException
    }

    public void perdVie(Zombie z) {
        vie = vie - z.getAttaque();
    }

    public void incremScore(int s) {
        score = score + s;
    }

    public void incremArgent(int a) {
        argent = argent + a;
    }

    public void lireJoueur() throws FileNotFoundException, IOException {

        FileReader fich = new FileReader(NOM_FICHIER2);// FileNotFoundException
        BufferedReader br = new BufferedReader(fich);
        String ligne;// IOException

        String[] tab = new String[20];

        ligne = br.readLine();
        while (ligne != null) {

            // On place les elements de la ligne dans un tableau
            tab = ligne.split("/");

            // On definit les attributs du joueur avec les elements du fichier
            String fnom = tab[0];
            int fscore = Integer.valueOf(tab[1]);

            Joueur j = new Joueur(fnom, fscore);
            listeScore.add(j);
            ligne = br.readLine();
        }
        fich.close(); // IOException
    }

    public void sauvegarderScore() throws IOException {

        FileWriter fich = new FileWriter(NOM_FICHIER2);// FileNotFoundException    
        Joueur j = new Joueur(this.nom, this.score);
        listeScore.add(j);

        Iterator<Joueur> it = listeScore.iterator();
        // Tant que l'iterateur n'est pas arrive à la fin de la liste :
        while (it.hasNext() == true) {
            Joueur j2 = it.next();
            fich.write(j2.getNom() + "/" + j2.getScore());
            fich.write(System.lineSeparator());
        }
        fich.close();
    }

    public void trierScores(JLabel tabLabel[][]) {
        int l = 0;
        try {
            this.lireJoueur();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        Collections.sort(listeScore, new JoueurComparator());
        Iterator<Joueur> it = listeScore.iterator();
        while (it.hasNext() == true && l < 10) {
            Joueur j2 = it.next();
            tabLabel[l + 1][1].setText(j2.getNom());
            tabLabel[l + 1][2].setText("" + j2.getScore());
            l++;
        }
    }

    public void incremNbrVagues() {
        nbrVagues++;
    }
}
