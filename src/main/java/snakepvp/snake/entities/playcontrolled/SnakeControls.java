package snakepvp.snake.entities.playcontrolled;

import javafx.scene.input.KeyCode;

/**
 * Enum for the different control schemes for Snakes
 */
public enum SnakeControls {
    ARROWS, WASD, IJKL, NUMPAD;

    /**
     * @return KeyCode[ ] with the KeyCodes for the control scheme
     */
    public KeyCode[] getKeyCodes() {
        switch (this) {
            case ARROWS:
                return new KeyCode[]{KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT};
            case WASD:
                return new KeyCode[]{KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D};
            case IJKL:
                return new KeyCode[]{KeyCode.I, KeyCode.K, KeyCode.J, KeyCode.L};
            case NUMPAD:
                return new KeyCode[]{KeyCode.NUMPAD8, KeyCode.NUMPAD2, KeyCode.NUMPAD4, KeyCode.NUMPAD6};
            default:
                return new KeyCode[]{KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT};
        }
    }
}
