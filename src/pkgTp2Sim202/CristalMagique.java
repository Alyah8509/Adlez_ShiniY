package pkgTp2Sim202;

public class CristalMagique extends Item{
    /**
     * appelle addtionnerCristaux avec un personnage héros. Voir la classe Heros.
     * Override la méthode utiliser de Item.
     * @param Adlez Pour accéder le nombre de cristal à Adlez
     */
    protected void utiliser (Heros Adlez){
        Adlez.additionnerCristaux();
    }
}
