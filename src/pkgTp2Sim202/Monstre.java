package pkgTp2Sim202;

public class Monstre extends Personnage {
    int attaque;
    Monstre monstre;
    int hp;
    int x;
    int y;
    int vie;
    Mur mur;
    int[] coordonner = new int[2];
    int[] nouvelleCoordonner = new int[2];
    boolean verif = false;

    public Monstre(int attaque, int hp) {
        this.attaque = attaque;
        this.hp = hp;
    }

    public void marcher(Heros Adlez, Tuile[][] map, int x, int y,int yAdlez, int xAdlez) {
        map[y][x].setMonstre();
        map[y][x].setPersonnage(true);
        coordonner[0] = x;
        coordonner[1] = y;

        //for (int i = 0; i < map.length; i++) {
           //for (int j = 0; j < map.length; j++) {
               //faire la condition d'avancement
                
        attaquerHeros(map,Adlez,coordonner[0],coordonner[1],yAdlez,xAdlez);
                if (yAdlez - coordonner[1]<= -1 && map[coordonner[1]-1][coordonner[0]]!=mur){
                    coordonner[1]-=1;
                }
                if (yAdlez - coordonner[1] >=1&& map[coordonner[1]+1][coordonner[0]]!=mur){
                    coordonner[1]+=1;
                }
                if (xAdlez-coordonner[0]<=-1&& map[coordonner[1]][coordonner[0]-1]!=mur){
                    coordonner[0]-=1;
                }
                if (xAdlez-coordonner[0]>=1&& map[coordonner[1]][coordonner[0]+1]!=mur){
                    coordonner[0]+=1;
                }
            //}
        //}
    }




    public void partir(Tuile [][]map, int x, int y){
        map[y][x].setPersonnage(false);
        map[y][x].revenir();
    }
    public void deplacer (){

    }

    public int getHp() {

        return hp;
    }
    private void attaquerHeros(Tuile[][] map, Heros Adlez, int xMonstre, int yMonstre, int yAdlez, int xAdlez){

            if (xAdlez+1 == xMonstre && yAdlez == yMonstre || xAdlez-1 == xMonstre && yMonstre == yAdlez || yAdlez-1== yMonstre && xAdlez == xMonstre || yAdlez+1== yMonstre && xAdlez == xMonstre){
            //attaquer adlez
                Adlez.diminuerVie(attaque);
            }
            else if (xAdlez + 1 == xMonstre && yAdlez + 1 == yMonstre || xAdlez - 1 == xMonstre && yAdlez + 1 == yMonstre || xAdlez - 1 == xMonstre && yAdlez - 1 == yMonstre || xAdlez + 1 == xMonstre && yAdlez - 1 == yMonstre){
                Adlez.diminuerVie(attaque);
            }



    }
    public boolean diminuerVie (int combien) {
        vie -= combien;
        if (vie <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
