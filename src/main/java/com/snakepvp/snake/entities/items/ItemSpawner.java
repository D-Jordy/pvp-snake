package com.snakepvp.snake.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.snakepvp.snake.scenes.GameScene;

import java.util.ArrayList;
import java.util.Random;

public class ItemSpawner {
    private GameScene scene;
    private ArrayList<Item> items = new ArrayList<>();

    public ItemSpawner(GameScene scene, ArrayList<Item> items) {
        this.scene = scene;
        this.items = items;
    }

    public void spawnItem() {
        spawnItem(scene, items);
    }

    public static void spawnItem(GameScene scene, ArrayList<Item> items) {
        Item item = items.get(
                new Random().nextInt(items.size())
        );

        item.setAnchorLocation(
                new Coordinate2D(
                        new Random().nextInt((int) scene.getWidth() - 100),
                        new Random().nextInt((int) scene.getHeight() - 100)
                )
        );

        scene.introduceEntity(item);
    }
}
