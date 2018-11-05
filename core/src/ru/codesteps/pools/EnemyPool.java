package ru.codesteps.pools;

import ru.codesteps.base.SpritesPool;
import ru.codesteps.sprites.Enemy;

public class EnemyPool extends SpritesPool<Enemy> { 

    @Override
    protected Enemy newObject() {
        return new Enemy();
    }
    
}
