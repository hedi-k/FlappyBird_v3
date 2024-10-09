package Model;

/**
 * Methode Static pour essayer différement.
 * @author hedi
 */
public class Collision {
    
     //Controle collision return true si contact.
    public static boolean collision(Bird a, Pipe b) {
        return a.getX() < b.getX() + b.getWidth()
                && //a's top left corner doesn't reach b's top right corner
                a.getX() + a.getWidth() > b.getX()
                && //a's top right corner passes b's top left corner
                a.getY() < b.getY() + b.getHeight()
                && //a's top left corner doesn't reach b's bottom left corner
                a.getY() + a.getHeight() > b.getY();    //a's bottom left corner passes b's top left corner
    }
    
}
