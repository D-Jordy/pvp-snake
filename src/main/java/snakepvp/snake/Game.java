package snakepvp.snake;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import snakepvp.snake.scenes.GameOverScene;
import snakepvp.snake.scenes.GameScene;
import snakepvp.snake.scenes.TitleScene;

public class Game extends YaegerGame {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("SnakePvP");
        setSize(new Size(800, 800));
    }

    @Override
    public void setupScenes() {
        addScene(0, new TitleScene(this));
        addScene(1, new GameScene(this));
        addScene(2, new GameOverScene());
    }
}
