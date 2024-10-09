package com.snakepvp.snake.entities.items.food;

import com.github.hanyaeger.api.Coordinate2D;
import com.snakepvp.snake.entities.items.Item;
import com.snakepvp.snake.entities.playcontrolled.Snake;

public abstract class Food extends Item {
    protected Food(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }

    @Override
    public void handleSnakeCollision(Snake snake) {
        System.out.println("Snake ate " + getClass().getSimpleName());
        snake.eat();
    }
}
