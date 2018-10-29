package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;

public class Background extends BaseSprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setHeightProportion(worldBounds.height);
        pos.set(worldBounds.pos);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }
    
    
    
}
