package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class SnakeBodyPart extends DynamicSpriteEntity implements Collider {
    protected double direction;

    protected SnakeBodyPart(String resource, Coordinate2D initialLocation, Size size, double direction, double speed) {
        super(resource, initialLocation, size);
        this.direction = direction;
        setDirection(direction);
        setRotate(direction);
        setSpeed(speed);
    }

    public void changeDirection(double direction) {
        this.direction = direction;
        setDirection(direction);
        setRotate(direction);
    }

    private void changeSpriteOrientation(int direction) {
        setRotate(direction);
    }

    public boolean isAlignedToGrid() {
        //checks if current location in scene is aligned to grid
        return ((getLocationInScene().getX() % 50) == 0) && ((getLocationInScene().getY() % 50) == 0);
    }

    public Coordinate2D returnLocationInScene() {
        return getLocationInScene();
    }

    public void changeSpeed(double speed) {
        setSpeed(speed);
    }

    public void handleCollision(Snake snake) {
        if (this.getLocationInScene().distance(snake.getAnchorLocation()) < 5) {

        }
    }
}
