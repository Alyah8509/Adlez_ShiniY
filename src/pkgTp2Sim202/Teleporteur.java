package pkgTp2Sim202;

public class Teleporteur extends Tuile {
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
    public Teleporteur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * montre s'il y avait un monstre sur cette case
     * @param personnage
     */
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    /**
     * retourne symbole
     * @return
     */
    public String getSymbole() {
        return symbole;
    }

    /**
     * remplace symbole par &
     */
    public void setHero() {
        if (vide) {
            symbole = adlez;
        }
    }

    /**
     * remplace symbole par @
     */

    public void setMonstre() {
        if (vide) {
            symbole = monstre;
        }
    }

    /**
     * revient au symbole d'avant
     */
    public void revenir() {
        if (personnage){
            symbole="@";
        }else {

            symbole=act;}
    }



    /**
     * si on peut marcher dessus
     * @return
     */
    public boolean getVide() {
        return vide;
    }


    /**
     * retourne x (destination)
     * @return
     */
    public int getTpx (){
        return x;
    }

    /**
     * retourne y (destination)
     * @return
     */
    public int getTpy(){
        return y;
    }
}
