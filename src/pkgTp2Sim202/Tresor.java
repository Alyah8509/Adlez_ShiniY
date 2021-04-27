package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tresor extends Tuile {
    String symbole="$";
    String symbole2="_";
    String act="$";
    boolean vide=false;
    String adlez="&";
    String monstre="@";
    Item [] item;
    boolean ouvert=false;

    public void utiliser (Heros Adlez){
        if (item[0].getClass()==CristalMagique.class){
            Adlez.additionnerCristaux();
        }else if (item[0].getClass()==PotionForce.class){
            Adlez.addForce();
        }else if (item[0].getClass()==PotionVie.class){
            Adlez.additionnerVie();
        }
    }
    public void setItem (int niveau, int x, int y){
        item=new Item[1];
        ArrayList <Character> positionAutres=new ArrayList<>();
        char [] tuiles;char [] convertisseur=new char[2];
        ArrayList <Integer> positionXY=new ArrayList<>();
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(niveau + ".txt"));
            String ligne;
            ligne = fichier.readLine();
            while (ligne!=null){
                String[] tableauTemporaire = ligne.split(",");
                positionAutres.clear();
                tuiles = tableauTemporaire[0].toCharArray();
                for (int i = 0; i < tuiles.length; i++) {
                    positionAutres.add(tuiles[i]);
                }
                if (positionAutres.get(0).equals('t') && positionAutres.get(1).equals('r')){
                    convertisseur[0] = positionAutres.get(positionAutres.size() - 2);
                    if (convertisseur[0] >= 48 && convertisseur[0] <= 57) {
                        String test = Character.toString(convertisseur[0]);
                        convertisseur[0] = positionAutres.get(positionAutres.size()-1);
                        test += Character.toString(convertisseur[0]);
                        positionXY.add(Integer.parseInt(test));
                    } else {
                        convertisseur[0] = positionAutres.get(positionAutres.size()-1);
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
                    if (positionXY.get(0)==x&&positionXY.get(1)==y){
                        positionAutres.clear();
                        tuiles = tableauTemporaire[2].toCharArray();
                        for (int i = 0; i < tuiles.length; i++) {
                            positionAutres.add(tuiles[i]);
                        }
                        switch (positionAutres.get(positionAutres.size()-2)){
                            case 'u': item[0]=new CristalMagique();System.out.println("Cristal reçu");
                            break;
                            case 'i':item[0]=new PotionVie();System.out.println("Potion de vie reçu");
                            break;
                            case 'c': item[0]=new PotionForce();System.out.println("Potion de Force reçu");
                            break;
                        }
                        ouvert=true;
                        act=symbole2;
                        symbole=act;
                    }
                }
                if (ouvert){
                    break;
                }
                positionXY.clear();positionXY.clear();
                ligne=fichier.readLine();
            }fichier.close();
        }catch (Exception e){

        }
    }
    public String getSymbole (){

        return symbole;
    }
    public void setHero (){
        if (vide){
            symbole=adlez;
        }
    }
    public void setMonstre(){
        if (vide){
            symbole=monstre;
        }
    }
    public void revenir (){
        symbole=act;
    }
    public void ouvrir (){
        symbole=symbole2;
    }
    public boolean getVide (){return vide;}

}
