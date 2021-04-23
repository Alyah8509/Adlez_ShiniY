package pkgTp2Sim202;

import java.util.ArrayList;

public class Messages {
    public void afficher (ArrayList<String> message){
        for (int i=0; i<message.size(); i++){
            if (i==message.size()-1){
                System.out.print(message.get(i));
            }else {
           System.out.print(message.get(i)+", ");
            }
        }
    }
}
