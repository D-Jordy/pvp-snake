package snakepvp.snake;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import snakepvp.snake.scenes.GameOverScene;
import snakepvp.snake.scenes.GameScene;

public class Game extends YaegerGame {
    // poep
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
        addScene(0, new GameScene(this));
        addScene(1, new GameOverScene());
    }
}
