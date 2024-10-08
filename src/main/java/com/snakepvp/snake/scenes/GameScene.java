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
    @Override
    public void setupScene() {
    }

    @Override
    public void setupEntities() {
        (new Grid(new Coordinate2D(0,0), new Coordinate2D(500,500))).draw(this);

        Snake snake = new Snake("snake.jpg", new Coordinate2D(400, 300), new Size(100, 100), this);
        addEntity(snake);

        for (SnakeBody parts : snake.bodyParts) {
            addEntity(parts);
        }

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Apple(new Coordinate2D(100, 100)));

        ItemSpawner itemSpawner = new ItemSpawner(this, items);
        itemSpawner.spawnItem();
    }

    public void introduceEntity(SpriteEntity entity) {
        addEntity(entity);
    }

    public void introduceEntity(SpriteEntity entity) {
        addEntity(entity);
    }
}
