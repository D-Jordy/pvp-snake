package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class SnakeTail extends SnakeBodyPart {
    public SnakeTail(String resource, Coordinate2D initialLocation, Size size, double direction, double speed) {
        super(resource, initialLocation, size, direction, speed);
        setDirection(direction);
        setRotate(direction);
        setSpeed(1);
    }

    public double getDirection(){
        return direction;
    }

    public void pauseTail(){
        setSpeed(0);
    }
    public void continueTail(){
        setSpeed(1);
    }

}
