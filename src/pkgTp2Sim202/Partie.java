package pkgTp2Sim202;


import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    boolean statut = false;
    Vie vieActuelle = Vie.VIVANT;
    Tuile[][] map;
    boolean quit=false;
    Heros Adlez = new Heros();
    ArrayList<Integer> positionJoueur = new ArrayList<>();
    ArrayList<Integer> donnesMonstres = new ArrayList<>();
    ArrayList<Monstre> monstres = new ArrayList<>();
    Messages messages=new Messages();
    ArrayList <Integer> donnesTp=new ArrayList<>();

    public void jouer(int niveau) {
        Scanner sc = new Scanner(System.in);
        String actions;
        int essais = 0;
        while (!statut) {
            if (essais == 0) {//si c'est la première fois qu'on arrive à ce niveau
                Niveau floor = new Niveau();
                map = floor.lire(niveau);//utilise lire pour avoir la carte
                if (floor.donnesPancartes.size()!=0){
                    messages.setMessage(floor.messagePancarte);
                    //s'il y a une pancarte, préparer l'affichage
                }
                donnesTp=floor.donnesTp;
                //nouveau liste int qui est les coordonnées du téléporteur
                if (donnesTp.size()!=0){//s'il y en a un
                    for (int i=0; i<donnesTp.size();i+=4){
                        map[donnesTp.get(i+1)][donnesTp.get(i)].setTp(donnesTp.get(i+2),donnesTp.get(i+3));
                        //map [coordonnée tp en y][coordonnée tp en x].set la destination
                        //les coordonnées sont tous dans le arraylist (x,y,x destination, y destination, x du deuxième, y du deuxième, etc..)
                    }
                }
                positionJoueur = floor.getPositionJoueur();
                Adlez.setPosition(positionJoueur);
                //setPosition s'agit de update la classe Heros pour Adlez, qu'elle est présentemment à cette position
                donnesMonstres = floor.getDonnesMonstres();
                map[positionJoueur.get(1)][positionJoueur.get(0)].setHero();
                //setHero va changer le symbole de cette tuile en & (ceux qui le peuvent)(voir Plancher pour plus de détails)
                if (donnesMonstres.size() != 0) {
                    int info=0;
                    for (int i = 0; i < donnesMonstres.size(); i += 4) {
                        monstres.add(new Monstre(donnesMonstres.get(i + 3), donnesMonstres.get(i + 2)));
                        monstres.get(info).marcher(map,donnesMonstres.get(i),donnesMonstres.get(i+1));
                        info++;
                    }
                }
                Adlez.afficher(map);
                //Adlez.afficher s'agit d'afficher la carte avec getSymbole qui retourne un String propre à cette tuile (devient & ou @ possiblement)

            } else {
                //si la carte est déjà importée et Adlez a déjà bougé, il suffit de l'afficher
                Adlez.afficher(map);
            }
            System.out.println("Que voulez-vous faire: ");
            actions = sc.nextLine();
            char[] actionEnChar = actions.toCharArray();
            for (int i = 0; i < actionEnChar.length; i++) {
                if (actionEnChar[i] == 'w') {
                    if (map[Adlez.getY() - 1][Adlez.getX()].getVide()) {
                        map[Adlez.getY()][Adlez.getX()].revenir();
                        map[Adlez.getY() - 1][Adlez.getX()].setHero();
                        Adlez.setY(Adlez.getY()-1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }

                } else if (actionEnChar[i] == 'a') {
                    if (map[Adlez.getY()][Adlez.getX() - 1].getVide()) {
                        map[Adlez.getY()][Adlez.getX()].revenir();
                        map[Adlez.getY()][Adlez.getX() - 1].setHero();
                        Adlez.setX(Adlez.getX()-1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }
                } else if (actionEnChar[i] == 's') {
                    if (map[Adlez.getY() + 1][Adlez.getX()].getVide()) {
                        map[Adlez.getY()][Adlez.getX()].revenir();
                        map[Adlez.getY() + 1][Adlez.getX()].setHero();
                        Adlez.setY(Adlez.getY()+1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }
                } else if (actionEnChar[i] == 'd') {
                    if (map[Adlez.getY()][Adlez.getX() + 1].getVide()) {
                        map[Adlez.getY()][Adlez.getX()].revenir();
                        map[Adlez.getY()][Adlez.getX() + 1].setHero();
                        Adlez.setX(Adlez.getX()+1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }

                }
                else if (actionEnChar[i]=='c'){
                    ArrayList<Integer> boite=new ArrayList<>();
                    if (map[Adlez.getY()][Adlez.getX()].getClass()!=Mur.class&&map[Adlez.getY()][Adlez.getX()].getClass()!=Plancher.class){
                        if (map[Adlez.getY()][Adlez.getX()].getClass()==Pancarte.class){
                        messages.afficher();}
                        else if (map[Adlez.getY()][Adlez.getX()].getClass()==Teleporteur.class){
                            boite.add(Adlez.getX());boite.add(Adlez.getY());
                            Adlez.setY(map[boite.get(1)][boite.get(0)].getTpy());
                            Adlez.setX(map[boite.get(1)][boite.get(0)].getTpx());
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            map[boite.get(1)][boite.get(0)].revenir();
                            boite.clear();
                        }
                    }
                    else if (map[Adlez.getY()][Adlez.getX()+1].getClass()!=Mur.class&&map[Adlez.getY()][Adlez.getX()+1].getClass()!=Plancher.class){
                        if (map[Adlez.getY()][Adlez.getX()+1].getClass()==Pancarte.class){
                            messages.afficher();}
                        else if (map[Adlez.getY()][Adlez.getX()+1].getClass()==Teleporteur.class){
                            boite.add(Adlez.getX());boite.add(Adlez.getY());
                            Adlez.setY(map[boite.get(1)][boite.get(0)+1].getTpy());
                            Adlez.setX(map[boite.get(1)][boite.get(0)+1].getTpx());
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            map[boite.get(1)][boite.get(0)].revenir();
                            boite.clear();

                        }else if (map[Adlez.getY()][Adlez.getX()+1].getClass()==Tresor.class){
                            map[Adlez.getY()][Adlez.getX()+1].setItem(niveau,Adlez.getX()+1,Adlez.getY());
                            map[Adlez.getY()][Adlez.getX()+1].utiliser(Adlez);
                        }
                    }else if (map[Adlez.getY()][Adlez.getX()-1].getClass()!=Mur.class&&map[Adlez.getY()][Adlez.getX()-1].getClass()!=Plancher.class){
                        if (map[Adlez.getY()][Adlez.getX()-1].getClass()==Pancarte.class){
                            messages.afficher();}
                        else if (map[Adlez.getY()][Adlez.getX()-1].getClass()==Teleporteur.class){
                            boite.add(Adlez.getX());boite.add(Adlez.getY());
                            Adlez.setY(map[boite.get(1)][boite.get(0)-1].getTpy());
                            Adlez.setX(map[boite.get(1)][boite.get(0)-1].getTpx());
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            map[boite.get(1)][boite.get(0)].revenir();
                            boite.clear();
                        }else if (map[Adlez.getY()][Adlez.getX()-1].getClass()==Tresor.class){
                            map[Adlez.getY()][Adlez.getX()-1].setItem(niveau,Adlez.getX()-1,Adlez.getY());
                            map[Adlez.getY()][Adlez.getX()-1].utiliser(Adlez);
                        }
                    }else if (map[Adlez.getY()+1][Adlez.getX()].getClass()!=Mur.class&&map[Adlez.getY()+1][Adlez.getX()].getClass()!=Plancher.class){
                        if (map[Adlez.getY()+1][Adlez.getX()].getClass()==Pancarte.class){
                            messages.afficher();}
                        else if (map[Adlez.getY()+1][Adlez.getX()].getClass()==Teleporteur.class){
                            boite.add(Adlez.getX());boite.add(Adlez.getY());
                            Adlez.setY(map[boite.get(1)+1][boite.get(0)].getTpy());
                            Adlez.setX(map[boite.get(1)+1][boite.get(0)].getTpx());
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            map[boite.get(1)][boite.get(0)].revenir();
                            boite.clear();
                        }else if (map[Adlez.getY()+1][Adlez.getX()].getClass()==Tresor.class){
                            map[Adlez.getY()+1][Adlez.getX()].setItem(niveau,Adlez.getX(),Adlez.getY()+1);
                            map[Adlez.getY()+1][Adlez.getX()].utiliser(Adlez);
                        }
                    }else if (map[Adlez.getY()-1][Adlez.getX()].getClass()!=Mur.class&&map[Adlez.getY()-1][Adlez.getX()].getClass()!=Plancher.class){
                        if (map[Adlez.getY()-1][Adlez.getX()].getClass()==Pancarte.class){
                            messages.afficher();}
                        else if (map[Adlez.getY()-1][Adlez.getX()].getClass()==Teleporteur.class){
                            boite.add(Adlez.getX());boite.add(Adlez.getY());
                            Adlez.setY(map[boite.get(1)-1][boite.get(0)].getTpy());
                            Adlez.setX(map[boite.get(1)-1][boite.get(0)].getTpx());
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            map[boite.get(1)][boite.get(0)].revenir();
                            boite.clear();
                        }else if (map[Adlez.getY()-1][Adlez.getX()].getClass()==Tresor.class){
                            map[Adlez.getY()-1][Adlez.getX()].setItem(niveau,Adlez.getX(),Adlez.getY()-1);
                            map[Adlez.getY()-1][Adlez.getX()].utiliser(Adlez);
                        }
                    }

                }
                else if (actionEnChar[i]=='x'){

                }
                else if (actionEnChar[i]=='q'){
                    quit=true;
                    break;
                }

            }
            if (quit){
                break;
            }
            essais++;
            if (Adlez.getCristaux()==niveau){
                if (donnesMonstres.size()==0){
                    break;
                }else {
                    int vieMonstre=0;
                    for (int i=0; i<monstres.size(); i++){
                        if (monstres.get(i).hp!=0){
                            vieMonstre++;
                        }
                    }
                    if (vieMonstre==0){
                        break;
                    }
                }
            }
        }

    }
    public boolean getQuit (){
        return quit;
    }
    public int getCristaux(){
        return Adlez.getCristaux();
    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }

}
