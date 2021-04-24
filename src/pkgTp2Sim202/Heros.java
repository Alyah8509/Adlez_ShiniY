package pkgTp2Sim202;

public class Heros extends Personnage {
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
    public void afficher (Tuile [][] map){
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[0].length; y++) {
                System.out.print(map[i][y]);
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
