package pkgTp2Sim202;

import java.util.ArrayList;

public class Heros extends Personnage {

    private ArrayList <Integer> position;//je fonctionne avec une liste plutôt que deux int ici
    private int vie=6;
    private int force=1;
    private int cristaux=0;

    /**
     * Additionne vie+1 pour la potion de vie
     */
    protected void additionnerVie(){
        vie+=1;
        if (vie>=6){
            vie=6;//il ne peut pas dépasser 6
        }
    }

    /**
     * retourne Vie
     * @return
     */
    protected int getVie (){
        return vie;
    }

    /**
     * retourne le nombre de cristaux
     * @return
     */
    protected int getCristaux(){
        return cristaux;
    }
    /**
     * Additionne force pour la potion de force
     */
    protected void addForce (){force+=1;}

    /**
     * Change la position dans la liste
     * @param position la position à modifier
     */
    protected void setPosition (ArrayList <Integer> position){
        this.position=position;
    }

    /**
     * retourne le premier chiffre dans la liste, donc la coordonnée x
     * @return
     */
    protected int getX (){
        return position.get(0);
    }

    /**
     * retourne le deuxième chiffre de la liste, donc y
     * @return
     */
    protected int getY (){
        return position.get(1);
    }

    /**
     * Change le premier chiffre de la liste, change x
     * @param x le nombre à utiliser
     */
    protected void setX (int x){
        position.set(0, x);
    }

    /**
     * Change le deuxième chiffre de la liste
     * change y
     * @param y
     */
    protected void setY (int y){
        position.set(1,y);
    }

    /**
     * Afficher la carte 2d
     * @param map la carte à afficher
     */
    protected void afficher (Tuile [][] map){
        System.out.println("Vies: " + vie + "/6        Force: " + force + "            Cristaux: " + cristaux);
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[0].length; y++) {
                System.out.print(map[i][y].getSymbole());
            }
            System.out.println();
        }
    }

    /**
     * Diminuer la vie selon la force du monstre
     * @param combien combiend de vie à diminuer (force du monstre)
     */
    protected void diminuerVie (int combien){
        vie-=combien;
    }

    /**
     * Attaquer le monstre selon la force
     * @param monstre le monstre à attaquer
     */
    protected void attaquer (Monstre monstre){
        monstre.diminuerVie(force);
        System.out.println("Vie restant de ce monstre:"+monstre.getVie());
    }

    /**
     * Ajoute un cristal
     */
    protected void additionnerCristaux (){
        cristaux++;
    }

}
