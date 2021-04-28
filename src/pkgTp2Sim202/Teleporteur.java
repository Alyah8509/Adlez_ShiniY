package pkgTp2Sim202;

public class Teleporteur extends Tuile {
    int x;
    int y;
    boolean tp = true;
    String symbole = "*";
    boolean vide = true;
    String act = "*";
    String adlez = "&";
    String monstre = "@";
    boolean personnage=false;
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setHero() {
        if (vide) {
            symbole = adlez;
        }
    }

    public void setMonstre() {
        if (vide) {
            symbole = monstre;
        }
    }

    public void revenir() {
        if (personnage){
            symbole="@";
        }else {

            symbole=act;}
    }

    public Teleporteur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean getVide() {
        return vide;
    }

    public void setTp(int x, int y) {
        if (tp) {
            this.x = x;
            this.y = y;
        }
    }
    public int getTpx (){
        return x;
    }
    public int getTpy(){
        return y;
    }
}
