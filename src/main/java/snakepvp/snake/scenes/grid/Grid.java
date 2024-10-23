package snakepvp.snake.scenes.grid;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import snakepvp.snake.scenes.GameScene;

import java.util.Arrays;
import java.util.Random;

public class Grid implements GridComponent {
    private GridRow[] gridRows;
    private int gridCellStrokeLength = 50;
    private int numOfRows, numOfCellsPerRow;
    private Coordinate2D gridStart, gridEnd;

    public Grid(Coordinate2D start, Coordinate2D end) {
        this.gridStart = start;
        this.gridEnd = end;
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

    public void draw(GameScene scene) {
        for (GridRow row : gridRows) {
            row.draw(scene);
        }
    }

    public GridCell getRandomCell(int spaceFromBorder) {
        Random rn = new Random();

        int randomRow = rn.nextInt(spaceFromBorder, numOfRows - spaceFromBorder);
        int randomCell = rn.nextInt(spaceFromBorder, numOfCellsPerRow - spaceFromBorder);


        return gridRows[randomRow].getCells()[randomCell];
    }

    public boolean isEntityOnGrid(SpriteEntity entity) {
        return entity.getAnchorLocation().getX() >= gridStart.getX() && entity.getAnchorLocation().getX() <= gridEnd.getX() && entity.getAnchorLocation().getY() >= gridStart.getY() && entity.getAnchorLocation().getY() <= gridEnd.getY();
    }

    public boolean isEntityAlignedToGrid(SpriteEntity entity) {
        return ((entity.getAnchorLocation().getX() % gridCellStrokeLength) == 0) && ((entity.getAnchorLocation().getY() % gridCellStrokeLength) == 0);
    }


    public Coordinate2D getRandomCellCoordinates(int spaceFromBorder){
        GridCell randomCell = getRandomCell(spaceFromBorder);
        return new Coordinate2D(randomCell.getX(), randomCell.getY());
    }

    public Coordinate2D getRandomCellCoordinates(){
        GridCell randomCell = getRandomCell(0);
        return new Coordinate2D(randomCell.getX(), randomCell.getY());
    }

    public GridCell getRandomCell(){
        return getRandomCell(0);
    }

    public Coordinate2D getGridStart() {
        return gridStart;
    }

    public void setGridStart(Coordinate2D gridStart) {
        this.gridStart = gridStart;
    }

    public Coordinate2D getGridEnd() {
        return gridEnd;
    }

    public void setGridEnd(Coordinate2D gridEnd) {
        this.gridEnd = gridEnd;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "gridRows=" + Arrays.toString(gridRows) +
                '}';
    }
}
