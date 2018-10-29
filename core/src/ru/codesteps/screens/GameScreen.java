package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.codesteps.BattleSpaceGame;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseScreen;
import ru.codesteps.sprites.Background;
import ru.codesteps.sprites.Star;

public class GameScreen extends BaseScreen {

    private static final int STARS_COUNT = 256;

    private Texture bgTexture;
    private TextureAtlas gameAtlas;
    private Background background;
    private Star[] stars;

    public GameScreen(final BattleSpaceGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("game-bg.jpg");
        background = new Background(new TextureRegion(bgTexture));

        gameAtlas = new TextureAtlas("./game/pack.atlas");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(gameAtlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        bgTexture.dispose();

        super.dispose();
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        background.resize(worldBounds);
        for (Star s : stars) {
            s.resize(worldBounds);
        }
    }

    private void update(float delta) {
        for (Star s : stars) {
            s.update(delta);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (Star s : stars) {
            s.draw(batch);
        }
        batch.end();
    }

}
