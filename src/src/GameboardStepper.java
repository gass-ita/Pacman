package src;

public class GameboardStepper implements Runnable{
    Gameboard gameboard;
    double stepRate;
    boolean running = false;

    public GameboardStepper(Gameboard gameboard, double stepRate) {
        this.gameboard = gameboard;
        this.stepRate = stepRate;
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
        while(running){
            gameboard.step();
            try {
                Thread.sleep((long) (1000 / stepRate));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    
}
