package com.snakepvp.snake.entities.items.food;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.snakepvp.snake.entities.items.Item;

public abstract class Food extends Item {
    protected Food(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation, new Size(50, 50));
    }

    public void handleCollision() {
        //snake grows
    }
}
