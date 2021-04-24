package pkgTp2Sim202;

public class Teleporteur extends Tuile {
    int x;
    int y;
    String symbole="*";
    boolean vide;
    String act="*";
    String adlez="&";
    String monstre="@";
    public String getSymbole (){
        return symbole;
    }
    public void setHero (){
        if (vide){
            symbole=adlez;
        }
    }
    public void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }
    public void revenir (){
        symbole=act;
    }
    public Teleporteur (int x, int y){
        this.x=x;
        this.y=y;
    }
}
