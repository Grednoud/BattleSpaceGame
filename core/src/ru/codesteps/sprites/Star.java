package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;
import ru.codesteps.math.RandomUtil;

public class Star extends BaseSprite {

    private Vector2 v;
    private BaseRectangle worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(0.005f);
        v = new Vector2();
        v.set(RandomUtil.nextFloat(-0.005f, 0.005f), RandomUtil.nextFloat(-0.5f, -0.1f));
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        this.worldBounds = worldBounds;
        float posX = RandomUtil.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = RandomUtil.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
    }

    private void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
    }

}
