package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
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
import java.util.Set;

public class Snake extends DynamicSpriteEntity implements KeyListener, Collider, Collided, UpdateExposer {
    private GameScene scene;
    private double direction = 0;
    private int requestedDirection = -1; //-1 means no request
    private Grid grid;
    private ArrayList<SnakeBodyPart> bodyParts = new ArrayList<>();
    private ArrayList<SnakeBendPoint> bendPoints = new ArrayList<>();
    private int bodyPartsToSpawn = 0;
    private String color;

    public Snake(Coordinate2D headLocation, Size size, GameScene scene, Grid grid, double startDirection, double defaultSpeed, String color) {
        //create entity
        super(color + "-snake.png", headLocation, size);

        this.color = color;
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
        double x = headLocation.getX();
        double y = headLocation.getY();
        switch ((int) direction) {
            case 0: return new Coordinate2D(x, y - 50);
            case 90: return new Coordinate2D(x - 50, y);
            case 180: return new Coordinate2D(x, y + 50);
            case 270: return new Coordinate2D(x + 50, y);
            default: return headLocation;
        }
    }

    private SnakeBodyPart spawnSnakeTail(Coordinate2D initialLocation, Size size, GameScene scene, double direction, double speed) {
        SnakeBodyPart tail = new SnakeTail(color + "-snake-tail.png", initialLocation, size, direction, speed);
        scene.introduceEntity(tail);
        return tail;
    }

    public void eat() {
        addBodyPart();
    }

    private void addBodyPart(){
        SnakeTail tail = getSnakeTail();
        Coordinate2D tailLocation = tail.returnLocationInScene();
        SnakeBodyPart newBodypart = new SnakeBody(color+ "-snake-body.png", tailLocation, new Size(50,50), tail.getDirection(), 1);
        double alternativeDirection = checkIfSpawnedOnBendpoint(tailLocation);
        if (alternativeDirection != -1) {
            newBodypart.changeDirection(alternativeDirection);
        }
        
        bodyParts.add(newBodypart);
        bodyPartsToSpawn += 1;
        tail.pauseTail();
        scene.introduceEntity(newBodypart);
    }

    private double checkIfSpawnedOnBendpoint(Coordinate2D spawnLocation){
        for (SnakeBendPoint snakeBendPoint : bendPoints) {
            if (snakeBendPoint.getX() == spawnLocation.getX() && snakeBendPoint.getY() == spawnLocation.getY()) {
                return snakeBendPoint.getDirection();
            }
        }
        return -1.0;
    }

    private SnakeTail getSnakeTail() {
        for (SnakeBodyPart bodyPart : bodyParts) {
            if (bodyPart instanceof SnakeTail) {
                return (SnakeTail) bodyPart;
            }
        }
        return null;
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
    }

    @Override
    public void checkForCollisions(List<Collider> colliders) {
        Collided.super.checkForCollisions(colliders);
    }

    @Override
    public void explicitUpdate(long timestamp) {
        if (!this.isInsideGrid()) {
            this.scene.changeScene(1);
        }

        //check if any bodypart is over bendpoint, and change directiob
        moveBodyPartWhenOverBendpoint();

        //check if head is aligned to grid
        if (isAlignedToGrid()){
            //change snake head direction
            changeSnakeDirection();
            if (bodyPartsToSpawn > 0){
                bodyPartsToSpawn--;
            }
        }

        if (bodyPartsToSpawn == 0){
            getSnakeTail().continueTail();
        }

    }

    public boolean isInsideGrid() {
        return getLocationInScene().getX() >= this.scene.getGridStart().getX() && getLocationInScene().getX() <= this.scene.getGridEnd().getX() && getLocationInScene().getY() >= this.scene.getGridStart().getY() && getLocationInScene().getY() <= this.scene.getGridEnd().getY();
    }

    public boolean isAlignedToGrid() {
        //checks if current location in scene is aligned to grid
        return ((getLocationInScene().getX() % 50) == 0) && ((getLocationInScene().getY() % 50) == 0);
    }

    @Override
    public void onCollision(List<Collider> list) {
        for (Collider collider : list) {

            if (collider instanceof Item) {
                ((Item) collider).handleCollision(this);
            }
        }
    }
}
