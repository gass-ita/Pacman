
package Visualizer;
import src.Gameboard;
/* i want to use a thread */
// package GameboardVisualizerCLI;

public class GameboardVisualizerCLI implements Runnable {
    Gameboard gameboard;
    double refreshRate;
    boolean running = false;

    public GameboardVisualizerCLI(Gameboard gameboard, double refreshRate) {
        this.gameboard = gameboard;
        this.refreshRate = refreshRate;
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
            System.out.println(gameboard);
            try {
                Thread.sleep((long) (1000 / refreshRate));
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
    
}