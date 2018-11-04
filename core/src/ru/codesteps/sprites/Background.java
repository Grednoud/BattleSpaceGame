package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;

public class Background extends BaseSprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setHeightProportion(worldBounds.height);
        pos.set(worldBounds.pos);
    } 
    
}
