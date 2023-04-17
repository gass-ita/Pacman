package src;


import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import Entities.Entity;
import Entities.Pacman;
import Map.Cell;
import Map.Path;
import Map.Wall;

public class Gameboard implements KeyListener {

    /* default variables */
    private final int DEFAULT_WIDTH = 10;
    private final int DEFAULT_HEIGHT = 10;
    private final Cell[][] DEFAULT_BOARD = new Cell[DEFAULT_WIDTH][DEFAULT_HEIGHT];

    private Cell[][] board;
    private int width;
    private int height;

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private Pacman pacman;

    /* constructors */
    public Gameboard(){
        this.board = DEFAULT_BOARD;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;

        for(int i = 1; i < this.width - 1; i++){
            for(int j = 1; j < this.height - 1; j++){
                this.board[i][j] = new Path();
            }
        }

        for(int i = 0; i < this.width; i++){
            this.board[i][0] = new Wall();
            this.board[i][this.height - 1] = new Wall();
        }

        for(int i = 0; i < this.height; i++){
            this.board[0][i] = new Wall();
            this.board[this.width - 1][i] = new Wall();
        }
        
        new TestFrame(this);

    }

    public void step(){
        for(Entity entity : this.entities){
            entity.step();
        }
    }

    public void addEntity(Entity entity){
        this.entities.add(entity);
    }

    public void createEntity(int x, int y){
        Entity entity = new Entity(this.board, x, y);
        this.addEntity(entity);
    }

    public void createPacman(int x, int y){
        Pacman pacman = new Pacman(this.board, x, y);
        this.pacman = pacman;
        this.addEntity(pacman);
    }
    

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                output += this.board[j][i].toString();
            }
            output += "\n";
        }
        return output;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                this.pacman.setMovingDirection(Pacman.DIRECTION_UP);
                break;
            case KeyEvent.VK_DOWN:
                this.pacman.setMovingDirection(Pacman.DIRECTION_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                this.pacman.setMovingDirection(Pacman.DIRECTION_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.pacman.setMovingDirection(Pacman.DIRECTION_RIGHT);
                break;
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }


    

}