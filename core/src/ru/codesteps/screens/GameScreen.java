package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

import ru.codesteps.BattleSpaceGame;
import ru.codesteps.base.ActionListener;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseScreen;
import ru.codesteps.base.Font;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.pools.EnemyPool;
import ru.codesteps.pools.ExplosionPool;
import ru.codesteps.sprites.Background;
import ru.codesteps.sprites.Bullet;
import ru.codesteps.sprites.Enemy;
import ru.codesteps.sprites.GameOverButton;
import ru.codesteps.sprites.HealthLine;
import ru.codesteps.sprites.MainShip;
import ru.codesteps.sprites.Star;
import ru.codesteps.utils.EnemiesEmiter;

public class GameScreen extends BaseScreen {

    private static final int STARS_COUNT = 32;
    private static final String HEALTH = "health: ";
    private static final String TOTAL_DAMAGE = "total damage: ";

    private StringBuilder sbTotalDamage = new StringBuilder();
    private StringBuilder sbHealth = new StringBuilder();

    private int totalDamage;

    private HealthLine healthLine;

    private Texture bgTexture;
    private Background background;

    private TextureAtlas gameAtlas;
    private Star[] stars;

    private MainShip ship;

    private GameOverButton gameOverBtn;

    private ExplosionPool explosionPool;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private EnemiesEmiter enemiesEmiter;

    private Music music;
    private Sound bulletSound;
    private Sound explosionSound;

    private Font font;

    public GameScreen(BattleSpaceGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sound/shoot.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sound/explosion.wav"));

        music = Gdx.audio.newMusic(Gdx.files.internal("sound/soundtrack.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        bgTexture = new Texture("game-bg.jpg");
        background = new Background(new TextureRegion(bgTexture));

        font = new Font("font/font1.fnt", "font/font1.png");
        font.setFontSize(0.012f);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, HEALTH);

        gameAtlas = new TextureAtlas("game/pack.atlas");

        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(gameAtlas);
        }

        explosionPool = new ExplosionPool(gameAtlas, explosionSound);
        bulletPool = new BulletPool();
        ship = new MainShip(gameAtlas, bulletPool, explosionPool, bulletSound);

        healthLine = new HealthLine(gameAtlas, ship);
        healthLine.setFixedLeftMargin(layout.width);

        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, bulletSound);
        enemiesEmiter = new EnemiesEmiter(enemyPool, worldBounds, gameAtlas);

        gameOverBtn = new GameOverButton(gameAtlas, new ActionListener() {
            @Override
            public void actionPerformed(Object src) {
                game.setMenuScreen();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDesroyed();
        draw();
    }

    private void update(float delta) {
        for (Star s : stars) {
            s.update(delta);
        }
        if (!ship.isDestroyed()) {
            ship.update(delta);
            enemiesEmiter.generate(delta);
        }
        bulletPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        explosionPool.updateActiveObjects(delta);
        if (ship.isDestroyed()) {
            gameOverBtn.update(delta);
        }
        healthLine.update(delta);
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (Star s : stars) {
            s.draw(batch);
        }

        if (!ship.isDestroyed()) {
            ship.draw(batch);
        }

        bulletPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);

        if (ship.isDestroyed()) {
            gameOverBtn.draw(batch);
        }
        healthLine.draw(batch);
        printScore();

        batch.end();
    }

    private void printScore() {
        sbTotalDamage.setLength(0);
        sbHealth.setLength(0);
        font.draw(batch, sbHealth.append(HEALTH).append(ship.getHp()),
                worldBounds.getLeft() + 0.01f, worldBounds.getBottom() + 0.025f);
        font.draw(batch,
                sbTotalDamage.append(TOTAL_DAMAGE).append(totalDamage),
                worldBounds.getLeft() + 0.01f,
                worldBounds.getTop() - 0.025f);
    }

    private void checkCollisions() {
        if (!ship.isDestroyed()) {
            List<Enemy> enemies = enemyPool.getActiveObjects();
            for (Enemy enemy : enemies) {
                if (enemy.isDestroyed()) {
                    continue;
                }
                float minDistace = enemy.width / 2 + ship.width / 2;
                if (enemy.pos.dst2(ship.pos) < minDistace * minDistace) {
                    enemy.boom();
                    enemy.destroy();
                    ship.destroy();
                    return;
                }
            }

            List<Bullet> bullets = bulletPool.getActiveObjects();
            for (Bullet bullet : bullets) {
                if (bullet.getOwner() == ship || bullet.isDestroyed()) {
                    continue;
                }
                if (ship.isDamaged(bullet)) {
                    return;
                }
            }

            for (Enemy enemy : enemies) {
                if (enemy.isDestroyed()) {
                    continue;
                }
                for (Bullet bullet : bullets) {
                    if (bullet.getOwner() != ship || bullet.isDestroyed()) {
                        continue;
                    }
                    if (enemy.isDamaged(bullet)) {
                        totalDamage += bullet.getDamage();
                        return;
                    }
                }
            }
        }
    }

    private void deleteAllDesroyed() {
        bulletPool.freeAllDestroyedObjects();
        enemyPool.freeAllDestroyedObjects();
        explosionPool.freeAllDestroyedObjects();
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        gameAtlas.dispose();
        music.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        font.dispose();
        super.dispose();
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        background.resize(worldBounds);
        for (Star s : stars) {
            s.resize(worldBounds);
        }
        ship.resize(worldBounds);
        gameOverBtn.resize(worldBounds);
        healthLine.resize(worldBounds);
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

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (ship.isDestroyed()) {
            gameOverBtn.touchUp(touch, pointer);
        }
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (ship.isDestroyed()) {
            gameOverBtn.touchDown(touch, pointer);
        }
        return super.touchDown(touch, pointer);
    }

}
