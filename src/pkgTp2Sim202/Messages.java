package pkgTp2Sim202;

import java.util.ArrayList;

public class Messages {
    private ArrayList <String> message=new ArrayList<>();
    public void setMessage (ArrayList <String> message){
        this.message=message;
    }
    public void afficher (){
        System.out.print("Le message:");
        for (int i=0; i<message.size(); i++){
            if (i==message.size()-1){
                System.out.print(message.get(i));
            }else {
           System.out.print(message.get(i)+", ");
            }
        }System.out.println();
    }
}
