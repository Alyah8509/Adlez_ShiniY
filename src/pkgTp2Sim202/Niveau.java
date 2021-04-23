package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Niveau {
    ArrayList<Integer> positionJoueur = new ArrayList<>();
    ArrayList<Character> positionAutres = new ArrayList<>();
    int longueur;
    int largeur;
    ArrayList <Monstre> monstres=new ArrayList<>();
    Tuile[][] carte;
    ArrayList<Integer> donnesMonstres = new ArrayList<>();
    char[] convertisseur=new char[1];
    ArrayList<Integer> donnesPancartes = new ArrayList<>();
    ArrayList<String> messagePancarte = new ArrayList<>();
    ArrayList<Integer> donnesTresor = new ArrayList<>();
    ArrayList<Integer> donnesTp = new ArrayList<>();
    char[] tuiles;
     //Monstre monstre;Heros hero=new Heros();

    public Tuile[][] lire(int niveau) {
        positionJoueur.clear();positionAutres.clear();longueur=0;largeur=0;monstres.clear();donnesMonstres.clear();
        donnesPancartes.clear();messagePancarte.clear();donnesTresor.clear();donnesTp.clear();
        try {

            BufferedReader fichier = new BufferedReader(new FileReader(niveau + ".txt"));

            String ligne;
            ligne = fichier.readLine();
            int ligneLu = 0;
            while (ligne != null) {
                if (ligneLu == 0) {
                    positionJoueur=CoordonneesAdlez(ligne,positionJoueur);
                } else if (ligneLu != 0) {
                    String[] tableauTemporaire = ligne.split(",");
                    positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                    if (positionAutres.contains('#')) {
                        longueur = positionAutres.size();
                        while (ligne != null) {
                            largeur++;
                            ligne = fichier.readLine();
                        }
                        break;
                    }

                }
                ligneLu++;
                ligne = fichier.readLine();
            }
            fichier.close();
            ligneLu = 0;
            carte = new Tuile[largeur][longueur];
            BufferedReader fich = new BufferedReader(new FileReader(niveau + ".txt"));
            ligne = fich.readLine();
            while (ligne != null) {
                String[] tableauTemporaire = ligne.split(",");
                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                for (int i = 0; i < positionAutres.size(); i++) {
                    if (positionAutres.get(i).equals('#')) {
                        while (ligne != null) {
                            for (int t = 0; t < largeur; t++) {
                                carte=lireNiv(t,positionAutres,largeur,longueur,carte);
                                ligne = fich.readLine();
                                if (ligne == null) {
                                    break;
                                }
                                tableauTemporaire = ligne.split(",");
                                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
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
                    positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                    for (int i = 0; i < positionAutres.size(); i++) {
                        if (positionAutres.get(i).equals(':')) {
                            if (positionAutres.get(0).equals('m')) {
                                donnesMonstres=CoordonnesPlusieusDonnees(convertisseur,positionAutres,i,tableauTemporaire,tuiles,donnesMonstres);
                            } else if (positionAutres.get(0).equals('p')) {
                                convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                                if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(i + 2);
                                    test += Character.toString(convertisseur[0]);
                                    donnesPancartes.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    donnesPancartes.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres=LigneEnArray(1,tableauTemporaire,positionAutres,tuiles);
                                if (positionAutres.size() >= 2) {
                                    convertisseur[0] = positionAutres.get(0);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(1);
                                    test += Character.toString(convertisseur[0]);
                                    donnesPancartes.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(0);
                                    donnesPancartes.add(Character.getNumericValue(convertisseur[0]));
                                }
                                int q = 0;
                                String[] temporaire = new String[tableauTemporaire.length - 2];
                                for (int r = tableauTemporaire.length - 1; r > 1; r--) {
                                    temporaire[q] = tableauTemporaire[r];
                                    q++;
                                }
                                int a = 1;
                                for (int r = 0; r < temporaire.length; r++) {
                                    messagePancarte.add(temporaire[temporaire.length - a]);
                                    a++;
                                }
                            } else if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('r')) {
                                donnesTresor=CoordonnesTresor(convertisseur,positionAutres,i,tableauTemporaire,tuiles,donnesTresor);
                            } else if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('e')) {
                                donnesTp=CoordonnesPlusieusDonnees(convertisseur,positionAutres,i,tableauTemporaire,tuiles,donnesTp);
                            }
                        }
                    }
                }
                ligneLu++;
                ligne = fich1.readLine();
            }

        if (donnesMonstres!=null) {

            for (int i=0; i<donnesMonstres.size(); i+=4){
                monstres.add(new Monstre(donnesMonstres.get(i+2),donnesMonstres.get(i+3)));
            }
        }
        //carte[positionJoueur.get(1)][positionJoueur.get(0)];
        if (donnesTp!=null) {
            for (int i=0; i<donnesTp.size(); i+=4){
                carte[donnesTp.get(i+1)][donnesTp.get(i)]=new Teleporteur(donnesTp.get(i+2),donnesTp.get(i+3));
            }
        }
        if (donnesPancartes!=null) {
            carte[donnesPancartes.get(1)][donnesPancartes.get(0)] = new Pancarte();
        }

        for (int i = 0; i < donnesTresor.size(); i += 2) {
            carte[donnesTresor.get(i+1)][donnesTresor.get(i)] = new Tresor();
        }

            fich1.close();
        } catch (Exception e) {
        }
        return carte;
    }


    /*private void afficher (int [][] donnes, char special){
        for (int i=0; i<donnes[0].length; i+=2){
            if (donnes[0][i]==0){
                break;
            }
            carte[donnes[0][i+1]][donnes[0][i]]=special;
        }
    }*/
    public ArrayList<Integer> getPositionJoueur() {
        return positionJoueur;
    }
    public ArrayList<String> getMessagePancarte(){return messagePancarte;}
    private static ArrayList<Character> LigneEnArray (int chiffre,String[] tableauTemporaire, ArrayList<Character> positionAutres, char [] tuiles){
        positionAutres.clear();
        tuiles = tableauTemporaire[chiffre].toCharArray();
        for (int i = 0; i < tuiles.length; i++) {
            positionAutres.add(tuiles[i]);
        }
        return positionAutres;

    }
    private static ArrayList<Integer> CoordonneesAdlez (String ligne, ArrayList <Integer> positionJoueur){
        for (int i = 0; i < 2; i++) {
            String[] positionString = ligne.split(",");
            positionJoueur.add(i, Integer.parseInt(positionString[i]));
        }return positionJoueur;
    }
    private static Tuile[][] lireNiv(int t,ArrayList <Character> positionAutres,int largeur,int longueur,Tuile [][]carte){
                            for (int a = 0; a < longueur; a++) {
                                if (positionAutres.get(a).equals('#')) {
                                    carte[t][a] = new Mur();
                                } else if (positionAutres.get(a).equals(' ')) {
                                    carte[t][a] = new Plancher();
                                }
                            }
            return carte;
    }
    private static ArrayList <Integer> CoordonnesTresor (char []convertisseur,ArrayList<Character>positionAutres,
                                                         int i,String [] tableauTemporaire, char []tuiles,ArrayList <Integer> donnesTresor){
        convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
        if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
            convertisseur[0] = positionAutres.get(i + 1);
            String test = Character.toString(convertisseur[0]);
            convertisseur[0] = positionAutres.get(i + 2);
            test += Character.toString(convertisseur[0]);
            donnesTresor.add(Integer.parseInt(test));
        } else {
            convertisseur[0] = positionAutres.get(i + 1);
            donnesTresor.add(Character.getNumericValue(convertisseur[0]));
        }
        positionAutres=LigneEnArray(1,tableauTemporaire,positionAutres,tuiles);
        if (positionAutres.size() >= 2) {
            convertisseur[0] = positionAutres.get(0);
            String test = Character.toString(convertisseur[0]);
            convertisseur[0] = positionAutres.get(1);
            test += Character.toString(convertisseur[0]);
            donnesTresor.add(Integer.parseInt(test));
        } else {
            convertisseur[0] = positionAutres.get(0);
            donnesTresor.add(Character.getNumericValue(convertisseur[0]));
        }
        return donnesTresor;
    }
    private static ArrayList<Integer> CoordonnesPlusieusDonnees(char []convertisseur,ArrayList<Character>positionAutres,
                                                                int i,String [] tableauTemporaire, char []tuiles,ArrayList <Integer> donnes){
        convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
        if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
            convertisseur[0] = positionAutres.get(i + 1);
            String test = Character.toString(convertisseur[0]);
            convertisseur[0] = positionAutres.get(i + 2);
            test += Character.toString(convertisseur[0]);
            donnes.add(Integer.parseInt(test));
        } else {
            convertisseur[0] = positionAutres.get(i + 1);
            donnes.add(Character.getNumericValue(convertisseur[0]));
        }
        positionAutres=LigneEnArray(1,tableauTemporaire,positionAutres,tuiles);
        if (positionAutres.size() >= 2) {
            convertisseur[0] = positionAutres.get(0);
            String test = Character.toString(convertisseur[0]);
            convertisseur[0] = positionAutres.get(1);
            test += Character.toString(convertisseur[0]);
            donnes.add(Integer.parseInt(test));
        } else {
            convertisseur[0] = positionAutres.get(0);
            donnes.add(Character.getNumericValue(convertisseur[0]));
        }
        positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
        convertisseur[0] = positionAutres.get(0);
        donnes.add(Character.getNumericValue(convertisseur[0]));
        positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
        convertisseur[0] = positionAutres.get(0);
        donnes.add(Character.getNumericValue(convertisseur[0]));
        return donnes;
    }


}


    /*package pkgTp2Sim202;

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
    Tuile [][] map;

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
            map=new Tuile[largeur][longueur];
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
            for (int i=0; i<carte.length; i++){
                for (int y=0; y<carte[0].length; y++){
                    if (carte[i][y]=='#'){
                        map[i][y]=new Mur();
                    }
                    else if (carte[i][y]==' '){
                        map[i][y]=new Plancher();
                    }
                }
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
    public int [] getPositionJoueur(){
        return positionJoueur;
    }

}


            BufferedReader fichier = new BufferedReader(new FileReader(niveau + ".txt"));

            String ligne;
            ligne = fichier.readLine();
            int ligneLu = 0;
            while (ligne != null) {
                if (ligneLu == 0) {
                    for (int i = 0; i < 2; i++) {
                        String[] positionString = ligne.split(",");
                        positionJoueur.add(i, Integer.parseInt(positionString[i]));
                    }
                } else if (ligneLu != 0) {
                    String[] tableauTemporaire = ligne.split(",");
                    positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                    if (positionAutres.contains('#')) {
                        longueur = positionAutres.size();
                        while (ligne != null) {
                            largeur++;
                            ligne = fichier.readLine();
                        }
                        break;
                    }

                }
                ligneLu++;
                ligne = fichier.readLine();
            }
            fichier.close();
            ligneLu = 0;
            carte = new Tuile[largeur][longueur];
            BufferedReader fich = new BufferedReader(new FileReader(niveau + ".txt"));
            ligne = fich.readLine();
            while (ligne != null) {
                String[] tableauTemporaire = ligne.split(",");
                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                for (int i = 0; i < positionAutres.size(); i++) {
                    if (positionAutres.get(i).equals('#')) {
                        while (ligne != null) {
                            for (int t = 0; t < largeur; t++) {
                                for (int a = 0; a < longueur; a++) {
                                    if (positionAutres.get(a).equals('#')) {
                                        carte[t][a] = new Mur();
                                    } else if (positionAutres.get(a).equals(' ')) {
                                        carte[t][a] = new Plancher();
                                    }
                                }
                                ligne = fich.readLine();
                                if (ligne == null) {
                                    break;
                                }
                                tableauTemporaire = ligne.split(",");
                                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
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
                    positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                    for (int i = 0; i < positionAutres.size(); i++) {
                        if (positionAutres.get(i).equals(':')) {
                            if (positionAutres.get(0).equals('m')) {
                                convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                                if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(i + 2);
                                    test += Character.toString(convertisseur[0]);
                                    donnesMonstres.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    donnesMonstres.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres=LigneEnArray(1,tableauTemporaire,positionAutres,tuiles);
                                if (positionAutres.size() >= 2) {
                                    convertisseur[0] = positionAutres.get(0);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(1);
                                    test += Character.toString(convertisseur[0]);
                                    donnesMonstres.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(0);
                                    donnesMonstres.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                                convertisseur[0] = positionAutres.get(0);
                                donnesMonstres.add(Character.getNumericValue(convertisseur[0]));
                                positionAutres.clear();
                                tuiles = tableauTemporaire[0].toCharArray();
                                for (int y = 0; y < tuiles.length; y++) {
                                    positionAutres.add(tuiles[y]);
                                }
                                convertisseur[0] = positionAutres.get(0);
                                donnesMonstres.add(Character.getNumericValue(convertisseur[0]));
                            } else if (positionAutres.get(0).equals('p')) {
                                convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                                if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(i + 2);
                                    test += Character.toString(convertisseur[0]);
                                    donnesPancartes.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    donnesPancartes.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres.clear();
                                tuiles = tableauTemporaire[1].toCharArray();
                                for (int y = 0; y < tuiles.length; y++) {
                                    positionAutres.add(tuiles[y]);
                                }
                                if (positionAutres.size() >= 2) {
                                    convertisseur[0] = positionAutres.get(0);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(1);
                                    test += Character.toString(convertisseur[0]);
                                    donnesPancartes.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(0);
                                    donnesPancartes.add(Character.getNumericValue(convertisseur[0]));
                                }
                                int q = 0;
                                String[] temporaire = new String[tableauTemporaire.length - 2];
                                for (int r = tableauTemporaire.length - 1; r > 1; r--) {
                                    temporaire[q] = tableauTemporaire[r];
                                    q++;
                                }
                                int a = 1;
                                for (int r = 0; r < temporaire.length; r++) {
                                    messagePancarte.add(temporaire[temporaire.length - a]);
                                    a++;
                                }
                            } else if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('r')) {
                                convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                                if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(i + 2);
                                    test += Character.toString(convertisseur[0]);
                                    donnesTresor.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    donnesTresor.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres.clear();
                                tuiles = tableauTemporaire[1].toCharArray();
                                for (int y = 0; y < tuiles.length; y++) {
                                    positionAutres.add(tuiles[y]);
                                }
                                if (positionAutres.size() >= 2) {
                                    convertisseur[0] = positionAutres.get(0);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(1);
                                    test += Character.toString(convertisseur[0]);
                                    donnesTresor.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(0);
                                    donnesTresor.add(Character.getNumericValue(convertisseur[0]));
                                }
                            } else if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('e')) {
                                convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                                if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(i + 2);
                                    test += Character.toString(convertisseur[0]);
                                    donnesTp.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(i + 1);
                                    donnesTp.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres.clear();
                                tuiles = tableauTemporaire[1].toCharArray();
                                for (int y = 0; y < tuiles.length; y++) {
                                    positionAutres.add(tuiles[y]);
                                }
                                if (positionAutres.size() >= 2) {
                                    convertisseur[0] = positionAutres.get(0);
                                    String test = Character.toString(convertisseur[0]);
                                    convertisseur[0] = positionAutres.get(1);
                                    test += Character.toString(convertisseur[0]);
                                    donnesTp.add(Integer.parseInt(test));
                                } else {
                                    convertisseur[0] = positionAutres.get(0);
                                    donnesTp.add(Character.getNumericValue(convertisseur[0]));
                                }
                                positionAutres.clear();
                                tuiles = tableauTemporaire[0].toCharArray();
                                for (int y = 0; y < tuiles.length; y++) {
                                    positionAutres.add(tuiles[y]);
                                }
                                convertisseur[0] = positionAutres.get(0);
                                donnesTp.add(Character.getNumericValue(convertisseur[0]));
                                positionAutres.clear();
                                tuiles = tableauTemporaire[0].toCharArray();
                                for (int y = 0; y < tuiles.length; y++) {
                                    positionAutres.add(tuiles[y]);
                                }
                                convertisseur[0] = positionAutres.get(0);
                                donnesTp.add(Character.getNumericValue(convertisseur[0]));
                            }
                        }
                    }
                }
                ligneLu++;
                ligne = fich1.readLine();
            }*/


