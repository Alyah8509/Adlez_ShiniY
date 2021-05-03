package pkgTp2Sim202;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int niveau = 1;//commence au niveau 1
        boolean fini=false;//la partie n'est pas fini
        afficherIntro();//afficher l'intro
        Partie jeu = new Partie();//creer une nouvelle partie
        while (!fini){//tant que ce n'est pas fini
        jeu.jouer(niveau);//jouer au niveau decide
        fini=jeu.getQuit();//si le joueur a choisi quit, le boolean quit serait true. donc fini=true
        niveau++;//passe au niveau prochain
        if (jeu.getCristaux()==6){//s'il y a 6 cristaux
            afficherVictoire();
            fini=true;
        }
        //si quit est false et cristaux est plus petit que 6, il passe au prochain niveau
        }
    }

        /**
         * Affiche des messages pré-déterminés
         */

        public static void afficherIntro(){
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

        public static void afficherVictoire () {
            System.out.println("          Félicitations! Vous avez tous les cristeaux magiques!");
            System.out.println("                        Le monde est sauvé!");
            System.out.println("                           Δ");
            System.out.println("                          Δ Δ");
            System.out.println("                         Δ Δ Δ");
            System.out.println("                          \\o/");
            System.out.println("                           |");
            System.out.println("                          / \\");
        }

        public void afficherDefaite () {
            System.out.println("          Nooooon! Adlez est morte avant d'avoir collecté les");
            System.out.println("                       cristeaux magiques...");
            System.out.println();
            System.out.println("              Meilleure chance la prochaine fois!");
            System.out.println("                           o ");
            System.out.println("                          /|\\");
            System.out.println("                          / \\");
        }
    }
