package com.snakepvp.snake.scenes.grid;

import com.snakepvp.snake.scenes.GameScene;

import java.util.Arrays;

public class GridRow implements GridComponent {
    private int x;
    private int rowNumber;
    private GridCell[] cells;

    public GridRow(int x, int rowNumber, int numOfCells, int gridCellStrokeLength) {
        this.x = x;
        this.rowNumber = rowNumber;
        cells = new GridCell[numOfCells];
        generateCells(numOfCells, gridCellStrokeLength);
    }

    private void generateCells(int numOfCells, int gridCellStrokeLength) {
        cells = new GridCell[numOfCells];
        for (int i = 0; i < numOfCells; i++) {
            cells[i] = new GridCell(this.x, (gridCellStrokeLength * (i + 1)), i + 1);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public GridCell[] getCells() {
        return cells;
    }

    public void setCells(GridCell[] cells) {
        this.cells = cells;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public String toString() {
        return "GridRow{" +
                "x=" + x +
                ", rowNumber='" + rowNumber + '\'' +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }

    public void draw(GameScene scene) {
        for (GridCell cell : cells) {
            cell.draw(scene);
        }
    }
}
