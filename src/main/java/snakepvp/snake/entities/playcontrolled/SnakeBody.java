package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class SnakeBody extends SnakeBodyPart {

    public SnakeBody(String resource, Coordinate2D initialLocation, Size size, double direction, double speed) {
        super(resource, initialLocation, size, direction, speed);
    }
}
