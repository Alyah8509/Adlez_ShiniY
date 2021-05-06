package pkgTp2Sim202;

public class Mur extends Tuile {
    private String symbole="#";
    private boolean vide=false;//ne peut pas marcher dessus

    /**
     * retourne le symbole
     * @return
     */
    public String getSymbole (){
        return symbole;
    }

    /**
     * retourne si on peut marcher dessus ou non
     * @return
     */
    public boolean getVide (){return vide;}
}//Mur n'a presque rien vu qu'un ne peut pas piler dessus.
