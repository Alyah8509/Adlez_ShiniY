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
            if (essais == 0) {
                Niveau floor = new Niveau();
                map = floor.lire(niveau);
                if (floor.donnesPancartes.size()!=0){
                    messages.setMessage(floor.messagePancarte);
                }
                donnesTp=floor.donnesTp;
                if (donnesTp.size()!=0){
                    for (int i=0; i<donnesTp.size();i+=4){
                        map[donnesTp.get(i+1)][donnesTp.get(i)].setTp(donnesTp.get(i+2),donnesTp.get(i+3));
                    }
                }
                positionJoueur = floor.getPositionJoueur();
                Adlez.setPosition(positionJoueur);
                donnesMonstres = floor.getDonnesMonstres();
                map[positionJoueur.get(1)][positionJoueur.get(0)].setHero();
                if (donnesMonstres.size() != 0) {
                    int info=0;
                    for (int i = 0; i < donnesMonstres.size(); i += 4) {
                        monstres.add(new Monstre(donnesMonstres.get(i + 3), donnesMonstres.get(i + 2)));
                        monstres.get(info).marcher(map,donnesMonstres.get(i),donnesMonstres.get(i+1));
                        info++;
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
                            Item[] item=map[Adlez.getY()][Adlez.getX()+1].getItem();
                            if (item[0].getClass()==CristalMagique.class){
                                Adlez.additionnerCristaux();
                            }else if (item[0].getClass()==PotionForce.class){
                                Adlez.addForce();
                            }else if (item[0].getClass()==PotionVie.class){
                                Adlez.additionnerVie();
                            }
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
                            Item[] item=map[Adlez.getY()][Adlez.getX()-1].getItem();
                            if (item[0].getClass()==CristalMagique.class){
                                Adlez.additionnerCristaux();
                            }else if (item[0].getClass()==PotionForce.class){
                                Adlez.addForce();
                            }else if (item[0].getClass()==PotionVie.class){
                                Adlez.additionnerVie();
                            }
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
                            Item[] item=map[Adlez.getY()+1][Adlez.getX()].getItem();
                            if (item[0].getClass()==CristalMagique.class){
                                Adlez.additionnerCristaux();
                            }else if (item[0].getClass()==PotionForce.class){
                                Adlez.addForce();
                            }else if (item[0].getClass()==PotionVie.class){
                                Adlez.additionnerVie();
                            }
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
                            Item[] item=map[Adlez.getY()-1][Adlez.getX()].getItem();
                            if (item[0].getClass()==CristalMagique.class){
                                Adlez.additionnerCristaux();
                            }else if (item[0].getClass()==PotionForce.class){
                                Adlez.addForce();
                            }else if (item[0].getClass()==PotionVie.class){
                                Adlez.additionnerVie();
                            }
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
