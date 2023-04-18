
package Visualizer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import src.Gameboard;

/**
 * GameboardVisualizerGraphic
 */
public class GameboardVisualizerGraphic extends JPanel implements Runnable{
    static final Color WALL = Color.WHITE;
    static final Color PATH = Color.BLACK;
    static final Color PACMAN = Color.YELLOW;
    static final Color GHOST = Color.RED;
    //static final Color DOT = Color.BLUE;
    //static final Color POWERUP = Color.GREEN;

    Gameboard gameboard;
    double refreshRate;
    boolean running = false;

    public GameboardVisualizerGraphic(Gameboard gameboard, double refreshRate, int x, int y, int width, int height) {
        this.gameboard = gameboard;
        this.refreshRate = refreshRate;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void stop() {
        running = false;
    }

    public void start() {
        running = true;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (running) {
            this.repaint();
            try {
                Thread.sleep((long) (1000 / refreshRate));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       
    }

    @Override
    public void paint(Graphics g) {
        
        super.paint(g);

        int[][] boardInt = gameboard.getBoardInt();
        int cellWidth = this.getWidth() / boardInt[0].length;
        int cellHeight = this.getHeight() / boardInt.length;

        for (int i = 0; i < boardInt.length ; i++) {
            for (int j = 0; j < boardInt[i].length; j++) {
                switch (boardInt[i][j]) {
                    case Gameboard.WALL:
                        g.setColor(WALL);
                        break;
                    case Gameboard.PATH:
                        g.setColor(PATH);
                        break;
                    case Gameboard.ENTITY:
                        if (gameboard.getPacman().getX() == j && gameboard.getPacman().getY() == i)
                            g.setColor(PACMAN);
                        else
                            g.setColor(GHOST);
                        break;
                    default:
                        g.setColor(PATH);
                        break;
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        } 

        

        
    }
    
}