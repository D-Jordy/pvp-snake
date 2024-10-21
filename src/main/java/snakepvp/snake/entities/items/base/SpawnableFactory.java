package snakepvp.snake.entities.items.base;

import com.github.hanyaeger.api.Coordinate2D;

public interface SpawnableFactory {
    public Item createInstance(Coordinate2D location);
}
