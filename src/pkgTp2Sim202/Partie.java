package pkgTp2Sim202;



public class Partie {
    boolean statut=false;
    Vie vieActuelle=Vie.VIVANT;
    char [][] map;
    int niveau=1;

    public void jouer (){
        while (!statut){
            Niveau floor1=new Niveau();
            map=floor1.lire(niveau);
            for (int i=0; i< map.length; i++){
                for (int y=0; y< map[0].length; y++){
                    System.out.print(map[i][y]);
                }System.out.println();
            }
        statut=true;
        }
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
}
