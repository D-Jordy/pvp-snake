package com.snakepvp.snake.scenes.grid;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.snakepvp.snake.scenes.GameScene;

public class GridCell extends SpriteEntity implements GridComponent {
    private int y;
    private int x;
    private int cellNumber;

    public GridCell(int x, int y, int cellNumber) {
        super("gridcel.png", new Coordinate2D(x, y));
        this.y = y;
        this.x = x;
        this.cellNumber = cellNumber;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "GridCell{" +
                "y=" + y +
                ", cellNumber=" + cellNumber +
                '}';
    }

    public void draw(GameScene scene) {
        scene.introduceEntity(this);
    }
}
