package snakepvp.snake.entities.items.food;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.entities.items.Item;

public abstract class Food extends Item {
    protected Food(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }

    public void handleCollision() {
        //snake grows
    }
}
