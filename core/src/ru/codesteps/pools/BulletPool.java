package ru.codesteps.pools;

import ru.codesteps.base.SpritesPool;
import ru.codesteps.sprites.Bullet;

public class BulletPool extends SpritesPool<Bullet>{

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
    
}
