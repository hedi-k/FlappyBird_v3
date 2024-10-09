package Model;

import java.awt.Image;

/**
 * Model (l'oiseau) Classe qui contient les caractéristiques de l'oiseau afin de
 * le créer.
 *
 * @author hedi
 */
public class Bird {

    //propriétés
    //Position de l'oiseau sur x
    private int x = 360 / 8;
    //Position de l'oiseau sur y 
    private int y = 640 / 2;
    //Taille de l'oiseau
    private int width = 34;
    private int height = 24;
    //L'image de l'oiseau
    private Image img;

    //Constructeur
    public Bird(Image img) {
        this.img = img;
    }

    //Asseceurs pour les accès aux propriété valorisées
    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
    
    public void setY(int y){
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public Image getBirdImg() {
        return img;
    }

}
