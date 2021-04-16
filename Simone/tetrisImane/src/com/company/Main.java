package com.company;


import javax.swing.JFrame;

public class Main {
    //dimensioni totali del pannello di gioco
    public static final int WIDTH = 500, HEIGHT = 640;

    private GameBoard gameBoard;
    private Menu menu;
    private JFrame window;

    public Main() {

        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        //il pannello non è modificabile in dimensione
        window.setResizable(false);

        gameBoard = new GameBoard();
        menu = new Menu(this);

        window.addKeyListener(gameBoard);
        window.addKeyListener(menu);
        //compare prima il menu
        window.add(menu);

        window.setVisible(true);
    }

    public void startTetris() {
        // quando l'utente preme invio per far partire il gioco rimuovo la pagina iniziale e va al pannello di gioco effettivo
        window.remove(menu);
        window.add(gameBoard);
        gameBoard.startGame();
        window.revalidate();
    }

    public static void main(String[] args) {

        new Main();
    }

}
