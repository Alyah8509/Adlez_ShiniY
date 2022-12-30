package pkgTp2Sim202;

import java.io.Serializable;

public class Teleporteur extends Tuile implements Serializable {
    private int x;
    private int y;
    private String symbole = "*";
    private boolean vide = true;
    private String act = "*";
    private String adlez = "&";
    private String monstre = "@";
    private boolean personnage=false;

    /**
     * le x et le y est les coordonnées du destination (ou il faut se téléporter)
     * @param x
     * @param y
     */
    protected Teleporteur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * montre s'il y avait un monstre sur cette case
     * @param personnage false si rien n'est là, true si un monstre est là.
     */
    protected void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    /**
     * retourne symbole
     * @return
     */
    protected String getSymbole() {
        return symbole;
    }

    /**
     * remplace symbole par "&"
     */
    protected void setHero() {
        if (vide) {
            symbole = adlez;
        }
    }

    /**
     * remplace symbole par "@"
     */

    protected void setMonstre() {
        if (vide) {
            symbole = monstre;
        }
    }

    /**
     * revient au symbole d'avant
     */
    protected void revenir() {
        if (personnage){
            symbole="@";
        }else {

            symbole=act;}
    }



    /**
     * si on peut marcher dessus
     * @return oui ou non
     */
    protected boolean getVide() {
        return vide;
    }


    /**
     * retourne x (destination)
     * @return
     */
    protected int getTpx (){
        return x;
    }

    /**
     * retourne y (destination)
     * @return
     */
    protected int getTpy(){
        return y;
    }

    /**
     * Change le symbole
     * @param symbole String à remplacer
     */
    protected void setSymbole (String symbole){this.symbole=symbole;}
}
