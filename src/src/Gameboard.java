package src;


import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import Entities.Entity;
import Entities.Pacman;
import Map.Cell;
import Map.Path;
import Map.Wall;
import Utils.Debugger;

public class Gameboard implements KeyListener {
    public static final int WALL = 0;
    public static final int PATH = 1;
    public static final int ENTITY = 2;
    

    /* default variables */
    private final int DEFAULT_WIDTH = 10;
    private final int DEFAULT_HEIGHT = 10;
    private final Cell[][] DEFAULT_BOARD = new Cell[DEFAULT_HEIGHT][DEFAULT_WIDTH];

    private Cell[][] board;
    private int[][] boardInt;
    private int width;
    private int height;

    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private Pacman pacman;

    /* constructors */
    public Gameboard(){
        this.board = DEFAULT_BOARD;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;

        this.boardInt = new int[this.height][this.width];

        //fill with paths
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                this.board[i][j] = new Path();
            }
        }

        //surround with walls
        for(int i = 0; i < this.height; i++){
            this.board[i][0] = new Wall();
            this.board[i][this.width - 1] = new Wall();
        }
        for(int i = 0; i < this.width; i++){
            this.board[0][i] = new Wall();
            this.board[this.height - 1][i] = new Wall();
        }

        

        
        updateBoardInt();

    }

    public void step(){
        for(Entity entity : this.entities){
            entity.step();
        }
        updateBoardInt();
    }

    public void addEntity(Entity entity){
        this.entities.add(entity);
        this.boardInt[entity.getY()][entity.getX()] = Gameboard.ENTITY;
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
                output += this.board[i][j].toString();
            }
            output += "\n";
        }
        return output;
    }

    public void updateBoardInt(){
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                if(this.board[i][j] instanceof Wall){
                    this.boardInt[i][j] = Gameboard.WALL;
                }else if(this.board[i][j] instanceof Path){
                    if (((Path) this.board[i][j]).getEntity() != null)
                        this.boardInt[i][j] = Gameboard.ENTITY;
                    else
                        this.boardInt[i][j] = Gameboard.PATH;
                }
            }
        }
    }

    public int[][] getBoardInt(){
        return this.boardInt;
    }
    


    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Debugger.log("Key pressed: " + e.getKeyCode() + " " + e.getKeyChar());
        
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

    public Pacman getPacman() {
        return this.pacman;
    }


    

}