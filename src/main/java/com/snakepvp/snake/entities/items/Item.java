package com.snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Item extends DynamicSpriteEntity {
    public Item(String resource, Coordinate2D initialLocation, Size size) {
        super(resource, initialLocation, size);
    }

    public abstract void handleCollision();
}
