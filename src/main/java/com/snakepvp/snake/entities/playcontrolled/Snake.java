package com.snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.snakepvp.snake.scenes.GameScene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Snake extends DynamicSpriteEntity implements KeyListener, Collided, Collider {
    public ArrayList<SnakeBody> bodyParts = new ArrayList<>();
    private GameScene scene;
    private int direction = 0;
    private int requestedDirection = -1; //-1 means no request

    public Snake(String resource, Coordinate2D initialLocation, Size size, GameScene scene) {
        super(resource, initialLocation, size);
        this.scene = scene;
    }

    public void eat() {
//        bodyParts.add(new SnakeBody("snake.jpg", new Coordinate2D(-100, -100), new Size(100, 100)));
        this.scene.setupEntities();
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

        for (SnakeBody bodyPart : bodyParts) {
            bodyPart.setDirection(direction);
        }

        setSpeed(1);
    }

    @Override
    public void onCollision(List<Collider> list) {
    }

    public boolean isAlignedToGrid(){
        //checks if current location in scene is aligned to grid
        return ((getLocationInScene().getX() % 50) == 0) && ((getLocationInScene().getY() % 50) == 0);
    }

    private void changeSnakeDirection(){
        //if there is a requested direction, change direction
        if (requestedDirection != -1) {
            direction = requestedDirection;
            setDirection(requestedDirection);
            setRotate(direction);
            requestedDirection = -1;
        }
    }

    @Override
    public void checkForCollisions(List<Collider> colliders) {
        Collided.super.checkForCollisions(colliders);

        //check if snake is aligned to grid, then change snake direction
        if (isAlignedToGrid()){
            changeSnakeDirection();
        }
    }
}
