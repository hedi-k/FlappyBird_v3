package Model;

import java.awt.Image;

/**
 * Model (tuyau) Classe qui contient les caractéristiques d'un tuyau afin de le
 * créer.
 *
 * @author hedi
 */
public class Pipe {

    //propriétés
    //Position d'un tuyau au départ (tout à droite)
    private int x = 360;
    private int y = 0;
    //Largeur/hauteur
    private int width = 64;
    private int height = 512;
    //L'image d'un tuyau
    private Image img;
    //Boolean pour pour savoir si le jeu est fini
    private boolean passed = false;

    Pipe(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getPipeImg() {
        return img;
    }

    public Boolean getPassed() {
        return passed;
    }
    public void setPassed(Boolean passed){
        this.passed = passed;
    }
}
