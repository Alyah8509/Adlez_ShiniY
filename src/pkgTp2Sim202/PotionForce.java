package pkgTp2Sim202;

public class PotionForce extends Item{
    /**
     * nécessite un héros, et override la méthode utiliser avec addForce dans Heros.
     * @param Adlez
     */
    public void utiliser (Heros Adlez){
        Adlez.addForce();
    }
}
