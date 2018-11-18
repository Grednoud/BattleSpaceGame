package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;

public class HealthLine extends BaseSprite {

    private MainShip ship;
    private float fullWidth;
    private float fixLeft;
    private BaseRectangle worldBounds;

    public HealthLine(TextureAtlas atlas, MainShip ship) {
        super(atlas.findRegion("health"));
        this.ship = ship;
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setHeightProportion(0.02f);
        setBottom(worldBounds.getBottom()+ 0.01f);
        width = worldBounds.width - 0.02f;
        fullWidth = width;
        fixLeft = worldBounds.getLeft() + 0.01f;
        setLeft(fixLeft);

    }

    @Override
    public void update(float delta) {
        width = fullWidth * ship.getHp() / 100f;
        setLeft(fixLeft);
    }

}
