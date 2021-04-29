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

    /**
     * Ici, je passe en paramètre la carte pour chaque niveau ainsi que les
     * informations importantes tel que le nombre de vie et les points d'attaques des monstres
     * @param niveau
     * @return
     */
    public Tuile[][] lire(int niveau) {
        positionJoueur.clear();positionAutres.clear();longueur=0;largeur=0;monstres.clear();donnesMonstres.clear();
        donnesPancartes.clear();messagePancarte.clear();donnesTresor.clear();donnesTp.clear();
        //reset tous les Arraylist au cas ou
        try {

            BufferedReader fichier = new BufferedReader(new FileReader(niveau + ".txt"));
            String ligne;
            ligne = fichier.readLine();
            int ligneLu = 0;//pour raccourcir le programme (plus facile à debug)
            while (ligne != null) {
                if (ligneLu == 0) {
                    positionJoueur=CoordonneesAdlez(ligne,positionJoueur);
                    //la première ligne est toujours les coordonnées du joueur
                } else if (ligneLu != 0) {//si ce n'est pas la première ligne,
                    String[] tableauTemporaire = ligne.split(",");//sépare la ligne en tableau
                    positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);//voir la méthode en question
                    if (positionAutres.contains('#')) {//compter la grandeur de la carte (à partir de #)
                        longueur = positionAutres.size();//le size du Arraylist est la longueur
                        while (ligne != null) {//lire la ligne, ajouter 1 à largeur puis répéter jusqu'à ce qu'il n'y a plus rien
                            largeur++;
                            ligne = fichier.readLine();
                        }
                        break;//vu que le fichier est déjà lu, il n'y a plus de ligne à lire et donc break
                    }

                }
                ligneLu++;
                ligne = fichier.readLine();
            }
            fichier.close();
            ligneLu = 0;//reset la ligneLu
            carte = new Tuile[largeur][longueur];//créer un tableau 2d avec la grandeur trouvé (seulement les murs et planchers)
            BufferedReader fich = new BufferedReader(new FileReader(niveau + ".txt"));
            ligne = fich.readLine();//ouvre le fichier de nouveau
            while (ligne != null) {//remplir le tableau 2d ici
                String[] tableauTemporaire = ligne.split(",");
                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);//si on est rendu à la ligne de la carte, alors il ne devrait pas avoir de virgule
                for (int i = 0; i < positionAutres.size(); i++) {//donc ça devrait être un tableau du genre {"##      ##"}
                    if (positionAutres.get(i).equals('#')) {
                        while (ligne != null) {
                            for (int t = 0; t < largeur; t++) {
                                carte=lireNiv(t,positionAutres,largeur,longueur,carte);//pour chaque ligne à partir de celui ou il y a #, les données sont en paramètres
                                ligne = fich.readLine();//ligne suivante
                                if (ligne == null) {//s'il n'y a plus rien
                                    break;
                                }
                                tableauTemporaire = ligne.split(",");//refait la même chose, avec la deuxième ligne
                                positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                            }
                        }
                    }
                }
                ligne = fich.readLine();
            }
            fich.close();
            BufferedReader fich1 = new BufferedReader(new FileReader(niveau + ".txt"));
            ligne = fich1.readLine();//rendu ici, il faut ajouter les détails (pancartes, monstre, etc.)
            while (ligne != null) {
                if (ligneLu != 0) {//ce n'est 100% pas la première ligne qu'il y a les données de monstres ou de trésors
                    String[] tableauTemporaire = ligne.split(",");
                    positionAutres=LigneEnArray(0,tableauTemporaire,positionAutres,tuiles);
                    //la même formule: passe les premiers charactères avant la virgule en Arraylist
                    for (int i = 0; i < positionAutres.size(); i++) {
                        if (positionAutres.get(i).equals(':')) {//si il y a ':', alors c'est sûrement quelque chose à placer
                            if (positionAutres.get(0).equals('m')) {//si le premier charactère du liste est m, alors c'est monstre
                                donnesMonstres=CoordonnesPlusieusDonnees(convertisseur,positionAutres,i,tableauTemporaire,tuiles,donnesMonstres);
                            } else if (positionAutres.get(0).equals('p')) {//pancarte:
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
                                }//à peu près la même chose que monstre: vérifier s'il est un chiffre ou un nombre, puis le range dans une liste
                                positionAutres=LigneEnArray(1,tableauTemporaire,positionAutres,tuiles);
                                //le y cette fois
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
                                //j'ai passé le message en paramètre aussi
                                int q = 0;
                                String[] temporaire = new String[tableauTemporaire.length - 2];
                                //créer un nouveau tableau avec une taille de tableau temporaire moins 2 (moins l'espace pour les coordonnées x et y)
                                //donc si c'est un message avec virgule, la taille est de 2. Sinon, sa taille est de 1.
                                for (int r = tableauTemporaire.length - 1; r > 1; r--) {//par exemple, le tableauTemporaire est de taille 4: {"5","8","Allo","ça va?"}
                                    // r= 3, temporaire à 0= ça va (tableauTemporaire à la position 3), q+1, r=2, temp. à 1= Allo (tabTem^. à la position 2), q+1, r=1, sort du for
                                    temporaire[q] = tableauTemporaire[r];
                                    q++;
                                }
                                //mais le message est possiblement à l'envers, s'il a une virgule
                                int a = 1;
                                for (int r = 0; r < temporaire.length; r++) {
                                    /*r=0, messagePancarte.add (temporaire position (temporaire.lengh-a, donc 2-1=1)), messagePancarte.get(0)=Allo, a+1=2
                                    r=1, messageP.add (temp. position (2-2))m messageP.get(1)=ça va, a+1=3
                                    r=2: sort du for (temporaire.lengh=2)
                                    Bien sûr si c'est sans virgule, le length de temporaire =1 donc il sort après une fois seulement
                                    */
                                    messagePancarte.add(temporaire[temporaire.length - a]);
                                    a++;
                                }
                            } else if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('r')) {//trésor
                                donnesTresor=CoordonnesTresor(convertisseur,positionAutres,i,tableauTemporaire,tuiles,donnesTresor);
                            } else if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('e')) {//téléporteur
                                donnesTp=CoordonnesPlusieusDonnees(convertisseur,positionAutres,i,tableauTemporaire,tuiles,donnesTp);
                                //la même méthode que monstre mais au lieu de nbdeVie et attaque, c'est x et y (destination à la place)
                            }
                        }
                        else if (positionAutres.get(i).equals('#')){
                            //faciliter le debug: les murs sont déja placé donc pas besoin de la carte
                            break;
                        }
                    }
                }
                ligneLu++;//pour faciliter le debug
                ligne = fich1.readLine();
            }

        /*if (donnesMonstres!=null) {

            for (int i=0; i<donnesMonstres.size(); i+=4){
                monstres.add(new Monstre(donnesMonstres.get(i+2),donnesMonstres.get(i+3)));
            }
        }*/
        if (donnesTp.size()!=0) {//car il est possible qu'il n'a aucune teleporteur
            for (int i=0; i<donnesTp.size(); i+=4){//bond de 4 vu qu'il y a 4 infos par téléporteur
                carte[donnesTp.get(i+1)][donnesTp.get(i)]=new Teleporteur(donnesTp.get(i+2),donnesTp.get(i+3));
                /*carte à la position y x (dans la liste c'est x y donc c'est inversé)=téléporteur (x et y de desitination (situé après x et y position))
                exemple: dans une lite 4,5,10,14, c'est carte [5][4]=new teleporteur (10,14) 4 et 5 sont la position du * et 15 et 14 sont les destinations
                */
            }
        }
        if (donnesPancartes.size()!=0) {
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
        //la place d'Adlez et des monstres est mis après (dans partie) vu qu'il n'y a pas de tuile heros ni monstre
        return positionJoueur;
    }
    public ArrayList<Integer> getDonnesMonstres (){
        return donnesMonstres;
    }
    public ArrayList<String> getMessagePancarte(){return messagePancarte;}
    private static ArrayList<Character> LigneEnArray (int chiffre,String[] tableauTemporaire, ArrayList<Character> positionAutres, char [] tuiles){
        positionAutres.clear();//méthode pour passer le tableau temporaire en Arraylist. PositionAutres est un Arraylist donc il fait le vider avant
        tuiles = tableauTemporaire[chiffre].toCharArray();//le chiffre s'agit de la position du tableau temporaire
        for (int i = 0; i < tuiles.length; i++) {//Tuile est pour passer le String en charactère
            positionAutres.add(tuiles[i]);
        }
        return positionAutres;//retourne le arraylist
        /*
        Exemple: si dans le fichier c'est: 156,Adlez,000
        le tableau temporaire serait {"156","Adlez","000"}
        donc si chiffre=0, alors tableauTemporaire[chiffre]=156
        avec tuile, les composantes de ce tableau char serait {'1','5','6'}
        ensuite positionAutres qui est un Arraylist va avoir des charactères,comme:
        positionAutres.add ('1'), positionAutres.add('5'), etc.
        Et le programme va retouner un Arraylist avec les charactères 1, 5 et 6.
         */

    }
    private static ArrayList<Integer> CoordonneesAdlez (String ligne, ArrayList <Integer> positionJoueur){
        for (int i = 0; i < 2; i++) {//méthode qui add les coordonnées de Adlez
            String[] positionString = ligne.split(",");//position String stocke 2 choses: les coordonnées x et y de Adlez
            positionJoueur.add(i, Integer.parseInt(positionString[i]));//le transforme en int en le met dans un Arraylist
        }return positionJoueur;
    }
    private static Tuile[][] lireNiv(int t,ArrayList <Character> positionAutres,int largeur,int longueur,Tuile [][]carte){
                            for (int a = 0; a < longueur; a++) {//avance de gauche à droite
                                if (positionAutres.get(a).equals('#')) {
                                    carte[t][a] = new Mur();//vu que c'est une carte 2d de tuile, alors ce sont des classes qui sont mis dedans
                                } else if (positionAutres.get(a).equals(' ')) {
                                    carte[t][a] = new Plancher();
                                }
                            }
            return carte;
    }
    private static ArrayList <Integer> CoordonnesTresor (char []convertisseur,ArrayList<Character>positionAutres,
                                                         int i,String [] tableauTemporaire, char []tuiles,ArrayList <Integer> donnesTresor){
        //la même chose que monstre mais avec deux chiffres au lieu de 4.
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
        //c'est un peu plus compliqué car ça se peut que les coordonnées soient à 2 chiffres
        convertisseur[0] = positionAutres.get(positionAutres.size() - 2);//dans un tableau, ranger l'avant-dernier char de la liste
        if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {//il devrait être ':' s'il est une coordonnée à un chiffre. Sinon,
            convertisseur[0] = positionAutres.get(i + 1);/* le i dépend de la situation. Dans lire() le i est la position
            ou il y a ':' (voir environ ligne 93). Donc le char. après : est sûrement un chiffre
            */
            String test = Character.toString(convertisseur[0]);//le premier chiffre est passé en String pour mieux le coller après
            convertisseur[0] = positionAutres.get(i + 2);//si c'est un nombre à deux positions alors il devrait avoir 2 char. après : au lieu d'un
            test += Character.toString(convertisseur[0]);//ajoute à ce String test le deuxième chiffre
            donnes.add(Integer.parseInt(test));//donnees est une liste int. Donc ajouter des nombres n'a pas de problème.
        } else {
            convertisseur[0] = positionAutres.get(i + 1);//sinon simplement mettre le chiffre dans un tableau et le convertir en int
            donnes.add(Character.getNumericValue(convertisseur[0]));
        }
        positionAutres=LigneEnArray(1,tableauTemporaire,positionAutres,tuiles);
        //pour les coordonnées en y, il sont après une virgule donc à la deuxième place du tableau (chiffre=1 au lieu de 0)
        if (positionAutres.size() >= 2) {//positionAutres à la deuxième position est quelque chose du genre de {'1','5'} (y=15)
            convertisseur[0] = positionAutres.get(0);
            String test = Character.toString(convertisseur[0]);
            convertisseur[0] = positionAutres.get(1);//j'utilise 1 et 0 au lieu de i car il ne peut avoir qu'une ou deux places.
            test += Character.toString(convertisseur[0]);
            donnes.add(Integer.parseInt(test));
        } else {
            convertisseur[0] = positionAutres.get(0);
            donnes.add(Character.getNumericValue(convertisseur[0]));//environ la même chose
        }
        positionAutres=LigneEnArray(2,tableauTemporaire,positionAutres,tuiles);//position 3: pour monstre c'est son nombre de vie
        if (positionAutres.size() >= 2) {
            convertisseur[0] = positionAutres.get(0);
            String test = Character.toString(convertisseur[0]);
            convertisseur[0] = positionAutres.get(1);
            test += Character.toString(convertisseur[0]);
            donnes.add(Integer.parseInt(test));
        } else {
            convertisseur[0] = positionAutres.get(0);
            donnes.add(Character.getNumericValue(convertisseur[0]));
        }//passé dans une liste donneesMonstre pour faciliter la suite (créer une instance monstre par exemple, je peux chercher ses donnes directement avec cette liste)
        positionAutres=LigneEnArray(3,tableauTemporaire,positionAutres,tuiles);
        //point d'attaque (dernière position)
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
        return donnes;//retourne quelque chose du genre {12, 6, 2, 4, 15, 5, 1, 4} 12=x 6=y 2=nb de vie et 4=attaque. 15=x 5=y 1=nb de vie et 4=attaque.
        //il s'agit de données pour 2 monstres (faire des bond de 4 si je veux accéder à ces données)
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


