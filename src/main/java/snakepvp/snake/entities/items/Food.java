package snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.entities.playcontrolled.Snake;

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
