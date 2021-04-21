package pkgTp2Sim202;

public class Partie {
    boolean statut=false;
    Vie vieActuelle=Vie.VIVANT;

    public void jouer (){
        while (!statut){

        statut=true;
        }
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
}
