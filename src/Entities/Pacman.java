package Entities;

import Map.Cell;

public class Pacman extends Entity{
    
    public Pacman(Cell[][] board, int x, int y){
        super(board, x, y);
    }

    @Override
    public String toString() {
        return "P";
    }
}
