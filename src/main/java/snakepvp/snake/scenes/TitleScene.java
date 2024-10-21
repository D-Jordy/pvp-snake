package snakepvp.snake.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;
import snakepvp.snake.entities.menu.Button;

public class TitleScene extends StaticScene {
    @Override
    public void setupScene() {
        setBackgroundImage("titlescreen.png");
    }

    @Override
    public void setupEntities() {
        addEntity(new Button(new Coordinate2D(350, 625), "Play", Color.PURPLE));
    }
}
