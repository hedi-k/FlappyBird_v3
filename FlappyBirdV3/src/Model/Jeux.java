package Model;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Cette classe contient le fonctionnement du jeu.
 *
 * @author hedi
 */
public class Jeux extends JPanel implements ActionListener, KeyListener {

    //Paramètres:
    int boardWidth = 360;
    int boardHeight = 640;
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeHeight = 512;
    int velocityX = -4;
    // négatif Y => monte dans l'image 
    int velocityY = 0;
    // positif donc déscend dans l'image
    int gravity = +1;

    boolean gameOver = false;
    double score = 0;
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;
    Bird bird;
    ArrayList<Pipe> tabPipes;
    Timer gameLoop; //boucle d'affichage du jeux.
    Timer placePipesTimer; //boucle de génération des tuyaux.

    public Jeux() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //Ce panel est bien le focus pour les KeyEvent et écoute l'event.
        setFocusable(true);
        addKeyListener(this);
        backgroundImg = new ImageIcon(getClass().getResource("/Images/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/Images/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/Images/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/Images/bottompipe.png")).getImage();
        //Valorise un "oiseau"
        this.bird = new Bird(birdImg);
        tabPipes = new ArrayList<Pipe>();
        //Boucle sur la génération d'un tuyaux tout les 1.5s appel placePipes()
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();
        //Boucle du jeu 1000ms divisé par 60 =>60 images secondes
        //Le timer appel actionPerformed 60 fois par seconde d'où l'implements
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    //Construit les tuyaux et les placent dans le tableau de tuyaux.
    public void placePipes() {
        //Donne un nombre aléatoire entre 1/4 et 3/4 du tuyaux.  
        //                       0     -     128     -   (entre 0 et 1) *    256  
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        //L'espace pour laisser passer l'oiseau entre deux tuyaux.
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.setY(randomPipeY);
        tabPipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        int a = topPipe.getY() + pipeHeight + openingSpace;
        bottomPipe.setY(a);
        tabPipes.add(bottomPipe);
    }

    //Permet de faire déplacer "l'oiseau" et tuyaux (verticale)
    //Math.max retourne la plus grand valeur entre ces paramètres,
    //comme paramBird.y se réduit 0 sera la plus grande valeur et donc paramBird.Y sera 0 en MAX
    public void move() {
        velocityY += gravity;
        System.out.println("velocityY => " + velocityY);
        bird.setY(bird.getY() + velocityY);
        System.out.println("birdY => " + bird.getY());
        //Pour l'empecher de sortir de l'écran
        bird.setY(Math.max(bird.getY(), 0));
        //Fait déplacer les tuyaux
        for (int i = 0; i < tabPipes.size(); i++) {
            Pipe pipe = tabPipes.get(i);
            pipe.setX(pipe.getX() + velocityX);

            if (!pipe.getPassed() && pipe.getX() > pipe.getWidth()) {
                pipe.setPassed(true);
                score += 0.5;
            }
            //Si touche tuyaux c'est game over.
            if (Collision.collision(bird, pipe)) {
                gameOver = true;
            }
        }
        //Si sort de l'écran par le bas c'est game over.
        if (bird.getY() > boardHeight) {
            gameOver = true;
        }
    }

    //implements ActionListener
    //Va appeler ces méthodes 60 fois par secondes.
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //Chaque barre d'espace me fera monter de 9.
            velocityY = -9;
            //Reinitialise le jeux
            if (gameOver) {
                bird.setY(360 / 8);
                velocityY = 0;
                tabPipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    //Dessine une image avec paramètres (coordonnées,longueur...)
    public void draw(Graphics g) {
        //Image de fond
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        //Image de l'oiseau
        g.drawImage(birdImg, bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);
        //Bouble pour dessiner les tuyaux,
        for (int i = 0; i < tabPipes.size(); i++) {
            Pipe pipe = tabPipes.get(i);
            g.drawImage(pipe.getPipeImg(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("PERDU ! score : " + String.valueOf((int) score), 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }
}
