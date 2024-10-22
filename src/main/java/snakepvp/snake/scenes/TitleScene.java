package snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;
import snakepvp.snake.Game;
import snakepvp.snake.entities.menu.Button;

public class TitleScene extends StaticScene {
    private Button playButton;

    public TitleScene(Game game) {
        this.playButton = new Button(new Coordinate2D(345, 620), "Play", Color.DARKBLUE, Color.BLUE, game, 1);
    }

    @Override
    public void setupScene() {
        setBackgroundImage("titlescreen.png");
    }

    @Override
    public void setupEntities() {
        addEntity(playButton);
    }
}
