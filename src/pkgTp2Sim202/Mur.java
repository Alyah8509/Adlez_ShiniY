package pkgTp2Sim202;

import java.awt.*;
import java.io.Serializable;

public class Mur extends Tuile implements Serializable {
    private String symbole="#";
    private boolean vide=false;//ne peut pas marcher dessus

    /**
     * retourne le symbole
     * @return
     */
    protected String getSymbole (){
        return symbole;
    }

    /**
     * retourne si on peut marcher dessus ou non
     * @return
     */
    protected boolean getVide (){return vide;}
}//Mur n'a presque rien vu qu'un ne peut pas piler dessus.
