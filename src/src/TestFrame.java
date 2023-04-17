package src;

import javax.swing.JFrame;

public class TestFrame extends JFrame {

    private Gameboard gameboard;

    public TestFrame(Gameboard gameboard) {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.gameboard = gameboard;
        this.addKeyListener(gameboard);
    }
}
    

