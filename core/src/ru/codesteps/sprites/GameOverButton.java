package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.ActionListener;
import ru.codesteps.base.BaseButton;
import ru.codesteps.base.BaseRectangle;

public class GameOverButton extends BaseButton {

    private static final float GAME_OVER_POS_Y = 0.5f;

    private final Vector2 v = new Vector2(0, -0.3f);

    public GameOverButton(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("game-over"), actionListener);
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setHeightProportion(0.15f);
        setBottom(worldBounds.getTop());
        clickArea.pos = worldBounds.pos;
        clickArea.width = worldBounds.width;
        clickArea.height = worldBounds.height;
    }

    @Override
    public void update(float delta) {
        if (pos.y > GAME_OVER_POS_Y) {
            super.update(delta);
            pos.mulAdd(v, delta);
        }
    }

}
