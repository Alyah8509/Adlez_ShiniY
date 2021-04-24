package pkgTp2Sim202;

import java.util.ArrayList;

public class Heros extends Personnage {
    ArrayList <Integer> position;
    int vie=6;
    int force=1;
    int cristaux=0;
    public int getVie (){
        return vie;
    }
    public int getForce(){
        return force;
    }
    public int getCristaux(){
        return cristaux;
    }
    public void additionnerVie(int combien){
        vie+=combien;
        if (vie>6){
            vie=6;
        }
    }
    public void setPosition (ArrayList <Integer> position){
        this.position=position;
    }
    public int getX (){
        return position.get(0);
    }
    public int getY (){
        return position.get(1);
    }
    public void setX (int x){
        position.set(0, x);
    }
    public void setY (int y){
        position.set(1,y);
    }
    public void afficher (Tuile [][] map){
        System.out.println("Vies: " + vie + "/6        Force: " + force + "            Cristaux: " + cristaux);
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[0].length; y++) {
                System.out.print(map[i][y].getSymbole());
            }
            System.out.println();
        }
    }
    public boolean diminuerVie (int combien){
        vie-=combien;
        if (vie<=0){
            return false;
        }else {return true;}
    }
    public void additionnerCristaux (){
        cristaux++;
    }
}
