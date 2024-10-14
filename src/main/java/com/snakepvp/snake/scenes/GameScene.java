package com.snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.snakepvp.snake.entities.items.Item;
import com.snakepvp.snake.entities.items.ItemSpawner;
import com.snakepvp.snake.entities.items.food.Apple;
import com.snakepvp.snake.entities.playcontrolled.Snake;
import com.snakepvp.snake.entities.playcontrolled.SnakeBody;
import com.snakepvp.snake.scenes.grid.Grid;

import java.util.ArrayList;

public class GameScene extends DynamicScene {
    private Coordinate2D gridStart = new Coordinate2D(0, 0);
    private Coordinate2D gridEnd = new Coordinate2D(500, 500);

    @Override
    public void setupScene() {
    }

    @Override
    public void setupEntities() {
        Grid grid = new Grid(gridStart, gridEnd);
        grid.draw(this);

        Snake snake = new Snake(new Coordinate2D(400, 300), new Size(50, 50), this, grid, 270 , 1);

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
