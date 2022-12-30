package pkgTp2Sim202;

import java.util.ArrayList;

public class Messages {
    private ArrayList <String> message=new ArrayList<>();

    /**
     * Demande une liste String qui est le message à afficher
     * @param message
     */
    protected void setMessage (ArrayList <String> message){
        this.message=message;
    }

    /**
     * L'affichage du message
     */
    protected void afficher (){
        System.out.print("Le message:");
        for (int i=0; i<message.size(); i++){
            if (i==message.size()-1){//s'il y a juste une phrase sans virgule
                System.out.print(message.get(i));
            }else {//sinon, il y a un ajout de virgule après
           System.out.print(message.get(i)+", ");
            }
        }System.out.println();//change de ligne
    }

    /**
     * @return le message (pour la sauvegarde)
     */
    protected ArrayList <String>getMessage(){return message;}
}
