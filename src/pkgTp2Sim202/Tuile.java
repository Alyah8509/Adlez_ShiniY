package pkgTp2Sim202;

public abstract class Tuile {
    String symbole;
    String symbole2;
    String act;
    boolean vide;
    String adlez;
    String monstre;
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
    public void ouvrir (){symbole=symbole2;}
    public boolean getVide (){return vide;}
}
