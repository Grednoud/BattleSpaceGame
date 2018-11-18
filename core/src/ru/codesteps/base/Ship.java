package ru.codesteps.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.pools.ExplosionPool;
import ru.codesteps.sprites.Bullet;
import ru.codesteps.sprites.Explosion;

public class Ship extends BaseSprite {

    protected Vector2 v = new Vector2();

    protected BaseRectangle worldBounds;

    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    protected float reloadInterval;
    protected float reloadTimer;

    protected float damageAnimationInterval = 0.1f;
    protected float damageAnimationTimer;

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

    @Override
    public void update(float delta) {
        super.update(delta);
        damageAnimationTimer += delta;
        if (damageAnimationTimer >= damageAnimationInterval) {
            frame = 0;
        }
    }

    public void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(height, pos);
    }

    public void damage(int damage) {
        frame = 1;
        damageAnimationTimer = 0f;
        hp -= damage;
        if (hp <= 0) {
            boom();
            destroy();
        }
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        shootSound.play(0.5f);
    }
    
    public void hit(Bullet bullet) {
        Explosion explosion = explosionPool.obtain();
        explosion.set(bullet.height, bullet.pos);
    }

}
