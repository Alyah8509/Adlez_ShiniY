package pkgTp2Sim202;

public abstract class Tuile {
    String symbole;
    String symbole2;
    String act;
    boolean vide;
    String adlez;
    boolean personnage;
    String monstre;
    int x; int y;
    boolean tp;
    Item [] item;
    boolean ouvert;
    public String getSymbole (){
        return symbole;
    }
    public void setHero (){
        if (vide){
            symbole=adlez;
        }
    }
    public Item [] getItem (){
        return item;
    }
    public void setItem (int niveau, int x, int y){

    }
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }
    public void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }
    public void revenir (){
            symbole=act;
    }
    public void ouvrir (){symbole=symbole2;}
    public boolean getVide (){return vide;}
    public void setTp (int x, int y){
        if (tp){
        this.x=x;
        this.y=y;}
    }
    public int getTpx (){
        return x;
    }
    public int getTpy(){
        return y;
    }
}
