package pkgTp2Sim202;


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

    public void jouer(int niveau) {
        Scanner sc = new Scanner(System.in);
        String actions;
        int essais = 0;
        while (!statut) {
            if (essais == 0) {
                Niveau floor = new Niveau();
                Adlez.setPosition(floor.getPositionJoueur());
                donnesMonstres = floor.getDonnesMonstres();
                map = floor.lire(niveau);
                map[Adlez.getY()][Adlez.getX()].setHero();
                if (donnesMonstres != null) {
                    for (int i = 0; i < donnesMonstres.size(); i += 4) {
                        map[donnesMonstres.get(i + 1)][donnesMonstres.get(i)].setMonstre();
                        monstres.add(new Monstre(donnesMonstres.get(i + 3), donnesMonstres.get(i + 2)));
                    }
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
