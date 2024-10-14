package snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.scenes.GameScene;
import snakepvp.snake.scenes.grid.Grid;
import snakepvp.snake.scenes.grid.GridCell;

import java.util.ArrayList;
import java.util.Random;

public class ItemSpawner {
    private final GameScene scene;
    private final Grid grid;
    private ArrayList<Item> items = new ArrayList<>();

    public ItemSpawner(GameScene scene, ArrayList<Item> items, Grid grid) {
        this.scene = scene;
        this.items = items;
        this.grid = grid;
    }

    public void spawnItem() {
        spawnItem(scene, items, this.grid.getRandomCell());
    }

    public static void spawnItem(GameScene scene, ArrayList<Item> items, GridCell cell) {
        Item item = items.get(
                new Random().nextInt(items.size())
        );

        item.setAnchorLocation(getRandomGridCellCoords(scene.getGridStart(), scene.getGridEnd(), cell));
        scene.introduceEntity(item);
    }

    public static Coordinate2D getRandomGridCellCoords(Coordinate2D start, Coordinate2D end, GridCell cell) {
        return new Coordinate2D(
                cell.getAnchorLocation().getX(),
                cell.getAnchorLocation().getY()
        );
    }

    public static int getRandomCoordInRange(int min, int max) {
        //Random int between min and max, rounded to nearest 50, with a minimum of 50
        // to avoid spawning on the edge (out of bounds)
        return (new Random().nextInt((max - min) / 50) * 50) + (min + 50);
    }
}
