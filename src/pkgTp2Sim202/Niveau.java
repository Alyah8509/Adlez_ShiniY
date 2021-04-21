package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {

    public char[][] lire(int niveau) {
        int positionJoueur []=new int[2];
        char positionAutres []=new char[30];
        int positionMonstre []=new int[2];
        char [][] carte = new char[0][];
        int longueur = 0;
        int largeur=0;

                try {
                    BufferedReader fichier = new BufferedReader(new FileReader(niveau + ".txt"));

                    String ligne;
                    ligne = fichier.readLine();
                    int ligneLu = 0;
                    while (ligne != null) {
                        if (ligneLu == 0) {
                            for (int i = 0; i < positionJoueur.length; i++) {
                                String[] positionString = ligne.split(",");
                                positionJoueur[i] = Integer.parseInt(positionString[i]);
                            }
                        } else if (ligneLu != 0) {
                            String[] tableauTemporaire = ligne.split(",");
                            positionAutres = tableauTemporaire[0].toCharArray();
                            for (int i = 0; i < positionAutres.length; i++) {
                                if (positionAutres[i] == '#') {
                                    for (int y = 0; y < positionAutres.length; y++) {
                                        if (positionAutres[y] == '#') {
                                            longueur++;
                                        }
                                    }
                                    while (ligne != null) {
                                        largeur++;
                                        ligne = fichier.readLine();
                                    }
                                    break;
                                }
                            }

                        }
                        ligneLu++;
                        ligne = fichier.readLine();
                    }
                    fichier.close();
                    ligneLu = 0;
                    carte = new char[largeur][longueur];
                    BufferedReader fich = new BufferedReader(new FileReader(niveau + ".txt"));
                    ligne = fich.readLine();
                    while (ligne != null) {
                        String[] tableauTemporaire = ligne.split(",");
                        positionAutres = tableauTemporaire[0].toCharArray();
                        for (int i = 0; i < positionAutres.length; i++) {
                            if (positionAutres[i] == '#') {
                                while (ligne != null) {
                                    for (int t = 0; t < largeur; t++) {
                                        for (int a = 0; a < longueur; a++) {
                                            carte[t][a] = positionAutres[a];
                                        }
                                        ligne = fich.readLine();
                                        if (ligne == null) {
                                            break;
                                        }
                                        tableauTemporaire = ligne.split(",");
                                        positionAutres = tableauTemporaire[0].toCharArray();
                                    }
                                }
                            }
                        }
                        ligne = fich.readLine();
                    }
                    fich.close();
                    BufferedReader fich1 = new BufferedReader(new FileReader(niveau + ".txt"));
                    ligne = fich1.readLine();
                    while (ligne != null) {
                        if (ligneLu != 0) {
                            String[] tableauTemporaire = ligne.split(",");
                            positionAutres = tableauTemporaire[0].toCharArray();
                            for (int i = 0; i < positionAutres.length; i++) {
                                if (positionAutres[i] == ':') {

                                }
                            }
                        }
                        ligneLu++;
                        ligne = fich1.readLine();
                    }
                    fich1.close();
                }catch (Exception e){

                }
                return carte;
    }

}
