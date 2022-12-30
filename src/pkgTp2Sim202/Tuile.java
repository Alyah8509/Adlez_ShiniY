package pkgTp2Sim202;

import java.io.Serializable;

public abstract class Tuile implements Serializable {
    //quelques variables ne sont pas utilisé: ce sont juste tous les variables que les sous-classes utilisent
    // (pour faciliter le debug)
    private String symbole;
    private String symbole2;
    private String act;
    private boolean vide;
    private String adlez;
    private boolean personnage;
    private String monstre;
    private int x; private int y;
    private boolean tp;
    private Item [] item;
    private boolean ouvert;
//beaucoup de ces méthodes sont vides: ils ne sont pas utlisés mais plutôt overridés
    /**
     * retourne le symbole de la tuile
     * @return
     */
    protected String getSymbole (){
        return symbole;
    }

    /**
     * Change le String Act (Pour trésor seulement)
     */
    protected void setAct (){
    }

    /**
     * montrer "&" au lieu du symbole
     */
    protected void setHero (){
        if (vide){
            symbole=adlez;
        }
    }

    /**
     * utiliser est seulement pour trésor, mais il doit être dans tuile aussi pour être appelé.
     * Voir dans trésor pour voir ce qu'il fait
     * @param Adlez
     */
        protected void utiliser (Heros Adlez){
        }

    /**
     * pour trésor aussi
     * @param niveau
     * @param x
     * @param y
     */
    protected void setItem (int niveau, int x, int y){

    }

    /**
     * montre s'il y a un monstre sur la case
     * @param personnage true s'il y a quelqu'un, false sinon
     */
    protected void setPersonnage (boolean personnage){
        this.personnage=personnage;
    }

    /**
     * si on peut piler dessus, remplace le symbole par "@"
     */
    protected void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }

    /**
     * il n'y a plus de personnage sur la case donc il revient à son symbole original
     */
    protected void revenir (){
            symbole=act;
    }

    /**
     * montre si on peut piler dessus ou non
     * @return oui ou non
     */
    protected boolean getVide (){return vide;}


    /**
     * retourne x du téléporteur
     * @return
     */
    protected int getTpx (){
        return x;
    }

    /**
     * retourne y du téléporteur
     * @return
     */
    protected int getTpy(){
        return y;
    }

    /**
     * Change le symbole (voir le combat dans Partie)
     * @param symbole à changer
     */
    protected void setSymbole (String symbole){this.symbole=symbole;}
}
