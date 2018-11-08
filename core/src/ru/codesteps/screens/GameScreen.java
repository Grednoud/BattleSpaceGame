package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.codesteps.BattleSpaceGame;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseScreen;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.pools.EnemyPool;
import ru.codesteps.sprites.Background;
import ru.codesteps.sprites.MainShip;
import ru.codesteps.sprites.Star;
import ru.codesteps.utils.EnemiesEmiter;

public class GameScreen extends BaseScreen {

    private static final int STARS_COUNT = 64;

    private Texture bgTexture;
    private Background background;

    private TextureAtlas gameAtlas;
    private Star[] stars;

    private MainShip ship;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private EnemiesEmiter enemiesEmiter;

    private Music music;
    private Sound bulletSound;

    public GameScreen(BattleSpaceGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sound/shoot.wav"));

        music = Gdx.audio.newMusic(Gdx.files.internal("sound/soundtrack.mp3"));
        music.setVolume(0.7f);
        music.setLooping(true);
        music.play();

        bgTexture = new Texture("game-bg.jpg");
        background = new Background(new TextureRegion(bgTexture));

        gameAtlas = new TextureAtlas("game/pack.atlas");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(gameAtlas);
        }
        bulletPool = new BulletPool();
        ship = new MainShip(gameAtlas, bulletPool, bulletSound);

        enemyPool = new EnemyPool(bulletPool, worldBounds, bulletSound);
        enemiesEmiter = new EnemiesEmiter(enemyPool, worldBounds, gameAtlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDesroyed();
        draw();
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        gameAtlas.dispose();
        music.dispose();
        bulletSound.dispose();
        super.dispose();
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        background.resize(worldBounds);
        for (Star s : stars) {
            s.resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public boolean keyUp(int keycode) {
        ship.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        ship.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }

    private void update(float delta) {
        for (Star s : stars) {
            s.update(delta);
        }

        ship.update(delta);
        bulletPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        enemiesEmiter.generate(delta);
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (Star s : stars) {
            s.draw(batch);
        }
        ship.draw(batch);
        enemyPool.drawActiveObjects(batch);
        bulletPool.drawActiveObjects(batch);
        batch.end();
    }

    private void checkCollisions() {

    }

    private void deleteAllDesroyed() {
        bulletPool.freeAllDestroyedObjects();
        enemyPool.freeAllDestroyedObjects();
    }

}