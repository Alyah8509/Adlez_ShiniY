package pkgTp2Sim202;

public abstract class Tuile {
    String symbole;
    String symbole2;
    String act;
    boolean vide;
    String adlez;
    boolean personnage;
    String monstre;
    int x; int y;
    boolean tp;
    Item [] item;
    boolean ouvert;
//beaucoup de ces méthodes sont vides: ils ne sont pas utlisés mais plutôt overr
    /**
     * retourne le symbole de la tuile
     * @return
     */
    public String getSymbole (){
        return symbole;
    }

    /**
     * montrer & au lieu du symbole
     */
    public void setHero (){
        if (vide){
            symbole=adlez;
        }
    }

    /**
     * utiliser est seulement pour trésor, mais il doit être dans tuile aussi pour être utilisé.
     * Voir dans trésor pour voir ce qu'il fait
     * @param Adlez
     */
        public void utiliser (Heros Adlez){
        }

    /**
     * pour trésor aussi
     * @param niveau
     * @param x
     * @param y
     */
    public void setItem (int niveau, int x, int y){

    }

    /**
     * montre s'il y a un monstre sur la case
     * @param personnage
     */
    public void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    /**
     * si on peut piler dessus, remplace le symbole par @
     */
    public void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }

    /**
     * il n'y a plus de personnage sur la case donc il revient à son symbole original
     */
    public void revenir (){
            symbole=act;
    }

    /**
     * montre si on peut piler dessus ou non
     * @return
     */
    public boolean getVide (){return vide;}


    /**
     * retourne x du téléporteur
     * @return
     */
    public int getTpx (){
        return x;
    }

    /**
     * retourne y du téléporteur
     * @return
     */
    public int getTpy(){
        return y;
    }
}
