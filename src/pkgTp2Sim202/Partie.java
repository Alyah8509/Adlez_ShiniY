package pkgTp2Sim202;

public class Partie {
    boolean statut=false;
    Vie vieActuelle=Vie.VIVANT;

    public void jouer (){
        while (!statut){
            Niveau floor1=new Niveau();
            floor1.lire(1);
        statut=true;
        }
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
}
