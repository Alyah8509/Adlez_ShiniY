package pkgTp2Sim202;


import javax.lang.model.type.IntersectionType;
import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    boolean statut = false;
    Vie vieActuelle = Vie.VIVANT;
    Tuile[][] map;
    boolean quit=false;
    Heros Adlez = new Heros();
    ArrayList<Integer> donnesMonstres = new ArrayList<>();
    ArrayList<Monstre> monstres = new ArrayList<>();
    ArrayList<Integer> tp=new ArrayList<>();
    ArrayList<Integer> pancart=new ArrayList<>();

    public void jouer(int niveau) {
        Scanner sc = new Scanner(System.in);
        String actions;
        int essais = 0;
        Messages lire=new Messages();
        while (!statut) {
            if (essais == 0) {
                Niveau floor = new Niveau();
                map = floor.lire(niveau);
                Adlez.setPosition(floor.getPositionJoueur());
                donnesMonstres = floor.getDonnesMonstres();
                pancart=floor.donnesPancartes;
                tp=floor.donnesTp;
                map[Adlez.getY()][Adlez.getX()].setHero();
                if (donnesMonstres.size() != 0) {
                    for (int i = 0; i < donnesMonstres.size(); i += 4) {
                        map[donnesMonstres.get(i + 1)][donnesMonstres.get(i)].setMonstre();
                        monstres.add(new Monstre(donnesMonstres.get(i + 3), donnesMonstres.get(i + 2)));
                    }
                }
                if (tp.size()!=0){
                    for (int i = 0; i < tp.size(); i += 4) {
                        map[tp.get(i + 1)][tp.get(i)].setTp(tp.get(i+2),tp.get(i+3));
                    }
                }
                if (pancart.size()!=0){
                    lire.setMessage(floor.messagePancarte);
                }
                Adlez.afficher(map);
            } else {
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
                    ArrayList<Integer> garder=new ArrayList<>();
                    if (map[Adlez.getY()][Adlez.getX()].getClass()!=Plancher.class&&map[Adlez.getY()][Adlez.getX()].getClass()!=Mur.class){
                        if (map[Adlez.getY()][Adlez.getX()].getClass()==Teleporteur.class){
                            garder.add(Adlez.getX());garder.add(Adlez.getY());
                            Adlez.setY(map[garder.get(1)][garder.get(0)].getTpy());
                            Adlez.setX(map[garder.get(1)][garder.get(0)].getTpx());
                            map[garder.get(1)][garder.get(0)].revenir();
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            garder.clear();}
                        else if (map[Adlez.getY()][Adlez.getX()].getClass()==Pancarte.class){
                            lire.afficher();
                        }else if (map[Adlez.getY()][Adlez.getX()].getClass()==Tresor.class){

                        }
                    } else if (map[Adlez.getY()][Adlez.getX()+1].getClass()!=Plancher.class&&map[Adlez.getY()][Adlez.getX()+1].getClass()!=Mur.class){
                        if (map[Adlez.getY()][Adlez.getX()+1].getClass()==Teleporteur.class){
                            garder.add(Adlez.getX());garder.add(Adlez.getY());
                            Adlez.setY(map[garder.get(1)][garder.get(0)+1].getTpy());
                            Adlez.setX(map[garder.get(1)][garder.get(0)+1].getTpx());
                            map[garder.get(1)][garder.get(0)].revenir();
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            garder.clear();}
                        else if (map[Adlez.getY()][Adlez.getX()+1].getClass()==Pancarte.class){
                            lire.afficher();
                        }else if (map[Adlez.getY()][Adlez.getX()+1].getClass()==Tresor.class){

                        }
                    }else if (map[Adlez.getY()][Adlez.getX()-1].getClass()!=Plancher.class&&map[Adlez.getY()][Adlez.getX()-1].getClass()!=Mur.class){
                        if (map[Adlez.getY()][Adlez.getX()-1].getClass()==Teleporteur.class){
                            garder.add(Adlez.getX());garder.add(Adlez.getY());
                            Adlez.setY(map[garder.get(1)][garder.get(0)-1].getTpy());
                            Adlez.setX(map[garder.get(1)][garder.get(0)-1].getTpx());
                            map[garder.get(1)][garder.get(0)].revenir();
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            garder.clear();}else if (map[Adlez.getY()][Adlez.getX()-1].getClass()==Pancarte.class){
                            lire.afficher();
                        }else if (map[Adlez.getY()][Adlez.getX()-1].getClass()==Tresor.class){

                        }
                    }else if (map[Adlez.getY()+1][Adlez.getX()].getClass()!=Plancher.class&&map[Adlez.getY()+1][Adlez.getX()].getClass()!=Mur.class){
                        if (map[Adlez.getY()+1][Adlez.getX()].getClass()==Teleporteur.class){
                            garder.add(Adlez.getX());garder.add(Adlez.getY());
                            Adlez.setY(map[garder.get(1)+1][garder.get(0)].getTpy());
                            Adlez.setX(map[garder.get(1)+1][garder.get(0)].getTpx());
                            map[garder.get(1)][garder.get(0)].revenir();
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            garder.clear();}else if (map[Adlez.getY()+1][Adlez.getX()].getClass()==Pancarte.class){
                            lire.afficher();
                        }else if (map[Adlez.getY()+1][Adlez.getX()].getClass()==Tresor.class){

                        }

                    }else if (map[Adlez.getY()-1][Adlez.getX()].getClass()!=Plancher.class&&map[Adlez.getY()-1][Adlez.getX()].getClass()!=Mur.class){
                        if (map[Adlez.getY()-1][Adlez.getX()].getClass()==Teleporteur.class){
                        garder.add(Adlez.getX());garder.add(Adlez.getY());
                            Adlez.setY(map[garder.get(1)-1][garder.get(0)].getTpy());
                            Adlez.setX(map[garder.get(1)-1][garder.get(0)].getTpx());
                            map[garder.get(1)][garder.get(0)].revenir();
                            map[Adlez.getY()][Adlez.getX()].setHero();
                            garder.clear();}
                        else if (map[Adlez.getY()-1][Adlez.getX()].getClass()==Pancarte.class){
                            lire.afficher();
                        }else if ( map[Adlez.getY()-1][Adlez.getX()].getClass()==Tresor.class){

                        }
                    }
                }
                else if (actionEnChar[i]=='x'){

                }
                else if (actionEnChar[i]=='q'){
                    quit=true;
                    break;
                }

            /*


                else if (actionEnChar[i]=='q'){
                    break;
                }
            }if (actionEnChar[actionEnChar.length-1]=='q'){
                break;
            }
            essais++;
        }
    }



    */
            }essais++;
            if (quit){
                break;
            }
        }

    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
    public boolean getQuit (){
        return quit;
    }
}
