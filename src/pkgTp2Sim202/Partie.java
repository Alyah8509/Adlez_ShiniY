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
            niveau++;
            Niveau floor2=new Niveau();
            map=floor2.lire(niveau);
            for (int i=0; i< map.length; i++){
                for (int y=0; y< map[0].length; y++){
                    System.out.print(map[i][y]);
                }System.out.println();
            }
            niveau++;
            Niveau floor3=new Niveau();
            map=floor3.lire(niveau);
            for (int i=0; i< map.length; i++){
                for (int y=0; y< map[0].length; y++){
                    System.out.print(map[i][y]);
                }System.out.println();
            }
            niveau++;
            Niveau floor4=new Niveau();
            map=floor4.lire(niveau);
            for (int i=0; i< map.length; i++){
                for (int y=0; y< map[0].length; y++){
                    System.out.print(map[i][y]);
                }System.out.println();
            }
            niveau++;
            Niveau floor5=new Niveau();
            map=floor5.lire(niveau);
            for (int i=0; i< map.length; i++){
                for (int y=0; y< map[0].length; y++){
                    System.out.print(map[i][y]);
                }System.out.println();
            }
            niveau++;
            Niveau floor6=new Niveau();
            map=floor6.lire(niveau);
            for (int i=0; i< map.length; i++){
                for (int y=0; y< map[0].length; y++){
                    System.out.print(map[i][y]);
                }System.out.println();
            }
            niveau++;
        statut=true;
        }
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
}
