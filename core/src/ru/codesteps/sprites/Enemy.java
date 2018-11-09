package ru.codesteps.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;

import ru.codesteps.base.Ship;
import ru.codesteps.pools.BulletPool;

public class Enemy extends Ship {

    private Vector2 v0 = new Vector2(0, -0.3f);

    public Enemy(BulletPool bulletPool, BaseRectangle worldBounds, Sound shootSound) {
        super(shootSound);

        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        if (v.len() < v0.len() && getTop() > worldBounds.getTop()) {
            pos.mulAdd(v0, delta);
        } else {
            pos.mulAdd(v, delta);
            if (isOutside(worldBounds)) {
                destroy();
            }
            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                shoot();
                reloadTimer = 0f;
            }
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
        this.v.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
        setAngle(180);
    }

}
