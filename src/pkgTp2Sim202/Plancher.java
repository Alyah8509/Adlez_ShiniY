package pkgTp2Sim202;

public class Plancher extends Tuile{
    String symbole=" ";
    String act=" ";
    boolean vide=true;
    boolean personnage=false;
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
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }
    public void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }
    public void revenir (){
        if (personnage){
            symbole="@";
        }else {

        symbole=act;}
    }
    public void ouvrir (){symbole=symbole2;}
    public boolean getVide (){return vide;}
}
