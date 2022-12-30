package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Tresor extends Tuile implements Serializable {
    private String symbole="$";
    private String symbole2="_";
    private String act="$";
    private boolean vide=false;
    private Item [] item;
    private boolean ouvert=false;

    /**
     * voir à la classe item (et ses sous-classes). Mais en bref il utilise l'item
     * @param Adlez la personne qui l'utilise
     */
    protected void utiliser (Heros Adlez){
        item[0].utiliser(Adlez);

    }

    /**
     * Décide de l'item. Lit le fichier (environ la même méthode que dans niveau)
     * regarde si les coordonnées x et y correspondent à celui du fichier.
     * S'il correspond, il lit un charactère pour décider ce que l'item est
     * puis il le stocke dans un tableau item
     * @param niveau le chiffre du fichier
     * @param x coordonnées
     * @param y
     */
    protected void setItem (int niveau, int x, int y){
        if (!ouvert) {
            item = new Item[1];
            ArrayList<Character> positionAutres = new ArrayList<>();
            char[] tuiles;
            char[] convertisseur = new char[2];
            ArrayList<Integer> positionXY = new ArrayList<>();
            try {
                BufferedReader fichier = new BufferedReader(new FileReader(niveau + ".txt"));
                String ligne;
                ligne = fichier.readLine();
                while (ligne != null) {
                    String[] tableauTemporaire = ligne.split(",");
                    positionAutres.clear();
                    tuiles = tableauTemporaire[0].toCharArray();
                    for (int i = 0; i < tuiles.length; i++) {
                        positionAutres.add(tuiles[i]);
                    }
                    if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('r')) {
                        convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                        if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                            String test = Character.toString(convertisseur[0]);
                            convertisseur[0] = positionAutres.get(positionAutres.size() - 1);
                            test += Character.toString(convertisseur[0]);
                            positionXY.add(Integer.parseInt(test));
                        } else {
                            convertisseur[0] = positionAutres.get(positionAutres.size() - 1);
                            positionXY.add(Character.getNumericValue(convertisseur[0]));
                        }
                        positionAutres.clear();
                        tuiles = tableauTemporaire[1].toCharArray();
                        for (int i = 0; i < tuiles.length; i++) {
                            positionAutres.add(tuiles[i]);
                        }
                        if (positionAutres.size() >= 2) {
                            convertisseur[0] = positionAutres.get(0);
                            String test = Character.toString(convertisseur[0]);
                            convertisseur[0] = positionAutres.get(1);
                            test += Character.toString(convertisseur[0]);
                            positionXY.add(Integer.parseInt(test));
                        } else {
                            convertisseur[0] = positionAutres.get(0);
                            positionXY.add(Character.getNumericValue(convertisseur[0]));
                        }
                        if (positionXY.get(0) == x && positionXY.get(1) == y) {
                            //si c'est la bonne ligne (x et y correspondent)
                            positionAutres.clear();
                            tuiles = tableauTemporaire[2].toCharArray();
                            //passe le dernier mot en charactère
                            for (int i = 0; i < tuiles.length; i++) {
                                positionAutres.add(tuiles[i]);
                            }
                            switch (positionAutres.get(positionAutres.size() - 2)) {
                                case 'u':
                                    item[0] = new CristalMagique();
                                    System.out.println("Cristal reçu");
                                    break;
                                case 'i':
                                    item[0] = new PotionVie();
                                    System.out.println("Potion de vie reçu");
                                    break;
                                case 'c':
                                    item[0] = new PotionForce();
                                    System.out.println("Potion de Force reçu");
                                    break;
                            }
                            //donc le trésor est ouvert
                            ouvert = true;
                            //et le symbole va de $ à _: donc je change act directement vu que $ ne va plus être utilisé
                            act = symbole2;
                            //puis je remplace symbole par act
                            symbole = act;
                        }
                    }
                    if (ouvert) {
                        //si c'est ouvert, il n'a plus besoin de lire la suite.
                        break;
                    }
                    positionXY.clear();//si ce n'était pas la bonne ligne, vider la liste et recommencer
                    ligne = fichier.readLine();
                }
                fichier.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     * retourne le symbole actuelle
     * @return
     */
    protected String getSymbole (){

        return symbole;
    }

    /**
     * @return si on peut marcher dessus
     */
    protected boolean getVide (){return vide;}

    /**
     * Dans le cas d'une sauvegarde, le trésor peut être déjà ouvert
     */
    protected void setAct (){
        ouvert=true;
        act=symbole2;
        symbole=act;
    }

}
