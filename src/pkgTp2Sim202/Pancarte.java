package pkgTp2Sim202;

import java.util.ArrayList;

public class Pancarte extends Tuile {
    private String symbole="!";
    private String act="!";
    private boolean vide=true;
    private String adlez="&";
    private String monstre="@";
    private boolean personnage=false;

    /**
     * retourne symbole
     * @return
     */
    public String getSymbole (){
        return symbole;
    }

    /**
     * affiche & au lieu du symbole
     */
    public void setHero (){
        if (vide){
            symbole=adlez;
        }
    }

    /**
     * affiche @ au lieu du symbole
     */
    public void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }

    /**
     * monstre s'il y avait un monstre sur la case
     * @param personnage
     */
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    /**
     * revient au symbole d'avant
     */
    public void revenir (){
        if (personnage){
            symbole="@";
        }else {

            symbole=act;}
    }

    /**
     * montre si on peut marcher dessus
     * @return
     */
    public boolean getVide (){return vide;}
}
