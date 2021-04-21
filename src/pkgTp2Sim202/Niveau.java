package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {

    public char[][] lire(int niveau) {
        int positionJoueur []=new int[2];
        char positionAutres []=new char[30];
        int donnesMonstres [][]=new int[12][12];
        char [][] carte = new char[0][];
        int longueur = 0;
        int largeur=0;
        int nbDeMonstres=0;
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
                                    if (positionAutres[0]=='m'){
                                        donnesMonstres[0][nbDeMonstres]=Character.getNumericValue(positionAutres[i+1]);
                                        positionAutres = tableauTemporaire[1].toCharArray();
                                        donnesMonstres[0][nbDeMonstres+1]=Character.getNumericValue(positionAutres[0]);
                                        positionAutres = tableauTemporaire[2].toCharArray();
                                        donnesMonstres[1][nbDeMonstres]=Character.getNumericValue(positionAutres[0]);
                                        positionAutres = tableauTemporaire[3].toCharArray();
                                        donnesMonstres[1][nbDeMonstres+1]=Character.getNumericValue(positionAutres[0]);
                                        nbDeMonstres+=2;
                                    }
                                }
                            }
                        }
                        ligneLu++;
                        ligne = fich1.readLine();
                    }
                    for (int i=0; i<donnesMonstres.length; i+=2){
                        if (donnesMonstres[0][i]==0){
                            break;
                        }
                        carte[donnesMonstres[0][i]-1][donnesMonstres[0][i+1]-1]='@';
                    }
                    carte[positionJoueur[0]][positionJoueur[1]]='&';
                    fich1.close();
                }catch (Exception e){

                }
                return carte;
    }

}
