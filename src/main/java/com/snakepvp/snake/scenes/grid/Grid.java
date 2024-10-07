package com.snakepvp.snake.scenes.grid;

import java.util.Arrays;

public class Grid {
    private GridRow[] gridRows;

    public Grid(int numOfRows, int numOfCellsPerRow, int gridCellStrokeLength) {
        generateRows(numOfRows, numOfCellsPerRow, gridCellStrokeLength);
    }

    private void generateRows(int numOfRows, int numOfCellsPerRow, int gridCellStrokeLength){
        gridRows = new GridRow[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            gridRows[i] = new GridRow((gridCellStrokeLength * (i+1)), (i + 1), numOfCellsPerRow, gridCellStrokeLength);
        }
    }

    public String getCurrentCell(int x, int y){
        int rowNumber = 0;
        int cellNum = 0;

        //get which row currentCell is in
        for (GridRow row : gridRows) {
            if (row.getX() == x){
                rowNumber = row.getRowNumber();

                //get which cell currentCell is in
                for (GridCell cell : row.getCells()) {
                    if (cell.getY() == y){
                        cellNum = cell.getY();
                    }
                }
            }
        }
        //return which cell the snake is in
        return "Row: " + rowNumber + "\n" +
                "Cell: " + cellNum + "\n";
    }

    public GridRow[] getGridRows() {
        return gridRows;
    }

    public void setGridRows(GridRow[] gridRows) {
        this.gridRows = gridRows;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "gridRows=" + Arrays.toString(gridRows) +
                '}';
    }
}
