package View;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Jeux;


/**
 * vue
 *
 * @author hedi
 */
public class EntreeJeu extends JPanel {

    //Propriétés
    private JFrame frmJeu;
    //Fenêtre du jeux largeur .
    int boardWidth = 360;
    //Fenêtre du jeux hauteur.
    int boardHeight = 640;
    Image backgroundImg;

    private Jeux jeux;
    //private Pipe pipe;

    public EntreeJeu(Jeux jeux) {
        this.jeux = jeux;
        //Fenêtre principale
        frmJeu = new JFrame("Flappy Pigeon");
        System.out.println("essai (vue)");
        //Dimensionne la fenêtre.
        frmJeu.setSize(boardWidth, boardHeight);
        //Place la fenêtre au centre de l'écran.
        frmJeu.setLocationRelativeTo(null);
        //Empêche de modifier la taille de la fenêtre du jeux.
        frmJeu.setResizable(false);
        //Valorise les images
        backgroundImg = new ImageIcon(getClass().getResource("/Images/flappybirdbg.png")).getImage();
        //Ferme la programme quand on ferme la fenêtre.
        frmJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmJeu.add(this.jeux);
        //pack prend en compte la barre de titre en haut
        frmJeu.pack();
        frmJeu.setVisible(true);
    }


}
