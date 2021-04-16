package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    private Main window;

    private Timer timer;
    private BufferedImage startBoard;

    public Menu(Main window){

        startBoard = ImageMenu.loadImage("/menu.png");
        timer = new Timer(1000/60, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                repaint();
            }

        });
        timer.start();
        this.window = window;



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.BLACK);

        g.drawImage(startBoard, 70, 100, null);




    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER) {
            window.startTetris();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}