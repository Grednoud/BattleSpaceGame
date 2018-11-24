package ru.codesteps.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.math.RandomUtil;
import ru.codesteps.pools.EnemyPool;
import ru.codesteps.sprites.Enemy;

public class EnemiesEmiter {

    private static final float FIGHTER_HEIGHT = 0.12f;
    private static final float FIGHTER_BULLET_HEIGHT = 0.01f;
    private static final float FIGHTER_BULLET_VY = -0.6f;
    private static final int FIGHTER_DAMAGE = 1;
    private static final float FIGHTER_RELOAD_INTERVAL = 0.3f;
    private static final int FIGHTER_HP = 1;

    private static final float TERMINATOR_HEIGHT = 0.13f;
    private static final float TERMINATOR_BULLET_HEIGHT = 0.015f;
    private static final float TERMINATOR_BULLET_VY = -0.6f;
    private static final int TERMINATOR_DAMAGE = 3;
    private static final float TERMINATOR_RELOAD_INTERVAL = 0.5f;
    private static final int TERMINATOR_HP = 3;

    private static final float CHASER_HEIGHT = 0.13f;
    private static final float CHASER_BULLET_HEIGHT = 0.015f;
    private static final float CHASER_BULLET_VY = -0.6f;
    private static final int CHASER_DAMAGE = 5;
    private static final float CHASER_RELOAD_INTERVAL = 0.5f;
    private static final int CHASER_HP = 5;

    private static final float FRIGATE_HEIGHT = 0.25f;
    private static final float FRIGATE_BULLET_HEIGHT = 0.03f;
    private static final float FRIGATE_BULLET_VY = -0.6f;
    private static final int FRIGATE_DAMAGE = 20;
    private static final float FRIGATE_RELOAD_INTERVAL = 0.7f;
    private static final int FRIGATE_HP = 10;
    
    private static final float DESTROYER_HEIGHT = 0.30f;
    private static final float DESTROYER_BULLET_HEIGHT = 0.04f;
    private static final float DESTROYER_BULLET_VY = -0.6f;
    private static final int DESTROYER_DAMAGE = 30;
    private static final float DESTROYER_RELOAD_INTERVAL = 0.9f;
    private static final int DESTROYER_HP = 20;

    private TextureRegion[] fighterRegions;
    private TextureRegion[] terminatorRegions;
    private TextureRegion[] chaserRegions;
    private TextureRegion[] frigateRegions;
    private TextureRegion[] destroyerRegions;

    private Vector2 fighterV = new Vector2(0, -0.3f);
    private Vector2 terminatorV = new Vector2(0, -0.25f);
    private Vector2 chaserV = new Vector2(0, -0.20f);
    private Vector2 frigateV = new Vector2(0, -0.1f);
    private Vector2 destroyerV = new Vector2(0, -0.05f);

    private EnemyPool enemyPool;
    private BaseRectangle worldBounds;
    private TextureRegion bulletRegion;

    private float generateInterval = 3f;
    private float generateTimer;

    public EnemiesEmiter(EnemyPool enemyPool, BaseRectangle worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.bulletRegion = atlas.findRegion("plasm");

        TextureRegion fighterRegion = atlas.findRegion("fighter0");
        this.fighterRegions = RegionsUtil.split(fighterRegion, 1, 2, 2);

        TextureRegion terminatorRegion = atlas.findRegion("terminator0");
        this.terminatorRegions = RegionsUtil.split(terminatorRegion, 1, 2, 2);

        TextureRegion chaserRegion = atlas.findRegion("chaser0");
        this.chaserRegions = RegionsUtil.split(chaserRegion, 1, 2, 2);

        TextureRegion frigateRegion = atlas.findRegion("frigate0");
        this.frigateRegions = RegionsUtil.split(frigateRegion, 1, 2, 2);

        TextureRegion destroyerRegion = atlas.findRegion("destroyer0");
        this.destroyerRegions = RegionsUtil.split(destroyerRegion, 1, 2, 2);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.3f) {
                enemy.set(
                        fighterRegions,
                        fighterV,
                        bulletRegion,
                        FIGHTER_BULLET_HEIGHT,
                        FIGHTER_BULLET_VY,
                        FIGHTER_DAMAGE,
                        FIGHTER_RELOAD_INTERVAL,
                        FIGHTER_HEIGHT,
                        FIGHTER_HP
                );
            } else if (type < 0.6f) {
                enemy.set(
                        terminatorRegions,
                        terminatorV,
                        bulletRegion,
                        TERMINATOR_BULLET_HEIGHT,
                        TERMINATOR_BULLET_VY,
                        TERMINATOR_DAMAGE,
                        TERMINATOR_RELOAD_INTERVAL,
                        TERMINATOR_HEIGHT,
                        TERMINATOR_HP
                );
            } else if (type < 0.8f) {
                enemy.set(
                        chaserRegions,
                        chaserV,
                        bulletRegion,
                        CHASER_BULLET_HEIGHT,
                        CHASER_BULLET_VY,
                        CHASER_DAMAGE,
                        CHASER_RELOAD_INTERVAL,
                        CHASER_HEIGHT,
                        CHASER_HP
                );
            } else if (type < 0.95f) {
                enemy.set(
                        frigateRegions,
                        frigateV,
                        bulletRegion,
                        FRIGATE_BULLET_HEIGHT,
                        FRIGATE_BULLET_VY,
                        FRIGATE_DAMAGE,
                        FRIGATE_RELOAD_INTERVAL,
                        FRIGATE_HEIGHT,
                        FRIGATE_HP
                );
            } else {
                enemy.set(
                        destroyerRegions,
                        destroyerV,
                        bulletRegion,
                        DESTROYER_BULLET_HEIGHT,
                        DESTROYER_BULLET_VY,
                        DESTROYER_DAMAGE,
                        DESTROYER_RELOAD_INTERVAL,
                        DESTROYER_HEIGHT,
                        DESTROYER_HP
                );
            }

            enemy.setBottom(worldBounds.getTop());
            enemy.pos.x = RandomUtil.nextFloat(worldBounds.getLeft() + enemy.width / 2f,
                    worldBounds.getRight() - enemy.width / 2f);
        }
    }

}
