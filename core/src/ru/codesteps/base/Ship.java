package ru.codesteps.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.sprites.Bullet;

public class Ship extends BaseSprite {

    protected Vector2 v = new Vector2();

    protected BaseRectangle worldBounds;

    protected BulletPool bulletPool;
    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;
    protected TextureRegion bulletRegion;

    private Sound shootSound;

    public Ship(TextureRegion region, int rows, int colums, int frames, Sound shootSound) {
        super(region, rows, colums, frames);
        this.shootSound = shootSound;
    }

    public Ship(Sound shootSound) {
        this.shootSound = shootSound;
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        shootSound.play();
    }
}
