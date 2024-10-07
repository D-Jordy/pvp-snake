package com.snakepvp.snake.entities.playcontrolled;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.snakepvp.snake.scenes.GameScene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;

public class Snake extends DynamicSpriteEntity implements KeyListener {
    public ArrayList<SnakeBody> bodyParts = new ArrayList<>();
    private GameScene scene;

    public Snake(String resource, Coordinate2D initialLocation, Size size, GameScene scene) {
        super(resource, initialLocation, size);
        this.scene = scene;
    }

    public void eat() {
        bodyParts.add(new SnakeBody("snake.jpg", new Coordinate2D(-100, -100), new Size(100, 100)));
        this.scene.setupEntities();
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        int direction = 0;

        if (pressedKeys.contains(KeyCode.W)) {
            direction = 180;
        }

        if (pressedKeys.contains(KeyCode.D)) {
            direction = 90;
        }

        if (pressedKeys.contains(KeyCode.S)) {
            direction = 0;
        }

        if (pressedKeys.contains(KeyCode.A)) {
            direction = 270;
        }

        if (pressedKeys.contains(KeyCode.SPACE)) {
            eat();
        }

        setDirection(direction);

        for (SnakeBody bodyPart : bodyParts) {
            bodyPart.setDirection(direction);
        }

        setSpeed(5);
    }
}
