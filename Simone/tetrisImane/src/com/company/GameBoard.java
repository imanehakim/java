package com.company;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements KeyListener{

    private static final long serialVersionUID = 1L;

    // la dimensione dell'area da gioco
    private final int boardHeight = 20, boardWidth = 10;

    // dimensione del singolo quadratino in pixel squareSize
    public static final int squareSize = 30;

    // board è una matrice
    private Color[][] board = new Color[boardHeight][boardWidth];

    // array dei tetramini disponibili
    private TetraminoShape[] tetraminoShapes = new TetraminoShape[7];

    // tetramini in gioco e quello successivo
    private static TetraminoShape currentTetraminoShape, nextTetraminoShape;

    // tempo di gioco di gioco
    private Timer timer;


    private int delay = 1000/60;

    private boolean gamePaused = false;

    private boolean gameOver = false;

    private Color[] colors = {Color.red, Color.green, Color.yellow,Color.pink, Color.blue, Color.cyan, Color.lightGray};
    private Random random = new Random();



    // punteggio
    private int score = 0;

    public GameBoard() {





        // crea game timer
        timer = new Timer(delay, new GameTimer());

        // istanziamento tetramini
        tetraminoShapes[0] = new TetraminoShape(new int[][]{
                {1, 1, 1, 1} // form I;
        }, this, colors[0]);

        tetraminoShapes[1] = new TetraminoShape(new int[][]{
                {1, 1, 1},
                {0, 1, 0}, //forma T ;
        }, this, colors[1]);

        tetraminoShapes[2] = new TetraminoShape(new int[][]{
                {1, 1, 1},
                {1, 0, 0}, // forma L ;
        }, this, colors[2]);

        tetraminoShapes[3] = new TetraminoShape(new int[][]{
                {1, 1, 1},
                {0, 0, 1}, // forma J ;
        }, this, colors[3]);

        tetraminoShapes[4] = new TetraminoShape(new int[][]{
                {0, 1, 1},
                {1, 1, 0}, // forma S;
        }, this, colors[4]);

        tetraminoShapes[5] = new TetraminoShape(new int[][]{
                {1, 1, 0},
                {0, 1, 1}, // forma Z ;
        }, this, colors[5]);

        tetraminoShapes[6] = new TetraminoShape(new int[][]{
                {1, 1},
                {1, 1}, // forma O ;
        }, this, colors[6]);

    }

    private void update() {


        if (gamePaused || gameOver) {
            return;
        }
        currentTetraminoShape.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
                }

            }
        }
        g.setColor(nextTetraminoShape.getColor());
        for (int row = 0; row < nextTetraminoShape.getTetraminoCoords().length; row++) {
            for (int col = 0; col < nextTetraminoShape.getTetraminoCoords()[0].length; col++) {
                if (nextTetraminoShape.getTetraminoCoords()[row][col] != 0) {
                    g.fillRect(col * 30 + 320, row * 30 + 50, GameBoard.squareSize, GameBoard.squareSize);
                }
            }
        }
        currentTetraminoShape.render(g);


        if (gamePaused) {
            String gamePausedString = "GAME PAUSED";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Georgia", Font.BOLD, 30));
            g.drawString(gamePausedString, 35, Main.HEIGHT / 2);
        }
        if (gameOver) {
            String gameOverString = "GAME OVER";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Georgia", Font.BOLD, 30));
            g.drawString(gameOverString, 50, Main.HEIGHT / 2);
        }
        g.setColor(Color.WHITE);

        g.setFont(new Font("Georgia", Font.BOLD, 20));
        g.drawString("SCORE", Main.WIDTH - 190, Main.HEIGHT -100);
        g.drawString(score + "", Main.WIDTH - 190, Main.HEIGHT-80);

        g.drawString("Istruzioni", Main.WIDTH - 190, Main.HEIGHT / 3);

        g.setFont(new Font("Georgia", Font.ITALIC, 10));

        g.drawString( "p= pausa;", Main.WIDTH - 190, Main.HEIGHT / 3 + 30);

        g.drawString( "space bar= riprendi", Main.WIDTH - 190, Main.HEIGHT / 3 + 60);

        g.drawString( " freccia destra= muove a destra", Main.WIDTH -190, Main.HEIGHT / 3 + 90);


        g.drawString( " freccia sinistra= muove a sinistra", Main.WIDTH - 190, Main.HEIGHT / 3 + 120);


        g.drawString( " freccia in basso= veloce", Main.WIDTH - 190, Main.HEIGHT / 3 + 150);


        g.drawString( " freccia in alto= ruota", Main.WIDTH - 190  , Main.HEIGHT / 3 + 180);

        g.drawString( " invio= restart game", Main.WIDTH - 190  , Main.HEIGHT / 3 + 210);



        for (int i = 0; i <= boardHeight; i++) {
            g.drawLine(0, i * squareSize, boardWidth * squareSize, i * squareSize);
        }
        for (int j = 0; j <= boardWidth; j++) {
            g.drawLine(j * squareSize, 0, j * squareSize, boardHeight * 30);
        }
    }

    public void setNextShape() {
        int index = random.nextInt(tetraminoShapes.length);

        nextTetraminoShape = new TetraminoShape(tetraminoShapes[index].getTetraminoCoords(), this, colors[index]);
    }

    public void setCurrentShape() {
        currentTetraminoShape = nextTetraminoShape;
        setNextShape();

        for (int row = 0; row < currentTetraminoShape.getTetraminoCoords().length; row++) {
            for (int col = 0; col < currentTetraminoShape.getTetraminoCoords()[0].length; col++) {
                if (currentTetraminoShape.getTetraminoCoords()[row][col] != 0) {
                    if (board[currentTetraminoShape.getY() + row][currentTetraminoShape.getX() + col] != null) {
                        gameOver = true;
                    }
                }
            }
        }

    }

    public Color[][] getBoard() {
        return board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentTetraminoShape.rotateShape();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentTetraminoShape.setDeltaX(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentTetraminoShape.setDeltaX(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentTetraminoShape.speedUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePaused=true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gamePaused=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentTetraminoShape.speedDown();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void startGame() {
        stopGame();
        setNextShape();
        setCurrentShape();
        gameOver = false;
        timer.start();

    }

    public void stopGame() {
        score = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = null;
            }
        }
        timer.stop();
    }

    class GameTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            update();
            repaint();
        }

    }


    public void addScore() {

        score++;
    }

}