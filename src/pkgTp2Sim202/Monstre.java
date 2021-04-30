package pkgTp2Sim202;

public class Monstre extends Personnage {
    private int attaque;
    private int hp;
    private int x;
    private int y;
    public Monstre (int attaque, int hp){
        this.attaque=attaque;
        this.hp=hp;
    }
    public int getHp (){
        return hp;
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
