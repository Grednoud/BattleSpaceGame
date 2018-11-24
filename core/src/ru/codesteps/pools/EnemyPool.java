package ru.codesteps.pools;

import com.badlogic.gdx.audio.Sound;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.SpritesPool;
import ru.codesteps.sprites.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private BaseRectangle worldBounds;
    private Sound bulletSound;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, BaseRectangle worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, worldBounds, bulletSound);
    }

}
