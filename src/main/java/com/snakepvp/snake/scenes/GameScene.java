package com.snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.snakepvp.snake.entities.playcontrolled.Snake;
import com.snakepvp.snake.entities.playcontrolled.SnakeBody;
import com.snakepvp.snake.scenes.grid.Grid;
import com.snakepvp.snake.scenes.grid.GridCell;

public class GameScene extends DynamicScene {
    @Override
    public void setupScene() {
    }

    @Override
    public void setupEntities() {
        (new Grid(10, 10, 100)).draw(this);

        Snake snake = new Snake("snake.jpg", new Coordinate2D(400, 300), new Size(100, 100), this);
        addEntity(snake);

        for (SnakeBody parts : snake.bodyParts) {
            addEntity(parts);
        }

    }

    public void introduceEntity(SpriteEntity entity) {
        addEntity(entity);
    }
}
