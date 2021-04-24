package pkgTp2Sim202;

public class Mur extends Tuile {
    String symbole="#";
    String act="#";
    boolean vide=false;
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
}
