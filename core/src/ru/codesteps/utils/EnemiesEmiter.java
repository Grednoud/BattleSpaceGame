package ru.codesteps.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.math.RandomUtil;
import ru.codesteps.pools.EnemyPool;
import ru.codesteps.sprites.Enemy;

public class EnemiesEmiter {

    private static final float ENEMY_HEIGHT = 0.15f;
    private static final float ENEMY_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_BULLET_VY = -0.5f;
    private static final int ENEMY_1_DAMAGE = 1;
    private static final float ENEMY_1_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_1_HP = 1;

    private TextureRegion[] enemy1Regions;
    private TextureRegion[] enemy2Regions;
    private TextureRegion[] enemy3Regions;
    private TextureRegion[] enemy4Regions;
    private TextureRegion[] enemy5Regions;

    private Vector2 enemy1V = new Vector2(0, -0.1f);
    private Vector2 enemy2V = new Vector2(0, -0.15f);
    private Vector2 enemy3V = new Vector2(0, -0.20f);
    private Vector2 enemy4V = new Vector2(0, -0.25f);
    private Vector2 enemy5V = new Vector2(0, -0.3f);

    private EnemyPool enemyPool;
    private BaseRectangle worldBounds;
    private TextureRegion bulletRegion;

    private float generateInterval = 3f;
    private float generateTimer;

    public EnemiesEmiter(EnemyPool enemyPool, BaseRectangle worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.bulletRegion = atlas.findRegion("bullet");

        TextureRegion textureRegion1 = atlas.findRegion("enemy1.0");
        this.enemy1Regions = RegionsUtil.split(textureRegion1, 1, 1, 1);

        TextureRegion textureRegion2 = atlas.findRegion("enemy2.0");
        this.enemy2Regions = RegionsUtil.split(textureRegion2, 1, 1, 1);

        TextureRegion textureRegion3 = atlas.findRegion("enemy3.0");
        this.enemy3Regions = RegionsUtil.split(textureRegion3, 1, 1, 1);

        TextureRegion textureRegion4 = atlas.findRegion("enemy4.0");
        this.enemy4Regions = RegionsUtil.split(textureRegion4, 1, 1, 1);

        TextureRegion textureRegion5 = atlas.findRegion("enemy5.0");
        this.enemy5Regions = RegionsUtil.split(textureRegion5, 1, 1, 1);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.15f) {
                enemy.set(
                        enemy1Regions,
                        enemy1V,
                        bulletRegion,
                        ENEMY_BULLET_HEIGHT,
                        ENEMY_BULLET_VY,
                        ENEMY_1_DAMAGE,
                        ENEMY_1_RELOAD_INTERVAL,
                        ENEMY_HEIGHT,
                        ENEMY_1_HP
                );
            } else if (type < 0.3f) {
                enemy.set(
                        enemy2Regions,
                        enemy2V,
                        bulletRegion,
                        ENEMY_BULLET_HEIGHT,
                        ENEMY_BULLET_VY,
                        ENEMY_1_DAMAGE,
                        ENEMY_1_RELOAD_INTERVAL,
                        ENEMY_HEIGHT,
                        ENEMY_1_HP
                );
            } else if (type < 0.45f) {
                enemy.set(
                        enemy3Regions,
                        enemy3V,
                        bulletRegion,
                        ENEMY_BULLET_HEIGHT,
                        ENEMY_BULLET_VY,
                        ENEMY_1_DAMAGE,
                        ENEMY_1_RELOAD_INTERVAL,
                        ENEMY_HEIGHT,
                        ENEMY_1_HP
                );
            } else if (type < 0.6f) {
                enemy.set(
                        enemy4Regions,
                        enemy4V,
                        bulletRegion,
                        ENEMY_BULLET_HEIGHT,
                        ENEMY_BULLET_VY,
                        ENEMY_1_DAMAGE,
                        ENEMY_1_RELOAD_INTERVAL,
                        ENEMY_HEIGHT,
                        ENEMY_1_HP
                );
            } else {
                enemy.set(
                        enemy5Regions,
                        enemy5V,
                        bulletRegion,
                        ENEMY_BULLET_HEIGHT,
                        ENEMY_BULLET_VY,
                        ENEMY_1_DAMAGE,
                        ENEMY_1_RELOAD_INTERVAL,
                        ENEMY_HEIGHT,
                        ENEMY_1_HP
                );
            }
            
            enemy.setBottom(worldBounds.getTop());
            enemy.pos.x = RandomUtil.nextFloat(worldBounds.getLeft()+enemy.width/2, 
                    worldBounds.getRight()-enemy.width/2);
        }
    }

}
