package snakepvp.snake.scenes.grid;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import snakepvp.snake.scenes.GameScene;

public class GridCell extends SpriteEntity implements GridComponent, Collider {
    private double y;
    private double x;
    private int cellNumber;

    public GridCell(double x, double y, int cellNumber) {
        super("gridcel.png", new Coordinate2D(x, y), new Size(50,50));
        this.y = y;
        this.x = x;
        this.cellNumber = cellNumber;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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
