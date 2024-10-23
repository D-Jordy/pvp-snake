package snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.entities.items.base.Item;
import snakepvp.snake.entities.playcontrolled.Snake;

public abstract class Powerup extends Item {
    protected Powerup(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }

    @Override
    public void handleSnakeCollision(Snake snake) {
        System.out.println("Snake got a " + getClass().getSimpleName());
        snake.eat();
    }
}
