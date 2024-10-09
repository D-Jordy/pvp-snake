package snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.scenes.GameScene;

import java.util.ArrayList;
import java.util.Random;

public class ItemSpawner {
    private GameScene scene;
    private ArrayList<Item> items = new ArrayList<>();

    public ItemSpawner(GameScene scene, ArrayList<Item> items) {
        this.scene = scene;
        this.items = items;
    }

    public void spawnItem() {
        spawnItem(scene, items);
    }

    public static void spawnItem(GameScene scene, ArrayList<Item> items) {
        Item item = items.get(
                new Random().nextInt(items.size())
        );

        item.setAnchorLocation(getRandomGridCellCoords(scene.getGridStart(), scene.getGridEnd()));

        scene.introduceEntity(item);
    }

    public static Coordinate2D getRandomGridCellCoords(Coordinate2D start, Coordinate2D end) {
        return new Coordinate2D(
                getRandomCoordInRange((int) start.getX(), (int) end.getX()),
                getRandomCoordInRange((int) start.getY(), (int) end.getY())
        );
    }

    public static int getRandomCoordInRange(int min, int max) {
        //Random int between min and max, rounded to nearest 50, with a minimum of 50
        // to avoid spawning on the edge (out of bounds)
        return (new Random().nextInt((max - min) / 50) * 50) + (min + 50);
    }
}
