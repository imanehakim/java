package com.company;


import java.awt.Color;
import java.awt.Graphics;

public class TetraminoShape {

    private Color color;

    private int x, y;

    private long time, lastTime;

    private int normal = 600, fast = 50;

    private int delay;

    private int[][] tetraminoCoords;



    private int deltaX;

    private GameBoard gameBoard;

    private boolean collision = false, moveX = false;

    private int timePassedFromCollision = -1;

    public TetraminoShape(int[][] tetraminoCoords, GameBoard gameBoard, Color color) {
        this.tetraminoCoords = tetraminoCoords;
        this.gameBoard = gameBoard;
        this.color = color;
        deltaX = 0;
        x = 4;
        y = 0;
        delay = normal;
        time = 0;
        lastTime = System.currentTimeMillis();
    }

    long deltaTime;

    public void update() {
        moveX = true;
        deltaTime = System.currentTimeMillis() - lastTime;
        time += deltaTime;
        lastTime = System.currentTimeMillis();

        if (collision && timePassedFromCollision > 500) {
            for (int row = 0; row < tetraminoCoords.length; row++) {
                for (int col = 0; col < tetraminoCoords[0].length; col++) {
                    if (tetraminoCoords[row][col] != 0) {
                        gameBoard.getBoard()[y + row][x + col] = color;
                    }
                }
            }
            checkLine();
            gameBoard.addScore();
            gameBoard.setCurrentShape();
            timePassedFromCollision = -1;
        }

        // controllo movimento orizzontale
        if (!(x + deltaX + tetraminoCoords[0].length > 10) && !(x + deltaX < 0)) {

            for (int row = 0; row < tetraminoCoords.length; row++) {
                for (int col = 0; col < tetraminoCoords[row].length; col++) {
                    if (tetraminoCoords[row][col] != 0) {
                        if (gameBoard.getBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }

                    }
                }
            }

            if (moveX) {
                x += deltaX;
            }

        }

        // verifico la posizione + il numero delle righe che formano il tetramino
        if (timePassedFromCollision == -1) {
            if (!(y + 1 + tetraminoCoords.length > 20)) {

                for (int row = 0; row < tetraminoCoords.length; row++) {
                    for (int col = 0; col < tetraminoCoords[row].length; col++) {
                        if (tetraminoCoords[row][col] != 0) {

                            if (gameBoard.getBoard()[y + 1 + row][x + col] != null) {
                                collision();
                            }
                        }
                    }
                }
                if (time > delay) {
                    y++;
                    time = 0;
                }
            } else {
                collision();
            }
        } else {
            timePassedFromCollision += deltaTime;
        }

        deltaX = 0;
    }

    private void collision() {
        collision = true;
        timePassedFromCollision = 0;
    }

    public void render(Graphics g) {

        g.setColor(color);
        for (int row = 0; row < tetraminoCoords.length; row++) {
            for (int col = 0; col < tetraminoCoords[0].length; col++) {
                if (tetraminoCoords[row][col] != 0) {
                    g.fillRect(col * 30 + x * 30, row * 30 + y * 30, GameBoard.squareSize, GameBoard.squareSize);
                }
            }
        }



    }

    private void checkLine() {
        int size = gameBoard.getBoard().length - 1;

        for (int i = gameBoard.getBoard().length - 1; i > 0; i--) {
            int count = 0;
            for (int j = 0; j < gameBoard.getBoard()[0].length; j++) {
                if (gameBoard.getBoard()[i][j] != null) {
                    count++;
                }

                gameBoard.getBoard()[size][j] = gameBoard.getBoard()[i][j];
            }
            if (count < gameBoard.getBoard()[0].length) {
                size--;
            }
        }
    }

    public void rotateShape() {

        int[][] rotatedShape = null;

        rotatedShape = transposeMatrix(tetraminoCoords);

        rotatedShape = reverseRows(rotatedShape);

        if ((x + rotatedShape[0].length > 10) || (y + rotatedShape.length > 20)) {
            return;
        }

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int col = 0; col < rotatedShape[row].length; col++) {
                if (rotatedShape[row][col] != 0) {
                    if (gameBoard.getBoard()[y + row][x + col] != null) {
                        return;
                    }
                }
            }
        }
        tetraminoCoords = rotatedShape;
    }

    private int[][] transposeMatrix(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
        return temp;
    }

    private int[][] reverseRows(int[][] matrix) {

        int middle = matrix.length / 2;

        for (int i = 0; i < middle; i++) {
            int[] temp = matrix[i];

            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        return matrix;

    }

    public Color getColor() {

         return color;
    }

    public void setDeltaX(int deltaX) {

        this.deltaX = deltaX;
    }

    public void speedUp() {

        delay = fast;
    }

    public void speedDown() {

        delay = normal;

    }

    public int[][] getTetraminoCoords() {

        return tetraminoCoords;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }
}

