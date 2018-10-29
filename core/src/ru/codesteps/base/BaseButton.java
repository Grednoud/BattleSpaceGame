package ru.codesteps.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.BattleSpaceGame;

public abstract class BaseButton extends BaseSprite {

    public BaseButton(TextureRegion... regions) {
        super(regions);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        scale = 0.95f;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        scale = 1f;
        action();
        return false;
    }
    
    public abstract void action();
    
}
