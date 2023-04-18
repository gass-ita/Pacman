package src;


import javax.swing.JFrame;

import Visualizer.GameboardVisualizerCLI;
import Visualizer.GameboardVisualizerGraphic;

public class App {
    public static void main(String[] args) throws Exception {
        Gameboard gameboard = new Gameboard();
        gameboard.createPacman(1, 1);
        GameboardStepper gameboardStepper = new GameboardStepper(gameboard, 10);
        gameboardStepper.start();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addKeyListener(gameboard);
        GameboardVisualizerGraphic gameboardVisualizerGraphic = new GameboardVisualizerGraphic(gameboard, 100d, 0, 0, 500, 500);
        
        frame.add(gameboardVisualizerGraphic);

        gameboardVisualizerGraphic.start();


    }
}
