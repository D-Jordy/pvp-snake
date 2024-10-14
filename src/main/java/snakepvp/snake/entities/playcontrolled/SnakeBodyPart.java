package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class SnakeBodyPart extends DynamicSpriteEntity
{
    protected double direction;

    protected SnakeBodyPart(String resource, Coordinate2D initialLocation, Size size, double direction, double speed) {
        super(resource, initialLocation, size);
        this.direction = direction;
        setDirection(direction);
        setRotate(direction);
        setSpeed(speed);
    }

    protected void changeDirection(int direction){
        this.direction = direction;
        setDirection(direction);
        changeSpriteOrientation(direction);
    };

    private void changeSpriteOrientation(int direction){
        setRotate(direction);
    }

    public boolean isAlignedToGrid(){
        //checks if current location in scene is aligned to grid
        return ((getLocationInScene().getX() % 50) == 0) && ((getLocationInScene().getY() % 50) == 0);
    }

    public Coordinate2D returnLocationInScene(){
        return getLocationInScene();
    }

}
