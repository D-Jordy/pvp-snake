package com.snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.snakepvp.snake.entities.playcontrolled.Snake;
import com.snakepvp.snake.entities.playcontrolled.SnakeBody;

public class GameScene extends DynamicScene {
    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities() {
        Snake snake = new Snake("snake.jpg", new Coordinate2D(400, 300), new Size(100, 100), this);
        addEntity(snake);

        for (SnakeBody parts : snake.bodyParts) {
            addEntity(parts);
        }

    }
}
