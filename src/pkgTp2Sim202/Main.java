package pkgTp2Sim202;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        afficherIntro();//afficher l'intro
        boolean sauve=false;//Il n'y a pas de sauvegarde au début
        int niveau=0;//initialise niveau
        Partie jeu = new Partie();//creer une nouvelle partie
        boolean fini=false;//la partie n'est pas fini
        System.out.println("charger la dernière partie?");
        System.out.println("1-Oui");
        System.out.println("2-Non");
        String choix=sc.nextLine();
        if (choix.equals("1")){
            sauve=true;//Pour la lecture des fichiers dans Partie
            try {
                FileInputStream fos=new FileInputStream("partie.sav");
                ObjectInputStream ois=new ObjectInputStream(fos);
                ArrayList <Integer> ouvre=(ArrayList<Integer>)ois.readObject();
                jeu.setOuvre(ouvre);//répète de processus de lire et de remplacer
                niveau=(int)ois.readObject();
                Heros adlez=(Heros)ois.readObject();
                jeu.setAdlez(adlez);
                if (!(boolean)ois.readObject()){//Si il n'y a aucun monstre

                }else {
                ArrayList<Monstre> monstres=(ArrayList<Monstre>)ois.readObject();
                jeu.setMonstres(monstres);}
                if (!(boolean)ois.readObject()){

                }else {
                    ArrayList <String> message=(ArrayList<String>) ois.readObject();
                    jeu.setMessages(message);
                }
                ois.close();//ferme

            }catch (Exception e){
                System.out.println("Erreur!Vérifiez si vous avez bien sauvegardé");
                fini=true;
            }
        }
        if (sauve){//le niveau serait déjà décidé si c'est une partie sauvegardé

        }else {
            niveau = 1;//sinon, il commence au niveau 1
        }
        while (!fini){//tant que ce n'est pas fini
        jeu.jouer(niveau,sauve);//jouer au niveau decide
        fini=jeu.getQuit();//si le joueur a choisi quit, le boolean quit serait true. Donc fini=true
        niveau++;//passe au niveau prochain
            sauve=false;//ce n'est plus un niveau sauvegardé peu importe (si Adlez passe au prochain niveau)
        if (jeu.getActuelle()== Partie.Vie.GAMECLEAR){//si la partie est clear
            afficherVictoire();
            fini=true;
        }else if (jeu.getActuelle()== Partie.Vie.MORT){
            afficherDefaite();
        }
        //si quit est false et cristaux est plus petit que 6, il passe au prochain niveau
        }
    }

        /**
         * Affiche des messages pré-déterminés
         * Mis en static pour l'utiliser en méthode
         */

        private static void afficherIntro(){
            System.out.println(
                    "                        /=\\\n" +
                            "                        |||\n" +
                            "                        |||\n" +
                            "                       =====\n" +
                            " _   _              //=======\\\\\n" +
                            "| |_| |__   ___    /// |   | \\\\\\\n" +
                            "| __| '_ \\ / _ \\  ///  |   |  \\\\\\\n" +
                            "| |_| | | |  ___ ///   |   |   \\\\\\            _\n" +
                            " \\__|_| |_|\\__| |    __|  _| _  ___ _ __   __| |\n" +
                            "              | |   / _ \\/ _` |/ _ \\ '_ \\ / _` |\n" +
                            "              | |__|  __/ (_| |  __/ | | | (_| |\n" +
                            "              |_____\\___|\\__, |\\___|_| |_|\\__,_|\n" +
                            "                       | |___/\n" +
                            "                      _|   |\n" +
                            "                ___  / _|  |   :##:    #####:    ##        ########  ########\n" +
                            "               / _ \\| |_   |    ##     #######   ##        ########  ########\n" +
                            "              | (_) |  _|  |   ####    ##  :##:  ##        ##            .###\n" +
                            "               \\___/|_||   |   ####    ##   :##  ##        ##            ###\n" +
                            "                       |   |  :#  #:   ##   .##  ##        ##           :##:\n" +
                            "                       |   |   #::#    ##    ##  ##        #######      ###\n" +
                            "                       |   |  ##  ##   ##    ##  ##        #######     ###\n" +
                            "                       |   |  ######   ##   .##  ##        ##         :##:\n" +
                            "                       |   | .######.  ##   :##  ##        ##         ##\n" +
                            "                       |   | :##  ##:  ##  :##:  ##        ##        ###\n" +
                            "                       |   | ###  ###  #######   ########  ########  ########\n" +
                            "                       |   | ##:  :##  #####:    ########  ########  ########\n" +
                            "                       \\   /\n" +
                            "                   Ocarina of Time.sleep(1000);\n" +
                            "                         V\n");
            System.out.println("Appuyez sur Enter pour commencer");
            Scanner s = new Scanner(System.in);
            s.nextLine();
        }

    /**
     * Affiche la victoire
     */
    private static void afficherVictoire () {
            System.out.println("          Félicitations! Vous avez tous les cristeaux magiques!");
            System.out.println("                        Le monde est sauvé!");
            System.out.println("                           Δ");
            System.out.println("                          Δ Δ");
            System.out.println("                         Δ Δ Δ");
            System.out.println("                          \\o/");
            System.out.println("                           |");
            System.out.println("                          / \\");
        }

    /**
     * Affiche la défaite
     */
    private static void afficherDefaite () {
            System.out.println("          Nooooon! Adlez est morte avant d'avoir collecté les");
            System.out.println("                       cristeaux magiques...");
            System.out.println();
            System.out.println("              Meilleure chance la prochaine fois!");
            System.out.println("                           o ");
            System.out.println("                          /|\\");
            System.out.println("                          / \\");
        }
    }
