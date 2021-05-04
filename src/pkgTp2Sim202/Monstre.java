package pkgTp2Sim202;

public class Monstre extends Personnage {
    private int attaque;
    private int hp;
    private int x;
    private int y;
    public Monstre(int attaque, int hp) {
        this.attaque = attaque;
        this.hp = hp;
    }
    public int getX (){return x;}
    public int getVie (){return hp;}
    public int getY (){return y;}
    public void verifier (Heros Adlez, Tuile [][]map){
        if (Adlez.getX()-x==-1||Adlez.getX()-x==0||Adlez.getX()-x==1){
            if (Adlez.getY()-y==-1||Adlez.getY()-y==0||Adlez.getY()-y==1){
                attaquerHeros(Adlez);
            }
            else {
                if (Adlez.getY()-y<=-1){
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
        else {
        if (Adlez.getX()-x<=-1){
            if (map[y][x-1].getVide()){
                partir(map,x,y);
                x-=1;
                marcher(map,x,y);
            }
        }else if (Adlez.getX()-x>=1){
            if (map[y][x+1].getVide()){
                partir(map,x,y);
                x+=1;
                marcher(map,x,y);
            }
        }
        if (Adlez.getY()-y<=-1){
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

    public void marcher(Tuile[][] map, int x, int y) {
        map[y][x].setMonstre();
        map[y][x].setPersonnage(true);
        this.x=x;this.y=y;

        //for (int i = 0; i < map.length; i++) {
           //for (int j = 0; j < map.length; j++) {
               //faire la condition d'avancement
                
        /*attaquerHeros(map,Adlez,coordonner[0],coordonner[1],yAdlez,xAdlez);
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
                }*/
            //}
        //}
    }




    public void partir(Tuile [][]map, int x, int y){
        map[y][x].setPersonnage(false);
        map[y][x].revenir();
    }
    public void deplacer (){

    }
    public void ff (){

    }
    public int getHp() {
        return hp;
    }
    private void attaquerHeros(Heros Adlez){
        Adlez.diminuerVie(attaque);

            /*if (xAdlez+1 == xMonstre && yAdlez == yMonstre || xAdlez-1 == xMonstre && yMonstre == yAdlez || yAdlez-1== yMonstre && xAdlez == xMonstre || yAdlez+1== yMonstre && xAdlez == xMonstre){
            //attaquer adlez
                Adlez.diminuerVie(attaque);
            }
            else if (xAdlez + 1 == xMonstre && yAdlez + 1 == yMonstre || xAdlez - 1 == xMonstre && yAdlez + 1 == yMonstre || xAdlez - 1 == xMonstre && yAdlez - 1 == yMonstre || xAdlez + 1 == xMonstre && yAdlez - 1 == yMonstre){
                Adlez.diminuerVie(attaque);
            }*/



    }
    public void diminuerVie (int combien) {
        hp -= combien;
    }
}
