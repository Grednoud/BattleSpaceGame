package ru.codesteps.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseButton extends BaseSprite {

    protected BaseRectangle clickArea;

    private static final float PRESSED_SCALE = 0.95f;

    private int pointer;
    protected boolean isPressed;
    private ActionListener actionListener;

    public BaseButton(TextureRegion region, ActionListener actionListener) {
        super(region);
        this.actionListener = actionListener;
        setHeightProportion(0.2f);
        clickArea = new BaseRectangle(pos.x, pos.y, width, height);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isPressed || !isInside(touch)) {
            return false;
        }
        this.pointer = pointer;
        scale = PRESSED_SCALE;
        this.isPressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !isPressed) {
            return false;
        }
        if (isInside(touch)) {
            actionListener.actionPerformed(this);
        }
        isPressed = false;
        scale = 1f;
        return false;
    }

    @Override
    public boolean isInside(Vector2 touch) {
        return touch.x >= clickArea.getLeft() && touch.x <= clickArea.getRight()
                && touch.y >= clickArea.getBottom() && touch.y <= clickArea.getTop();
    }

}
