package pkgTp2Sim202;


import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    private Tuile[][] map;
    private boolean quit=false;
    private Heros Adlez = new Heros();
    private Vie actuelle=Vie.VIVANT;
    private ArrayList<Integer> positionJoueur = new ArrayList<>();
    private ArrayList<Integer> donnesMonstres = new ArrayList<>();
    private ArrayList<Monstre> monstres = new ArrayList<>();
    private Messages messages=new Messages();
    private ArrayList <Integer> donnesTp=new ArrayList<>();

    public void jouer(int niveau) {
        Scanner sc = new Scanner(System.in);
        String actions;
        int essais = 0;
        while (!quit) {
            if (essais == 0) {//si c'est la première fois qu'on arrive à ce niveau
                Niveau floor = new Niveau();
                map = floor.lire(niveau);//utilise lire pour avoir la carte
                if (floor.getDonnesPancartes().size()!=0){
                    messages.setMessage(floor.getMessagePancarte());
                    //s'il y a une pancarte, préparer l'affichage
                }
                donnesTp=floor.getDonnesTp();
                positionJoueur = floor.getPositionJoueur();
                Adlez.setPosition(positionJoueur);
                //setPosition s'agit de update la classe Heros pour Adlez, qu'elle est présentemment à cette position
                donnesMonstres = floor.getDonnesMonstres();
                map[positionJoueur.get(1)][positionJoueur.get(0)].setHero();
                //setHero va changer le symbole de cette tuile en & (ceux qui le peuvent)(voir Plancher pour plus
                // de détails)
                if (donnesMonstres.size() != 0) {//s'il y a un monstre
                    int info=0;
                    for (int i = 0; i < donnesMonstres.size(); i += 4) {
                        //stocke les données du monstre dans une liste Monstre
                        monstres.add(new Monstre(donnesMonstres.get(i + 3), donnesMonstres.get(i + 2)));
                        //fait marcher tous les monstres existant dans la liste (un monstre à chaque bond de 4,
                        // expliquant info)
                        monstres.get(info).marcher(map,donnesMonstres.get(i),donnesMonstres.get(i+1));
                        info++;
                    }
                }
                Adlez.afficher(map);
                //Adlez.afficher s'agit d'afficher la carte avec getSymbole qui retourne un String propre à
                // cette tuile (devient & ou @ possiblement)

            } else {
                //si la carte est déjà importée et Adlez a déjà bougé, il suffit de l'afficher
                Adlez.afficher(map);
            }
            System.out.println("Que voulez-vous faire: ");
            actions = sc.nextLine();
            char[] actionEnChar = actions.toCharArray();//transforme le String saisi en char
            for (int i = 0; i < actionEnChar.length; i++) {//et lit les char un par un
                if (actionEnChar[i] == 'w') {//si c'est w,
                    //ne peut pas être découpé en méthode vu que X et Y change selon le mouvement.
                    if (map[Adlez.getY() - 1][Adlez.getX()].getVide()) {//vérifie si la case en haut peut être pilé
                        map[Adlez.getY()][Adlez.getX()].revenir();//la case ou Adlez est présentemment devient vide
                        map[Adlez.getY() - 1][Adlez.getX()].setHero();
                        //et le symbole de la case d'en haut est remplacé par &
                        Adlez.setY(Adlez.getY()-1);//mis à jour des coordonnées de Adlez
                    } else {//si getVide retourne false (mur ou trésor)
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }

                } else if (actionEnChar[i] == 'a') {//même chose, à droite
                    if (map[Adlez.getY()][Adlez.getX() - 1].getVide()) {
                        map[Adlez.getY()][Adlez.getX()].revenir();
                        map[Adlez.getY()][Adlez.getX() - 1].setHero();
                        Adlez.setX(Adlez.getX()-1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }
                } else if (actionEnChar[i] == 's') {//même chose en bas
                    if (map[Adlez.getY() + 1][Adlez.getX()].getVide()) {
                        map[Adlez.getY()][Adlez.getX()].revenir();
                        map[Adlez.getY() + 1][Adlez.getX()].setHero();
                        Adlez.setY(Adlez.getY()+1);
                    } else {
                        System.out.println("Vous ne pouvez pas marcher par dessus!");
                        break;
                    }
                } else if (actionEnChar[i] == 'd') {//même chose à gauche
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
                    toucher(map,Adlez,messages,niveau);//voir la méthode
                    if (niveau==6){//après le 'c', vérifie si le niveau est 6. Si oui, vérifie si Adlez a ouvert
                        // un trésor
                        //avec un cristal dedans. Si oui, quit est true et le jeu finit.
                        if (Adlez.getCristaux()==6){
                            quit=true;
                            break;
                        }
                    }

                }
                else if (actionEnChar[i]=='x'){
                    for (int u=0; u<monstres.size(); u++){//fait un tour dans la liste monstre
                        if (Adlez.getX()-monstres.get(u).getX()==-1||Adlez.getX()-monstres.get(u).getX()==0||
                                Adlez.getX()-monstres.get(u).getX()==1){//si un monstre est au alentours de Adlez
                            if (Adlez.getY()-monstres.get(u).getY()==-1||Adlez.getY()-monstres.get(u).getY()==0||
                                    Adlez.getY()-monstres.get(u).getY()==1){//en y aussi
                                Adlez.attaquer(monstres.get(u));//attaque
                            }
                        }
                        if (monstres.get(u).getVie()<=0){//si le monstre est mort
                            if (Adlez.getX()==monstres.get(u).getX()&&Adlez.getY()==monstres.get(u).getY()){
                                //situation 1: le monstre est sur Adlez
                                map[Adlez.getY()][Adlez.getX()].setPersonnage(false);//même chose que partir ici
                                map[Adlez.getY()][Adlez.getX()].setSymbole("&");
                                //mais si il part, la méthode devenir va directement transformer symbole en act,
                                //donc Adlez disparaît. Donc il remplace le symbole par & manuellement.
                            }
                            else {monstres.get(u).partir(map,monstres.get(u).getX(),monstres.get(u).getY());}
                            //si Adlez n'est pas sur le monstre il peut partir directement
                            monstres.remove(u);//enlevé pour éviter des bugs
                        }
                    }
                }
                else if (actionEnChar[i]=='q'){
                    //si c'est l'option quitter, quit égale true et la boucle dans partie se brise
                    quit=true;
                    break;
                }

                for (int r=0; r<monstres.size();r++){
                    //le tour des monstres (voir la classe Monstre)
                    monstres.get(r).verifier(Adlez,map);
                }
                if (Adlez.getVie()<=0){//si Adlez meurt
                    quit=true;//quitter le jeu
                    actuelle=Vie.MORT;//noter que Adlez est morte
                    break;
                }
            }
            essais++;
            if (Adlez.getCristaux()==6){//s'il y a 6 cristaux
                if (monstres.size()==0){//et plus aucune monstre
                    actuelle=Vie.GAMECLEAR;//noter que Adlez a gagné
                    break;
                }
            }
            else if (Adlez.getCristaux()==niveau){
                //si par contre, Adlez a trouvé le cristal dans le niveau
                if (monstres.size()==0){
                    //et qu'il y a aucun monstre, la boucle se brise et Adlez passe au prochain niveau
                    actuelle=Vie.VIVANT;//noter que Adlez est en vie (passe au niveau suivant)
                    break;
                }
            }
        }

    }

    /**
     * retourne quitter
     * @return
     */
    public boolean getQuit (){
        return quit;
    }

    /**
     * retourne le statut de Adlez
     * @return
     */
    public Vie getActuelle(){
        return actuelle;
    }

    /**
     * Les 3 statuts possibles de Adlez
     */
    public enum Vie {
        MORT,
        VIVANT,
        GAMECLEAR
    }
    public static void toucher (Tuile [][]map, Heros Adlez, Messages messages, int niveau){
        ArrayList<Integer> boite=new ArrayList<>();//liste pour stocker les coordonnées temporairement
        if (map[Adlez.getY()][Adlez.getX()].getClass()!=Mur.class&&map[Adlez.getY()][Adlez.getX()].getClass()!=Plancher.class){
            //du moment que ce n'est pas un mur ou un plancher, on peut interagir avec
            if (map[Adlez.getY()][Adlez.getX()].getClass()==Pancarte.class){
                messages.afficher();}//si c'est pancarte, un message d'affiche
            else if (map[Adlez.getY()][Adlez.getX()].getClass()==Teleporteur.class){//si c'est téléporteur
                boite.add(Adlez.getX());boite.add(Adlez.getY());//stocke les données x y de Adlez actuellement
                Adlez.setY(map[boite.get(1)][boite.get(0)].getTpy());
                //change le Y de Adlez (avec les données du téléporteur ou Adlez était).
                //Mais vu que après setY les coordonnées de Adlez change, utiliser getY et getX ne va pas marcher
                Adlez.setX(map[boite.get(1)][boite.get(0)].getTpx());
                map[Adlez.getY()][Adlez.getX()].setHero();
                //aux coordonnées de la classe téléporteur, afficher &
                map[boite.get(1)][boite.get(0)].revenir();
                //et effacer l'endroit ou Adlez était avant.
                boite.clear();//vide la boite pour la suite.
            }
        }
        else if (map[Adlez.getY()][Adlez.getX()+1].getClass()!=Mur.class&&map[Adlez.getY()][Adlez.getX()+1].
                getClass()!=Plancher.class){
            //même chose, à gauche
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
                //vu que Adlez ne peut pas marcher sur un trésor, il n'est pas au premier if
                map[Adlez.getY()][Adlez.getX()+1].setItem(niveau,Adlez.getX()+1,Adlez.getY());
                //lit le fichier pour voir c'est quoi l'item de cette coordonée.
                map[Adlez.getY()][Adlez.getX()+1].utiliser(Adlez);
                //l'utilise sur Adlez
            }
        }else if (map[Adlez.getY()][Adlez.getX()-1].getClass()!=Mur.class&&map[Adlez.getY()]
                [Adlez.getX()-1].getClass()!=Plancher.class){
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
        }else if (map[Adlez.getY()+1][Adlez.getX()].getClass()!=Mur.class&&map[Adlez.getY()+1][Adlez.getX()]
                .getClass()!=Plancher.class){
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
        }else if (map[Adlez.getY()-1][Adlez.getX()].getClass()!=Mur.class&&map[Adlez.getY()-1][Adlez.getX()]
                .getClass()!=Plancher.class){
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

}

