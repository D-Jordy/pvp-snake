package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public class SnakeBody extends DynamicSpriteEntity {

    public SnakeBody(String resource, Coordinate2D initialLocation, Size size) {
        super(resource, initialLocation, size);
    }
}
