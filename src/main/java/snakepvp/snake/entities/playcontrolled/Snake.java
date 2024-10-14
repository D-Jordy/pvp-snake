package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import snakepvp.snake.entities.items.Item;
import snakepvp.snake.scenes.GameScene;
import javafx.scene.input.KeyCode;
import snakepvp.snake.scenes.grid.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Snake extends DynamicSpriteEntity implements KeyListener, Collider, Collided {
    private GameScene scene;
    private double direction = 0;
    private int requestedDirection = -1; //-1 means no request
    private Grid grid;
    private ArrayList<SnakeBodyPart> bodyParts = new ArrayList<>();
    private ArrayList<SnakeBendPoint> bendPoints = new ArrayList<>();

    public Snake(Coordinate2D headLocation, Size size, GameScene scene, Grid grid, double startDirection, double defaultSpeed) {
        //create entity
        super("red-snake.png", headLocation, size);

        //set starting direction and speed. And add scene and grid
        this.direction = startDirection;
        setSpeed(defaultSpeed);
        setDirection(this.direction);
        setRotate(this.direction);
        this.scene = scene;
        this.grid = grid;

        //add head to scene
        this.scene.introduceEntity(this);

        //spawn tail
        bodyParts.add(spawnSnakeTail(getTailSpawnLocation(headLocation), size, scene, startDirection, defaultSpeed));

    }

    private Coordinate2D getTailSpawnLocation(Coordinate2D headLocation) {
        Coordinate2D tailLocation = headLocation;
        if (direction == 0){
            tailLocation = new Coordinate2D(headLocation.getX(), headLocation.getY()-50);
        } else if (direction == 90){
            tailLocation = new Coordinate2D(headLocation.getX()-50, headLocation.getY());
        } else if (direction == 180){
            tailLocation = new Coordinate2D(headLocation.getX(), headLocation.getY()+50);
        } else if (direction == 270){
            tailLocation = new Coordinate2D(headLocation.getX()+50, headLocation.getY());
        }

        return tailLocation;
    }

    private SnakeBodyPart spawnSnakeTail(Coordinate2D initialLocation, Size size, GameScene scene, double direction, double speed){
        SnakeBodyPart tail = new SnakeTail("snakeTail.png", initialLocation, size , direction, speed);
        scene.introduceEntity(tail);
        return tail;
    }

    public void eat() {
//        bodyParts.add(new SnakeBody("snakeBody.png", new Coordinate2D(-100, -100), new Size(100, 100)));
        this.scene.setupEntities();
    }

    private void changeSnakeDirection(){
        //if there is a requested direction, change direction
        if (requestedDirection != -1) {
            setDirection(requestedDirection);
            setRotate(requestedDirection);
            bendPoints.add(new SnakeBendPoint(getLocationInScene(), requestedDirection));
            requestedDirection = -1;
        }
    }

    public void moveBodyPartWhenOverBendpoint(){
        //bendpoint index that can be deleted if tail is over it
        int bendPointIndexToDelete = -1;

        //loop through all bodyparts
        for (SnakeBodyPart bodyPart : bodyParts) {

            //loop through all bendpoints
            for (SnakeBendPoint bendPoint : bendPoints){
                //check if body part is over bendpoint
                if (bodyPart.returnLocationInScene().getX() == bendPoint.getX() && bodyPart.returnLocationInScene().getY() == bendPoint.getY()){
                    //change body part direction
                    bodyPart.changeDirection(bendPoint.getDirection());

                    //checks if tail is over bendpoint
                    if (bodyPart instanceof SnakeTail){
                        bendPointIndexToDelete = bendPoints.indexOf(bendPoint);
                    }
                }
            }
        }

        //if bendpoint index is set, delete bendpoint
        if (bendPointIndexToDelete >= 0){
            bendPoints.remove(bendPointIndexToDelete);
        }

    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(KeyCode.W)) {
            requestedDirection = 180;
        }
        if (pressedKeys.contains(KeyCode.D)) {
            requestedDirection = 90;
        }

        if (pressedKeys.contains(KeyCode.S)) {
            requestedDirection = 0;
        }

        if (pressedKeys.contains(KeyCode.A)) {
            requestedDirection = 270;
        }
        if (pressedKeys.contains(KeyCode.SPACE)) {
//            eat();
        }
    }

    @Override
    public void checkForCollisions(List<Collider> colliders) {
        Collided.super.checkForCollisions(colliders);

        //check if any bodypart is over bendpoint, and change directiob
        moveBodyPartWhenOverBendpoint();

        //check if head is aligned to grid, then change head direction
        if (isAlignedToGrid()){
            changeSnakeDirection();
        }
    }

    public boolean isAlignedToGrid(){
        //checks if current location in scene is aligned to grid
        return ((getLocationInScene().getX() % 50) == 0) && ((getLocationInScene().getY() % 50) == 0);
    }

    @Override
    public void onCollision(List<Collider> list) {
    }
}
