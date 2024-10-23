package snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;
import snakepvp.snake.Game;
import snakepvp.snake.entities.menu.Button;

import java.util.ArrayList;

public class TitleScene extends StaticScene {
    private final ArrayList<Button> buttons = new ArrayList<>();

    public TitleScene(Game game) {
        this.buttons.add(new Button(new Coordinate2D(335, 620), "Single", Color.DARKBLUE, Color.BLUE, game, 1));
        this.buttons.add(new Button(new Coordinate2D(430, 620), "Multi", Color.RED, Color.DARKRED, game, 1, true));
    }

    @Override
    public void setupScene() {
        setBackgroundImage("titlescreen.png");
    }

    @Override
    public void setupEntities() {
        for (Button button : buttons) {
            addEntity(button);
        }
    }
}
