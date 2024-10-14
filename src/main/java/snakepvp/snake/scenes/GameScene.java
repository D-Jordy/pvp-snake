package snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import snakepvp.snake.Game;
import snakepvp.snake.entities.items.Item;
import snakepvp.snake.entities.items.ItemSpawner;
import snakepvp.snake.entities.items.food.Apple;
import snakepvp.snake.entities.playcontrolled.Snake;
import snakepvp.snake.entities.playcontrolled.SnakeBody;
import snakepvp.snake.scenes.grid.Grid;

import java.util.ArrayList;

public class GameScene extends DynamicScene {
    private final Game game;
    private Coordinate2D gridStart = new Coordinate2D(0, 50);
    private Coordinate2D gridEnd = new Coordinate2D(700, 650);

    public GameScene(Game game) {
        this.game = game;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("background.png");
    }

    @Override
    public void setupEntities() {
        Grid grid = new Grid(gridStart, gridEnd);
        grid.draw(this);

        Snake snake = new Snake(new Coordinate2D(400, 300), new Size(50, 50), this, grid, 270 , 1, "red");

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Apple(new Coordinate2D(100, 100)));

        ItemSpawner itemSpawner = new ItemSpawner(this, items);
        itemSpawner.spawnItem();

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
