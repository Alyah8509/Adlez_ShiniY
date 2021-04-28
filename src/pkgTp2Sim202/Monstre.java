package pkgTp2Sim202;

public class Monstre extends Personnage {
    int attaque;
    int hp;
    int x;
    int y;
    public Monstre (int attaque, int hp){
        this.attaque=attaque;
        this.hp=hp;
    }
    public void marcher(Tuile [][]map, int x, int y){
        map[y][x].setMonstre();
        map[y][x].setPersonnage(true);
        this.x=x;
        this.y=y;
    }
    public void partir(Tuile [][]map, int x, int y){
        map[y][x].setPersonnage(false);
        map[y][x].revenir();
    }
    public void placer (){

    }
}
