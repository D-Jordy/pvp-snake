package snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import snakepvp.snake.entities.items.Item;
import snakepvp.snake.entities.items.ItemSpawner;
import snakepvp.snake.entities.items.food.Apple;
import snakepvp.snake.entities.playcontrolled.Snake;
import snakepvp.snake.entities.playcontrolled.SnakeBody;
import snakepvp.snake.scenes.grid.Grid;

import java.util.ArrayList;

public class GameScene extends DynamicScene {
    private Coordinate2D gridStart = new Coordinate2D(0, 50);
    private Coordinate2D gridEnd = new Coordinate2D(700, 650);

    @Override
    public void setupScene() {
        setBackgroundImage("background.png");
    }

    @Override
    public void setupEntities() {
        (new Grid(gridStart, gridEnd)).draw(this);

        Snake snake = new Snake("snake.jpg", new Coordinate2D(400, 300), new Size(50, 50), this);
        addEntity(snake);

        for (SnakeBody parts : snake.bodyParts) {
            addEntity(parts);
        }

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
}
