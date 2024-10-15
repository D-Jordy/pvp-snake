package snakepvp.snake.entities.items.powerups;

import com.github.hanyaeger.api.Coordinate2D;
import snakepvp.snake.entities.items.Powerup;

public class Star extends Powerup {
    public Star(Coordinate2D initialLocation) {
        super("star.gif", initialLocation);
    }
}
