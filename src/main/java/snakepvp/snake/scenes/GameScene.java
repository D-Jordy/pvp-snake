package snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import snakepvp.snake.Game;
import snakepvp.snake.entities.items.base.Item;
import snakepvp.snake.entities.items.food.FoodFactory;
import snakepvp.snake.entities.items.spawner.ItemSpawner;
import snakepvp.snake.entities.playcontrolled.Snake;
import snakepvp.snake.entities.playcontrolled.SnakeControls;
import snakepvp.snake.scenes.grid.Grid;

import java.util.ArrayList;

public class GameScene extends DynamicScene {
    private final Game game;
    private final Coordinate2D gridStart = new Coordinate2D(50, 100);
    private final Coordinate2D gridEnd = new Coordinate2D(750, 700);
    private final Grid grid = new Grid(gridStart, gridEnd);
    private ItemSpawner itemSpawner = new ItemSpawner();

    public GameScene(Game game) {
        this.game = game;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("background.png");
    }

    @Override
    public void setupEntities() {
        itemSpawner.setScene(this);
        itemSpawner.setGrid(grid);

        grid.draw(this);

        Snake snake = new Snake(new Coordinate2D(400, 300), new Size(50, 50), this, grid, 270, 1, "red", SnakeControls.WASD);
        Snake snake2 = new Snake(new Coordinate2D(400, 400), new Size(50, 50), this, grid, 90, 1, "blue", SnakeControls.ARROWS);

        ItemSpawner.spawnItemFromFactory(FoodFactory.getRandomFoodType());
    }

    public Coordinate2D getGridStart() {
        return gridStart;
    }

    public Coordinate2D getGridEnd() {
        return gridEnd;
    }

    public void introduceEntity(SpriteEntity entity) {
        addEntity(entity);
    }

    public void changeScene(int i) {
        this.game.setActiveScene(i);
    }



}
