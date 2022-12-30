package pkgTp2Sim202;

import java.io.Serializable;
import java.util.ArrayList;

public class Pancarte extends Tuile implements Serializable {
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
    protected String getSymbole (){
        return symbole;
    }

    /**
     * affiche "&" au lieu du symbole
     */
    protected void setHero (){
        if (vide){
            symbole=adlez;
        }
    }

    /**
     * Change le symbole
     * @param symbole le symbole à changer
     */
    protected void setSymbole (String symbole){this.symbole=symbole;}

    /**
     * affiche @ au lieu du symbole
     */
    protected void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }

    /**
     * monstre s'il y avait un monstre sur la case
     * @param personnage si monstre part (false) ou arrive (true)
     */
    protected void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    /**
     * revient au symbole d'avant
     */
    protected void revenir (){
        if (personnage){
            symbole="@";
        }else {

            symbole=act;}
    }

    /**
     * montre si on peut marcher dessus
     * @return
     */
    protected boolean getVide (){return vide;}
}
