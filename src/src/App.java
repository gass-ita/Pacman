package src;


import Visualizer.GameboardVisualizerCLI;

public class App {
    public static void main(String[] args) throws Exception {
        Gameboard gameboard = new Gameboard();
        gameboard.createPacman(1, 1);
        GameboardStepper gameboardStepper = new GameboardStepper(gameboard, 10);
        gameboardStepper.start();
        GameboardVisualizerCLI gameboardVisualizerCLI = new GameboardVisualizerCLI(gameboard, 100);
        gameboardVisualizerCLI.start();

    }
}
