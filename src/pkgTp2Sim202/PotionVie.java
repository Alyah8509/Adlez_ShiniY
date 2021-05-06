package pkgTp2Sim202;

public class PotionVie extends Item{
    /**
     * Override la méthode utiliser de Item pour appeler additionnerVie dans la classe heros.
     * @param Adlez pour ajouter une vie
     */
    public void utiliser (Heros Adlez){
        Adlez.additionnerVie();
    }

}
