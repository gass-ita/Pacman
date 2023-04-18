
package Visualizer;
import src.Gameboard;
/* i want to use a thread */
// package GameboardVisualizerCLI;
import src.TestFrame;

public class GameboardVisualizerCLI implements Runnable {
    static final char WALL = '#';
    static final char PATH = ' ';
    static final char PACMAN = 'P';
    static final char GHOST = 'G';
    //static final char DOT = '.';
    //static final char POWERUP = 'o';
    

    Gameboard gameboard;
    double refreshRate;
    boolean running = false;


    public GameboardVisualizerCLI(Gameboard gameboard, double refreshRate) {
        this.gameboard = gameboard;
        this.refreshRate = refreshRate;
        new TestFrame(gameboard);
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
        while (true) {
            /* clear the screen */
            System.out.print("\033[H\033[2J");
            int[][] boardInt = gameboard.getBoardInt();
            for (int i = 0; i < boardInt.length ; i++) {
                for (int j = 0; j < boardInt[i].length; j++) {
                    switch (boardInt[i][j]) {
                        case Gameboard.WALL:
                            System.out.print(WALL);
                            break;
                        case Gameboard.PATH:
                            System.out.print(PATH);
                            break;
                        case Gameboard.ENTITY:
                            if (gameboard.getPacman().getX() == j && gameboard.getPacman().getY() == i)
                                System.out.print(PACMAN);
                            else
                                System.out.print(GHOST);
                            break;
                        default:
                            System.out.print(PATH);
                            break;
                    }
                }
                System.out.println();
            }
            //System.out.println(gameboard);
            try {
                Thread.sleep((long) (1000 / refreshRate));
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
    
}