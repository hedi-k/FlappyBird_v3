package Controler;

import View.EntreeJeu;
import Model.Jeux;

/**
 * Jeux FlappyBird Date 08/10/2024
 *
 * @author hedi
 */
public class Controle {

    //Propriétés
    private EntreeJeu frmEntreeJeu;
    private Jeux jeux;

    public static void main(String[] args) {
        //Appel du controleur
        new Controle();
    }

    //Controleur
    private Controle() {
        this.jeux = new Jeux();
        this.frmEntreeJeu = new EntreeJeu(jeux);

    }

}
