package pkgTp2Sim202;


import java.util.Scanner;

public class Partie {
    boolean statut=false;
    Vie vieActuelle=Vie.VIVANT;
    char [][] map;
    int niveau=1;
    Heros Adlez=new Heros();

    public void jouer (){
        Scanner sc=new Scanner(System.in);
        String actions;
        while (!statut){
            Niveau floor1=new Niveau();
            afficher(floor1);
            actions=sc.nextLine();

            niveau++;
        statut=true;
        }
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
    private void afficher(Niveau actuelle){
        System.out.println("Vies: "+Adlez.getVie()+"/6        Force: "+Adlez.getForce()+"            Cristaux: "+Adlez.getCristaux());
        map=actuelle.lire(niveau);
        for (int i=0; i< map.length; i++){
            for (int y=0; y< map[0].length; y++){
                System.out.print(map[i][y]);
            }System.out.println();
        }
        System.out.println("Que voulez-vous faire: ");
    }
}
