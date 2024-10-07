package com.snakepvp.snake.scenes.grid;

public class GridCell {
    private int y;
    private int cellNumber;

    public GridCell(int y, int cellNumber) {
        this.y = y;
        this.cellNumber = cellNumber;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "GridCell{" +
                "y=" + y +
                ", cellNumber=" + cellNumber +
                '}';
    }
}
