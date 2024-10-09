package snakepvp.snake.scenes.grid;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.scenes.GameScene;

import java.util.Arrays;

public class Grid implements GridComponent {
    private GridRow[] gridRows;
    private int gridCellStrokeLength = 50;

    public Grid(Coordinate2D start, Coordinate2D end) {
        generateRows(start, end);
    }

    private void generateRows(Coordinate2D start, Coordinate2D end) {
        //calculate number of rows based on length and height, rounded down
        int numOfRows = (int) Math.floor((end.getX() - start.getX()) / gridCellStrokeLength);
        int numOfCellsPerRow = (int) Math.floor((end.getY() - start.getY()) / gridCellStrokeLength);

        //create rows
        gridRows = new GridRow[numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            gridRows[i] = new GridRow((start.getX() + (gridCellStrokeLength * (i+1))), start.getY(), (i + 1), numOfCellsPerRow, gridCellStrokeLength);
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

    public void setGridRows(GridRow[] gridRows) {
        this.gridRows = gridRows;
    }

    public void draw(GameScene scene) {
        for (GridRow row : gridRows) {
            row.draw(scene);
        }
    }

    @Override
    public String toString() {
        return "Grid{" +
                "gridRows=" + Arrays.toString(gridRows) +
                '}';
    }
}
