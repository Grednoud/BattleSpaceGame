package ru.codesteps.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;

import ru.codesteps.base.Ship;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.pools.ExplosionPool;

public class Enemy extends Ship {

    enum State {
        FIGHT,
        INITIALIZATION
    }

    private Vector2 v0 = new Vector2();
    private Vector2 initializationV = new Vector2(0, -0.3f);

    private State state;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, BaseRectangle worldBounds, Sound shootSound) {
        super(shootSound);

        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);

        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            shoot();
            reloadTimer = 0f;
        }
        switch (state) {
            case INITIALIZATION:
                if (getTop() <= worldBounds.getTop()) {
                    v.set(v0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:

                if (getTop() <= worldBounds.getBottom()) {
                    destroy();
                }
                break;
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        this.v.set(initializationV);
        setAngle(180);
        setHeightProportion(height);
        state = State.INITIALIZATION;
    }

    public boolean isDamaged(Bullet bullet) {
        if (!(bullet.getLeft() < getLeft() || bullet.getRight() > getRight()
                || bullet.getTop() < pos.y || bullet.getBottom() > getTop())) {
            damage(bullet.getDamage());
            hit(bullet);
            bullet.destroy();
            return true;
        }
        return false;
    }

}
