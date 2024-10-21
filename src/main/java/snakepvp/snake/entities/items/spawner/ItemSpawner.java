package snakepvp.snake.entities.items.spawner;

import snakepvp.snake.entities.items.base.Item;
import snakepvp.snake.entities.items.base.SpawnableFactory;
import snakepvp.snake.entities.items.food.FoodFactory;
import snakepvp.snake.scenes.GameScene;
import snakepvp.snake.scenes.grid.Grid;

public class ItemSpawner {
    private static GameScene scene;
    private static Grid grid;

    public static void spawnItem(Item item){
        scene.introduceEntity(item);
    }


    public static void spawnItemFromFactory(SpawnableFactory factory) {
        scene.introduceEntity(factory.createInstance(grid.getRandomCellCoordinates()));
    }

    public static void setScene(GameScene gameScene){
        scene = gameScene;
    }

    public static void setGrid(Grid gameGrid){
        grid = gameGrid;
    }
}

