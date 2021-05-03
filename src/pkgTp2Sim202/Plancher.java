package pkgTp2Sim202;

public class Plancher extends Tuile{
    private String symbole=" ";
    //Symbole est un String qui change dépendamment des personnages, donc il est variable
    private String act=" ";
    //act est le String qui ne va jamais changer: si Adlez part de la tuile, le symbole redevient ce qu'il était et donc c'est act qui stocke le symbole de départ
    private boolean vide=true;
    private boolean personnage=false;
    private String adlez="&";
    private String monstre="@";

    /**
     * retourne symbole pour l'affichage de la carte
     * @return
     */
    public String getSymbole (){

        return symbole;
    }

    /**
     * montrer que Adlez est ici
     */
    public void setHero (){
        //le boolean vide est la confimation de si on peut piler dessus ou non. Pour mur elle serait false par exemple
        if (vide){
            //remplace le symbole d'espace par &
            symbole=adlez;
        }
    }

    /**
     * montre qu'il y a un monstre d'sur cette case
     * @param personnage
     */
    public void setPersonnage (boolean personnage){
        //il indique s'il y a un monstre dans cette tuile. Utilisé plus tard
        this.personnage=personnage;
    }

    /**
     * montre le symbole @ au lieu de l'espace
     */
    public void setMonstre(){
        //même chose avec @
        if (vide){
            symbole=monstre;
        }
    }

    /**
     * la case va revenir a son etat d'origine
     */
    public void revenir (){
        //s'il y avait un monstre sur la place ou Adlez a pilé, alors le système s'en souvient et quand Adlez part le monstre est toujours là
        if (personnage){
            symbole="@";
        }
        else {
            //si c'était un plancher normal, le symbole redevient ce qu'il était
        symbole=act;}
    }

    /**
     * voir si on peut piler dessus
     * @return
     */
    public boolean getVide (){return vide;}//voir si on peut piler sur cette tuile

    /**
     * Change le symbole
     * @param symbole
     */
    public void setSymbole (String symbole){this.symbole=symbole;}
}
