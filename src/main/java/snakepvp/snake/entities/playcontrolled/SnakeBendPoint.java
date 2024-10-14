package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;

public class SnakeBendPoint {
    private Coordinate2D location;
    private int direction;

    public SnakeBendPoint(Coordinate2D location, int direction) {
        this.location = location;
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public Coordinate2D getLocation() {
        return location;
    }

    public double getX(){
        return location.getX();
    }
    public double getY(){
        return location.getY();
    }

}
