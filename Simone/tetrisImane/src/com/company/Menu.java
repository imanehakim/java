package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    private Main window;

    private Timer timer;


    public Menu(Main window){

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

        g.setColor(Color.CYAN);

        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.BLACK);

        g.drawString("Imane's tetris!", 150, Main.HEIGHT /2 -100);

        g.drawString("Premi invio per iniziare!", 150, Main.HEIGHT / 2 + 100);


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