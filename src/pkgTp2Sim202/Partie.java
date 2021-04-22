package pkgTp2Sim202;


import java.util.Scanner;

public class Partie {
    boolean statut=false;
    //Vie vieActuelle=Vie.VIVANT;
    Tuile[][] map;
    int niveau=1;
    Heros Adlez=new Heros();
    int [] positionJoueur;

    public void jouer (){
        Scanner sc=new Scanner(System.in);
        String actions;
        int essais=0;
        while (!statut) {
            Niveau floor1 = new Niveau();
            if (essais == 0) {
                System.out.println("Vies: " + Adlez.getVie() + "/6        Force: " + Adlez.getForce() + "            Cristaux: " + Adlez.getCristaux());
                map = floor1.lire(niveau);
                for (int i = 0; i < map.length; i++) {
                    for (int y = 0; y < map[0].length; y++) {
                        System.out.print(map[i][y]);
                    }
                    System.out.println();
                }
            } else {
                //afficher();
            }
            System.out.println("Que voulez-vous faire: ");

            actions=sc.nextLine();/*
            char [] actionEnChar= actions.toCharArray();
            if (essais==0){
            positionJoueur=floor1.getPositionJoueur();}

            for (int i=0; i<actionEnChar.length; i++){
                if (actionEnChar[i]=='w'){
                    if (map[positionJoueur[1]-1][positionJoueur[0]]!='$'&&map[positionJoueur[1]-1][positionJoueur[0]]!='#'){
                        map[positionJoueur[1]-1][positionJoueur[0]]='&';
                        map[positionJoueur[1]][positionJoueur[0]]=' ';
                        positionJoueur[1]-=1;
                    }else{
                        System.out.println("Quelque chose bloque votre chemin!");break;
                    }
                }
                else if (actionEnChar[i]=='a'){
                    if (map[positionJoueur[1]][positionJoueur[0]-1]!='$'&&map[positionJoueur[1]][positionJoueur[0]-1]!='#'){
                        map[positionJoueur[1]][positionJoueur[0]-1]='&';
                        map[positionJoueur[1]][positionJoueur[0]]=' ';
                        positionJoueur[0]-=1;
                    }else{
                        System.out.println("Quelque chose bloque votre chemin!");break;
                    }
                }
                else if (actionEnChar[i]=='s'){
                    if (map[positionJoueur[1]+1][positionJoueur[0]]!='$'&&map[positionJoueur[1]+1][positionJoueur[0]]!='#'){
                        map[positionJoueur[1]+1][positionJoueur[0]]='&';
                        map[positionJoueur[1]][positionJoueur[0]]=' ';
                        positionJoueur[1]+=1;
                    }else{
                        System.out.println("Quelque chose bloque votre chemin!");break;
                    }
                }
                else if (actionEnChar[i]=='d'){
                    if (map[positionJoueur[1]][positionJoueur[0]+1]!='$'&&map[positionJoueur[1]][positionJoueur[0]+1]!='#'){
                        map[positionJoueur[1]][positionJoueur[0]+1]='&';
                        map[positionJoueur[1]][positionJoueur[0]]=' ';
                        positionJoueur[0]+=1;
                    }else{
                        System.out.println("Quelque chose bloque votre chemin!");break;
                    }
                }
                else if (actionEnChar[i]=='c'){
                    Tresor tresor=new Tresor(positionJoueur[1],positionJoueur[0]-1);
                }
                else if (actionEnChar[i]=='x'){

                }
                else if (actionEnChar[i]=='q'){
                    break;
                }
            }if (actionEnChar[actionEnChar.length-1]=='q'){
                break;
            }
            essais++;
        }
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
    private void afficher(){
        System.out.println("Vies: "+Adlez.getVie()+"/6        Force: "+Adlez.getForce()+"            Cristaux: "+Adlez.getCristaux());
        for (int i=0; i< map.length; i++){
            for (int y=0; y< map[0].length; y++){
                System.out.print(map[i][y]);
            }System.out.println();
        }
    */
        }
    }
}
