package pkgTp2Sim202;

public class Heros {
    int vie=6;
    int force=1;
    int cristaux=0;
    public int getVie (){
        return vie;
    }
    public int getForce(){
        return force;
    }
    public int getCristaux(){
        return cristaux;
    }
    public void additionnerVie(int combien){
        vie+=combien;
        if (vie>6){
            vie=6;
        }
    }
    public boolean diminuerVie (int combien){
        vie-=combien;
        if (vie<=0){
            return false;
        }else {return true;}
    }
    public void additionnerCristaux (){
        cristaux++;
    }
}
