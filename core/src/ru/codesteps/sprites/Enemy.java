package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;
import ru.codesteps.math.RandomUtil;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.utils.RegionsUtil;

public class Enemy extends BaseSprite {

    private Vector2 v = new Vector2();

    private BaseRectangle worldBounds;

    public Enemy() {
    }

    public void set(
            TextureAtlas atlas,
            Vector2 v0,
            float height, 
            BaseRectangle worldBounds) {
        regions = RegionsUtil.split(atlas.findRegion("enemy1.0"), 1, 1, 1);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getTop());
        pos.x = RandomUtil.nextFloat(worldBounds.getLeft()+width/2, worldBounds.getRight()-width/2);
        v.set(v0);
        angle = 180;
        setHeightProportion(height);
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

}
