package com.snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.snakepvp.snake.entities.playcontrolled.Snake;

public abstract class Item extends DynamicSpriteEntity implements Collider {
    public Item(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation, new Size(50, 50));
    }

    public void handleCollision(Collided collider) {
        if (collider instanceof Snake && ((Snake) collider).getAnchorLocation().equals(this.getAnchorLocation())) {
            handleSnakeCollision((Snake) collider);
        }
    }

    public abstract void handleSnakeCollision(Snake snake);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
