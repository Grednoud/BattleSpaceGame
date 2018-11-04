package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;

public class Bullet extends BaseSprite {

    private BaseRectangle worldBouns;
    private Vector2 v = new Vector2();

    private int damage;
    private Object owner;
    
    public Bullet() {
        regions = new TextureRegion[1];
    }

    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            BaseRectangle worldBounds,
            int damage) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBouns = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBouns)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

}
