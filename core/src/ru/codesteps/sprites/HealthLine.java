package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;

public class HealthLine extends BaseSprite {

    private MainShip ship;
    private float fullWidth;
    private float fixLeftMargin;
    private BaseRectangle worldBounds;

    public HealthLine(TextureAtlas atlas, MainShip ship) {
        super(atlas.findRegion("health"));
        this.ship = ship;
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setHeightProportion(0.02f);
        setBottom(worldBounds.getBottom() + 0.01f);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        fullWidth = worldBounds.width - 0.02f - fixLeftMargin;
        width = fullWidth * ship.getHp() / 100f;
        setLeft(worldBounds.getLeft() + fixLeftMargin);
    }

    public void setFixedLeftMargin(float fixLeftMargin) {
        this.fixLeftMargin = fixLeftMargin;
        
    }

}
