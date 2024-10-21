package snakepvp.snake.entities.menu;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;

public class Button extends TextEntity {
    public Button(Coordinate2D initialLocation, String text, Color color) {
        super(initialLocation, text);

        setFill(color);


    }
}
