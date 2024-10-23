package snakepvp.snake.entities.menu;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import snakepvp.snake.Game;

public class Button extends TextEntity implements MouseEnterListener, MouseExitListener, MouseButtonPressedListener {
    private Color color;
    private Color hoverColor;
    private int onClickSceneId;
    private Game game;
    private boolean multiPlayer = false;

    public Button(Coordinate2D initialLocation, String text, Color color, Color hoverColor, Game game, int onClickSceneId) {
        super(initialLocation, text);
        this.color = color;
        this.hoverColor = hoverColor;
        this.game = game;
        this.onClickSceneId = onClickSceneId;

        setFill(color);
        setFont(Font.font("Roboto", FontWeight.BOLD, 20));
    }

    public Button(Coordinate2D initialLocation, String text, Color color, Color hoverColor, Game game, int onClickSceneId, boolean multiPlayer) {
        super(initialLocation, text);
        this.color = color;
        this.hoverColor = hoverColor;
        this.game = game;
        this.onClickSceneId = onClickSceneId;
        this.multiPlayer = multiPlayer;

        setFill(color);
        setFont(Font.font("Roboto", FontWeight.BOLD, 20));
    }

    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
        this.game.setMultiPlayer(this.multiPlayer);
        this.game.setActiveScene(this.onClickSceneId);
    }

    @Override
    public void onMouseEntered() {
        setFill(this.hoverColor);
        setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited() {
        setFill(this.color);
        setCursor(Cursor.DEFAULT);
    }
}
