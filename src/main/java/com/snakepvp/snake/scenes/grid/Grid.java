package com.snakepvp.snake.scenes.grid;

import com.github.hanyaeger.api.Coordinate2D;
import com.snakepvp.snake.scenes.GameScene;

import java.util.Arrays;
import java.util.Random;

public class Grid implements GridComponent {
    private GridRow[] gridRows;
    private int gridCellStrokeLength = 50;
    private int numOfRows, numOfCellsPerRow;

    public Grid(Coordinate2D start, Coordinate2D end) {
        generateRows(start, end);
    }

    private void generateRows(Coordinate2D start, Coordinate2D end) {
        //calculate number of rows based on length and height, rounded down
        numOfRows = (int) Math.floor((end.getX() - start.getX()) / gridCellStrokeLength);
        numOfCellsPerRow = (int) Math.floor((end.getY() - start.getY()) / gridCellStrokeLength);

        //create rows
        gridRows = new GridRow[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            gridRows[i] = new GridRow((start.getX() + (gridCellStrokeLength * i)), start.getY(), (i + 1), numOfCellsPerRow, gridCellStrokeLength);
        }
    }

//    public String getCurrentCell(int x, int y) {
//        int rowNumber = 0;
//        int cellNum = 0;
//
//        //get which row currentCell is in
//        for (GridRow row : gridRows) {
//            if (row.getX() == x) {
//                rowNumber = row.getRowNumber();
//
//                //get which cell currentCell is in
//                for (GridCell cell : row.getCells()) {
//                    if (cell.getY() == y) {
//                        cellNum = cell.getY();
//                    }
//                }
//            }
//        }
//        //return which cell the snake is in
//        return "Row: " + rowNumber + "\n" +
//                "Cell: " + cellNum + "\n";
//    }

    public GridRow[] getGridRows() {
        return gridRows;
    }

    public void draw(GameScene scene) {
        for (GridRow row : gridRows) {
            row.draw(scene);
        }
    }

    public GridCell getRandomCell(int spaceFromBorder){
        Random rn = new Random();

            int randomRow = rn.nextInt(spaceFromBorder, numOfRows - spaceFromBorder);
            int randomCell = rn.nextInt(spaceFromBorder, numOfCellsPerRow - spaceFromBorder);


        return gridRows[randomRow].getCells()[randomCell];
    }

    public GridCell getRandomCell(){
        return getRandomCell(0);
    }

    @Override
    public String toString() {
        return "Grid{" +
                "gridRows=" + Arrays.toString(gridRows) +
                '}';
    }
}
