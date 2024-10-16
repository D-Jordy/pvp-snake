package snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import snakepvp.snake.entities.items.Item;
import snakepvp.snake.scenes.GameScene;
import snakepvp.snake.scenes.grid.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Snake extends DynamicSpriteEntity implements KeyListener, Collider, Collided, UpdateExposer {
    private final GameScene scene;
    private final Grid grid;
    private final String color;
    private SnakeControls controls;
    private final double defaultSpeed;
    private final ArrayList<SnakeBodyPart> bodyParts = new ArrayList<>();
    private int bodyPartsToSpawn = 0;
    private final ArrayList<SnakeBendPoint> bendPoints = new ArrayList<>();
    private double direction = 0;
    private int requestedDirection = -1; //-1 means no request

    public Snake(Coordinate2D headLocation, Size size, GameScene scene, Grid grid, double startDirection, double defaultSpeed, String color, SnakeControls controls) {
        super(color + "-snake.png", headLocation, size);

        this.color = color;
        this.direction = startDirection;
        this.defaultSpeed = defaultSpeed;
        this.scene = scene;
        this.grid = grid;
        this.controls = controls;

        //Set speed, direction and rotation
        setSpeed(this.defaultSpeed);
        setDirection(this.direction);
        setRotate(this.direction);

        //Add head and tail
        this.scene.introduceEntity(this);
        bodyParts.add(spawnSnakeTail(getTailSpawnLocation(headLocation), size, scene, startDirection, defaultSpeed));
    }

    private SnakeBodyPart spawnSnakeTail(Coordinate2D initialLocation, Size size, GameScene scene, double direction, double speed) {
        SnakeBodyPart tail = new SnakeTail(color + "-snake-tail.png", initialLocation, size, direction, speed);
        scene.introduceEntity(tail);
        return tail;
    }

    private Coordinate2D getTailSpawnLocation(Coordinate2D headLocation) {
        double x = headLocation.getX();
        double y = headLocation.getY();
        switch ((int) direction) {
            case 0:
                return new Coordinate2D(x, y - 50);
            case 90:
                return new Coordinate2D(x - 50, y);
            case 180:
                return new Coordinate2D(x, y + 50);
            case 270:
                return new Coordinate2D(x + 50, y);
            default:
                return headLocation;
        }
    }

    public void eat() {
        addBodyPart();
    }

    private void addBodyPart() {
        //get tail location to spawn bodypart in correct position
        SnakeTail tail = getSnakeTail();
        Coordinate2D tailLocation = tail.returnLocationInScene();

        //create new snake
        SnakeBodyPart newBodypart = new SnakeBody(color + "-snake-body.png", tailLocation, new Size(50, 50), tail.getDirection(), this.defaultSpeed);

        bodyParts.add(newBodypart);
        //adds the number of body parts to spawn, this is used to stop the tail
        bodyPartsToSpawn += 1;

        //pause tail movement
        tail.pauseTail();

        //add entity to scene
        scene.introduceEntity(newBodypart);
    }

    private SnakeTail getSnakeTail() {
        for (SnakeBodyPart bodyPart : bodyParts) {
            //checks if bodypart is SnakeTail
            if (bodyPart instanceof SnakeTail) {
                return (SnakeTail) bodyPart;
            }
        }
        return null;
    }

    private void changeSnakeDirection() {
        //if there is a requested direction, change direction
        if (requestedDirection != -1) {
            setDirection(requestedDirection);
            setRotate(requestedDirection);
            //add bendpoint, so bodyparts know when to turn
            bendPoints.add(new SnakeBendPoint(getLocationInScene(), requestedDirection));

            //set requestedDirection to -1 to tell code that there is no requested direction
            requestedDirection = -1;
        }
    }

    public void moveBodyPartWhenOverBendpoint() {
        //bendpoint index that can be deleted if tail is over it
        int bendPointIndexToDelete = -1;

        //loop through all bodyparts
        for (SnakeBodyPart bodyPart : bodyParts) {

            //loop through all bendpoints
            for (SnakeBendPoint bendPoint : bendPoints) {
                //check if body part is over bendpoint
                if (bodyPart.returnLocationInScene().getX() == bendPoint.getX() && bodyPart.returnLocationInScene().getY() == bendPoint.getY()) {
                    //change body part direction
                    bodyPart.changeDirection(bendPoint.getDirection());

                    //checks if tail is over bendpoint
                    if (bodyPart instanceof SnakeTail) {
                        bendPointIndexToDelete = bendPoints.indexOf(bendPoint);
                    }
                }
            }
        }

        //if bendpoint index is set, delete bendpoint
        if (bendPointIndexToDelete >= 0) {
            bendPoints.remove(bendPointIndexToDelete);
        }

    }

    /**
     * This method is called when the state of the pressed keys changes. It is called by the {@link com.github.hanyaeger.api.userinput.KeyListener} interface.
     *
     * @param pressedKeys A {@link Set} of {@code KeyCode} representations of the keys that are currently pressed
     */
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(controls.getKeyCodes()[0]) && this.getDirection() != 0) {
            //up
            requestedDirection = 180;
        }
        if (pressedKeys.contains(controls.getKeyCodes()[1]) && this.getDirection() != 180) {
            //down
            requestedDirection = 0;
        }

        if (pressedKeys.contains(controls.getKeyCodes()[2]) && this.getDirection() != 90) {
            //left
            requestedDirection = 270;
        }

        if (pressedKeys.contains(controls.getKeyCodes()[3]) && this.getDirection() != 270) {
            //right
            requestedDirection = 90;
        }
    }

    /**
     * This method is called when the {@link Collider} should check for collisions with other {@link Collider}s.
     *
     * @param colliders a {@link Set} of colliders that should be checked for collisions
     */
    @Override
    public void checkForCollisions(List<Collider> colliders) {
        Collided.super.checkForCollisions(colliders);
    }

    @Override
    public void explicitUpdate(long timestamp) {
        //checks if snake is not in grid anymore, then changes scene
        if (!this.grid.isEntityOnGrid(this)) {
            this.scene.changeScene(1);
        }

        //check if any bodypart is over bendpoint, and change direction
        moveBodyPartWhenOverBendpoint();

        //check if head is aligned to grid
        if (this.grid.isEntityAlignedToGrid(this)) {
            //change snake head direction
            changeSnakeDirection();
            if (bodyPartsToSpawn > 0) {
                bodyPartsToSpawn--;
            }
        }

        if (bodyPartsToSpawn == 0) {
            getSnakeTail().continueTail();
        }

    }

    @Override
    public void onCollision(List<Collider> list) {
        for (Collider collider : list) {

            if (collider instanceof Item) {
                ((Item) collider).handleCollision(this);
            }

            if (collider instanceof SnakeBodyPart) {
                if (((SnakeBodyPart) collider).getAnchorLocation().distance(this.getAnchorLocation()) < 5) {
                    this.scene.changeScene(1);
                }
            }
        }
    }
}
