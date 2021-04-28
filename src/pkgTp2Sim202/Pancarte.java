package pkgTp2Sim202;

import java.util.ArrayList;

public class Pancarte extends Tuile {
    String symbole="!";
    String act="!";
    boolean vide=true;
    String adlez="&";
    String monstre="@";
    boolean personnage=false;
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
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }
    public void revenir (){
        if (personnage){
            symbole="@";
        }else {

            symbole=act;}
    }
    public boolean getVide (){return vide;}
}
