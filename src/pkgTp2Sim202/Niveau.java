package pkgTp2Sim202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {
    public void lire(int niveau){
        int positionJoueur []=new int[2];
        char positionAutres []=new char[30];
        int positionMonstre []=new int[2];
        char carte [][];
        try {
            BufferedReader fichier=new BufferedReader(new FileReader(niveau+".txt"));
            String ligne;
            ligne=fichier.readLine();
            int ligneLu=0;
            while (ligne!=null){
                if (ligneLu==0){
                   for (int i=0; i<positionJoueur.length; i++){
                       String [] positionString =ligne.split(",");
                       positionJoueur[i]=Integer.parseInt(positionString[i]);
                   }
                }else if (ligneLu==1){
                    String [] tableauTemporaire=ligne.split(",");
                    positionAutres=tableauTemporaire[0].toCharArray();
                    for (int i=0; i<positionAutres.length; i++){
                        if (positionAutres[i]==':'){
                            if (positionAutres[0]=='m'){
                                positionMonstre[0]=Character.getNumericValue(positionAutres[i+1]);
                                positionAutres=tableauTemporaire[1].toCharArray();
                                positionMonstre[1]=Character.getNumericValue(positionAutres[0]);
                            }
                        }
                        else if (positionAutres[i]=='#'){

                        }
                    }

                }
                ligneLu++;
                ligne=fichier.readLine();
            }
        }catch (Exception e){

        }

    }

}
