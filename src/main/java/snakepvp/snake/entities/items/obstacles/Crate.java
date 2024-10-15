package snakepvp.snake.entities.items.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.entities.items.Obstacle;

public class Crate extends Obstacle {
    public Crate(Coordinate2D initialLocation) {
        super("crate.png", initialLocation);
    }
}
