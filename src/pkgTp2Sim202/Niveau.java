package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {
    int positionJoueur []=new int[2];
    int donnesMonstres [][]=new int[2][30];
    int nbDeMonstres=0;
    int donnesPancartes []=new int[2];
    String [] messagePancarte=new String[0];
    int donnesTresor []=new int[20];
    int nbDeTresor=0;
    int [][] donnesTp=new int [2][30];
    int nbDeTp=0;
    char [][] carte = new char[0][];
    int longueur = 0;
    int largeur=0;
    char positionAutres [];


    public char[][] lire(int niveau) {
                try {
                    longueur=0;
                    largeur=0;
                    nbDeMonstres=0;
                    nbDeTresor=0;
                    nbDeTp=0;
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
                                        donnesMonstres=raccourciMatrice(positionAutres,i,donnesMonstres,nbDeMonstres,tableauTemporaire);
                                        nbDeMonstres+=2;
                                    }
                                    else if (positionAutres[0]=='p'){
                                        donnesPancartes=raccourciPancartes(donnesPancartes,positionAutres,i,tableauTemporaire);
                                        int q=0;
                                        String [] temporaire=new String[tableauTemporaire.length-2];
                                        for (int r=tableauTemporaire.length-1; r> 1; r--){
                                            temporaire[q]=tableauTemporaire[r];
                                            q++;
                                        }messagePancarte=new String[temporaire.length];
                                        int a=1;
                                        for (int r=0; r<temporaire.length; r++){
                                            messagePancarte[r]=temporaire[temporaire.length-a];
                                            a++;
                                        }
                                    }
                                    else if (positionAutres[0]=='t'&&positionAutres[1]=='r'){
                                        raccourciPlusieursCoordonnees(donnesTresor,positionAutres,nbDeTresor,i,tableauTemporaire);
                                        nbDeTresor+=2;
                                    }
                                    else if (positionAutres[0]=='t'&&positionAutres[1]=='e'){
                                        raccourciMatrice(positionAutres,i,donnesTp,nbDeTp,tableauTemporaire);
                                        nbDeTp+=2;
                                    }
                                }
                            }
                        }
                        ligneLu++;
                        ligne = fich1.readLine();
                    }
                    if (donnesMonstres[0][0]>0){
                    afficher(donnesMonstres,'@');}
                    if (donnesTp[0][0]>0){
                        afficher(donnesTp,'*');
                    }
                    carte[positionJoueur[1]][positionJoueur[0]]='&';
                    if (donnesPancartes[0]>0){
                        carte[donnesPancartes[1]][donnesPancartes[0]]='!';
                    }
                    for (int i=0; i<donnesTresor.length; i+=2){
                        if (donnesTresor[i]==0){
                            break;
                        }
                        carte[donnesTresor[i+1]][donnesTresor[i]]='$';
                    }
                    fich1.close();
                }catch (Exception e){
                }
                return carte;
    }
    private int [] raccourciPancartes (int [] donnes, char [] positionAutres, int i, String [] tableauTemporaire){
        if (positionAutres[positionAutres.length-2]>=48&&positionAutres[positionAutres.length-2]<=57){
            String test=Character.toString(positionAutres[i+1]);
            test+=Character.toString(positionAutres[i+2]);
            donnes[0]=Integer.parseInt(test);
        }else{donnes[0]=Character.getNumericValue(positionAutres[i+1]);}
        positionAutres = tableauTemporaire[1].toCharArray();
        if (positionAutres.length>=2){
            String test=Character.toString(positionAutres[0]);
            test+=Character.toString(positionAutres[1]);
            donnes[1]=Integer.parseInt(test);
        }else {
            donnes[1]=Character.getNumericValue(positionAutres[0]);}return donnes;
    }
    private int [] raccourciPlusieursCoordonnees (int[] donnes, char[]positionAutres, int nbDeX, int i,String[] tableauTemporaire){
        if (positionAutres[positionAutres.length-2]>=48&&positionAutres[positionAutres.length-2]<=57){
            String test=Character.toString(positionAutres[i+1]);
            test+=Character.toString(positionAutres[i+2]);
            donnes[nbDeX]=Integer.parseInt(test);
        }
        else {donnes[nbDeX]=Character.getNumericValue(positionAutres[i+1]);}
        positionAutres = tableauTemporaire[1].toCharArray();
        if (positionAutres.length>=2){
            String test=Character.toString(positionAutres[0]);
            test+=Character.toString(positionAutres[1]);
            donnes[nbDeX+1]=Integer.parseInt(test);
        }
        else {donnes[nbDeX+1]=Character.getNumericValue(positionAutres[0]);}return donnes;
    }
    private int [][] raccourciMatrice (char [] positionAutres, int i,int [][]donnes, int nDeY,String[] tableauTemporaire){
        if (positionAutres[positionAutres.length-2]>=48&&positionAutres[positionAutres.length-2]<=57){
            String test=Character.toString(positionAutres[i+1]);
            test+=Character.toString(positionAutres[i+2]);
            donnes[0][nDeY]=Integer.parseInt(test);
        }
        else {donnes[0][nDeY]=Character.getNumericValue(positionAutres[i+1]);}
        positionAutres = tableauTemporaire[1].toCharArray();
        if (positionAutres.length>=2){
            String test=Character.toString(positionAutres[0]);
            test+=Character.toString(positionAutres[1]);
            donnes[0][nDeY+1]=Integer.parseInt(test);
        }
        else {donnes[0][nDeY+1]=Character.getNumericValue(positionAutres[0]);}
        positionAutres = tableauTemporaire[2].toCharArray();
        donnes[1][nDeY]=Character.getNumericValue(positionAutres[0]);
        positionAutres = tableauTemporaire[3].toCharArray();
        donnes[1][nDeY+1]=Character.getNumericValue(positionAutres[0]);
        return donnes;
    }
    private void afficher (int [][] donnes, char special){
        for (int i=0; i<donnes[0].length; i+=2){
            if (donnes[0][i]==0){
                break;
            }
            carte[donnes[0][i+1]][donnes[0][i]]=special;
        }
    }

}
