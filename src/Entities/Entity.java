package Entities;

import Map.Cell;
import Utils.Debugger;

public class Entity {

    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;
    public static final int DIRECTION_STAY = -1;

    public final int SPEED_SLOW = 3;
    public final int SPEED_NORMAL = 2;
    public final int SPEED_FAST = 1;

    /* default vars */
    public int DEFAULT_SPEED = SPEED_NORMAL;
    public int DEFAULT_DIRECTION = DIRECTION_RIGHT;



    Cell[][] board;
    int x;
    int y;
    int movingDirection;
    int speed;

    private int stepCount = 0;

    public Entity(Cell[][] board, int x, int y) {
        this.board = board;

        Cell cell = this.board[x][y];
        if (cell instanceof Map.Path) {
            ((Map.Path) cell).setEntity(this);
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException("Entity must be placed on a path");
        }

        this.movingDirection = DEFAULT_DIRECTION;
        this.speed = DEFAULT_SPEED;
    }

    public void step() {
        Debugger.log("Entity step called on [x:" + this.x + " y:" + this.y + " dir:" + this.movingDirection + " speed:" + this.speed + " stepCount:" + this.stepCount + " type:" + this.toString() + "]");
        this.stepCount++;
        if (this.stepCount == this.speed) {
            this.stepCount = 0;
            int newX = this.x;
            int newY = this.y;

            switch (this.movingDirection) {
                case DIRECTION_UP:
                    newY -= 1;
                    break;
                case DIRECTION_DOWN:
                    newY += 1;
                    break;
                case DIRECTION_LEFT:
                    newX -= 1;
                    break;
                case DIRECTION_RIGHT:
                    newX += 1;
                    break;
                case DIRECTION_STAY:
                    break;
                default:
                    throw new IllegalArgumentException("Invalid direction");
            }

            Cell newCell = this.board[newX][newY];
            if (newCell instanceof Map.Path) {
                Map.Path newPath = (Map.Path) newCell;
                if (newPath.getEntity() == null) {
                    newPath.setEntity(this);
                    this.board[this.x][this.y] = new Map.Path();
                    this.x = newX;
                    this.y = newY;
                }
            }
        } 
            
        
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getMovingDirection() {
        return this.movingDirection;
    }

    public void setMovingDirection(int direction) {
        this.movingDirection = direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "E";
    }
}
