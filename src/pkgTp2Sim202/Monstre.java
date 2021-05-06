package pkgTp2Sim202;

public class Monstre extends Personnage {
    private int attaque;
    private int hp;
    private int x;
    private int y;

    /**
     * Constructeur de monstre, décide du hp et force au début
     * @param attaque
     * @param hp
     */
    public Monstre(int attaque, int hp) {
        this.attaque = attaque;
        this.hp = hp;
    }

    /**
     * retourne x
     * @return
     */
    public int getX (){return x;}

    /**
     * retourne la vie
     * @return
     */
    public int getVie (){return hp;}

    /**
     * retourner Y
     * @return
     */
    public int getY (){return y;}

    /**
     * Il regarde les coordonnées de Adlez et détermine s'il faut se déplacer ou attaquer
     * @param Adlez nécessite le personnage pour ses coordonnées
     * @param map pour déplacer le monstre
     */
    public void verifier (Heros Adlez, Tuile [][]map){
        if (Adlez.getX()-x==-1||Adlez.getX()-x==0||Adlez.getX()-x==1){//c'est des maths
            if (Adlez.getY()-y==-1||Adlez.getY()-y==0||Adlez.getY()-y==1){
                attaquerHeros(Adlez);//voir la méthode attaquerHeros
            }
            else {//s'ils ont le même x mais pas le même y
                if (Adlez.getY()-y<=-1){//s'il est au moins 2 cases en haut
                    if (map[y-1][x].getVide()){//vérifie la Tuile (mur/trésor ou non)
                        partir(map,x,y);//part de cette coordonnée
                        y-=1;
                        marcher(map,x,y);}//et il marche vers le haut
                }else if (Adlez.getY()-y>=1){//même chose, si Adlez est en bad
                    if (map[y+1][x].getVide()){
                        partir(map,x,y);
                        y+=1;
                        marcher(map,x,y);}
                }
            }
        }
        else {//si Adlez est loin
            if (Adlez.getX()-x<=-1){//analyse les coordonnées x
                if (map[y][x-1].getVide()){//si Adlez est à gauche
                    partir(map,x,y);
                    x-=1;//avance vers la gauche: change x dans cette classe
                    marcher(map,x,y);//et mise à jour dans la carte 2d de Tuile
                }
            }else if (Adlez.getX()-x>=1){
                if (map[y][x+1].getVide()){
                    partir(map,x,y);
                    x+=1;
                    marcher(map,x,y);
                }
            }
            if (Adlez.getY()-y<=-1){//même chose avec Y
                if (map[y-1][x].getVide()){
                    partir(map,x,y);
                    y-=1;
                    marcher(map,x,y);}
            }else if (Adlez.getY()-y>=1){
                if (map[y+1][x].getVide()){
                    partir(map,x,y);
                    y+=1;
                    marcher(map,x,y);}
            }
        }
    }

    /**
     * Déplacement vers les coordonnées décidés
     * @param map la carte pour marcher
     * @param x coordonnée x pour se déplacer
     * @param y coordonnée y pour se déplacer
     */

    public void marcher(Tuile[][] map, int x, int y) {//simplement placer @ sur une tuile
        map[y][x].setMonstre();//voir setMonstre dans Plancher
        map[y][x].setPersonnage(true);//cette Tuile a un personnage présentemment (pour ne pas disparaître après que
        //Adlez marche dessus (Voir Plancher pour plus d'explication) )
        this.x=x;this.y=y;//change x et y
    }

    /**
     * efface @ dans la tuile précédente
     * @param map
     * @param x
     * @param y
     */
    public void partir(Tuile [][]map, int x, int y){
        map[y][x].setPersonnage(false);
        map[y][x].revenir();
    }

    /**
     * Diminue la vie de ce héros selon son attaque
     * @param Adlez
     */
    private void attaquerHeros(Heros Adlez){
        Adlez.diminuerVie(attaque);
    }

    /**
     * Si le monstre est attaqué (selon la force de Adlez)
     * @param combien la force de Adlez
     */
    public void diminuerVie (int combien) {
        hp -= combien;
    }
}
