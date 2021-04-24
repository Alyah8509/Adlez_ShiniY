package pkgTp2Sim202;


import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    boolean statut = false;
    Vie vieActuelle = Vie.VIVANT;
    Tuile[][] map;
    Heros Adlez = new Heros();
    ArrayList<Integer> positionJoueur = new ArrayList<>();
    ArrayList<Integer> donnesMonstres = new ArrayList<>();
    ArrayList<Monstre> monstres = new ArrayList<>();

    public void jouer(int niveau) {
        Scanner sc = new Scanner(System.in);
        String actions;
        int essais = 0;
        while (!statut) {
            if (essais == 0) {
                Niveau floor = new Niveau();
                positionJoueur = floor.getPositionJoueur();
                donnesMonstres = floor.getDonnesMonstres();
                System.out.println("Vies: " + Adlez.getVie() + "/6        Force: " + Adlez.getForce() + "            Cristaux: " + Adlez.getCristaux());
                map = floor.lire(niveau);
                map[positionJoueur.get(1)][positionJoueur.get(0)].setHero();
                if (donnesMonstres.size() != 0) {
                    for (int i = 0; i < donnesMonstres.size(); i += 4) {
                        map[donnesMonstres.get(i + 1)][donnesMonstres.get(i)].setMonstre();
                        monstres.add(new Monstre(donnesMonstres.get(i + 3), donnesMonstres.get(i + 2)));
                    }
                }
                for (int i = 0; i < map.length; i++) {
                    for (int y = 0; y < map[0].length; y++) {
                        System.out.print(map[i][y].getSymbole());
                    }
                    System.out.println();
                }
            } else {
                afficher();
            }
            System.out.println("Que voulez-vous faire: ");
            actions = sc.nextLine();
            char[] actionEnChar = actions.toCharArray();
            for (int i = 0; i < actionEnChar.length; i++) {
                if (actionEnChar[i] == 'w') {
                    if (map[positionJoueur.get(1) - 1][positionJoueur.get(0)].getVide()) {
                        map[positionJoueur.get(1)][positionJoueur.get(0)].revenir();
                        map[positionJoueur.get(1) - 1][positionJoueur.get(0)].setHero();
                        positionJoueur.set(1, positionJoueur.get(1) - 1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }

                } else if (actionEnChar[i] == 'a') {
                    if (map[positionJoueur.get(1)][positionJoueur.get(0) - 1].getVide()) {
                        map[positionJoueur.get(1)][positionJoueur.get(0)].revenir();
                        map[positionJoueur.get(1)][positionJoueur.get(0) - 1].setHero();
                        positionJoueur.set(0, positionJoueur.get(0) - 1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }
                } else if (actionEnChar[i] == 's') {
                    if (map[positionJoueur.get(1) + 1][positionJoueur.get(0)].getVide()) {
                        map[positionJoueur.get(1)][positionJoueur.get(0)].revenir();
                        map[positionJoueur.get(1) + 1][positionJoueur.get(0)].setHero();
                        positionJoueur.set(1, positionJoueur.get(1) + 1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }
                } else if (actionEnChar[i] == 'd') {
                    if (map[positionJoueur.get(1)][positionJoueur.get(0) + 1].getVide()) {
                        map[positionJoueur.get(1)][positionJoueur.get(0)].revenir();
                        map[positionJoueur.get(1)][positionJoueur.get(0) + 1].setHero();
                        positionJoueur.set(0, positionJoueur.get(0) + 1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }

                }
                else if (actionEnChar[i]=='c'){
                    if (map[Adlez.getY()][Adlez.getX()].getClass()==Pancarte.class){

                    }
                }
                else if (actionEnChar[i]=='x'){

                }
                else if (actionEnChar[i]=='q'){
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
        }

    }
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
    private void afficher () {
        System.out.println("Vies: " + Adlez.getVie() + "/6        Force: " + Adlez.getForce() + "            Cristaux: " + Adlez.getCristaux());
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[0].length; y++) {
                System.out.print(map[i][y].getSymbole());
            }
            System.out.println();
        }
    }
}
