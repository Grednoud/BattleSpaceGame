package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.BattleSpaceGame;
import ru.codesteps.base.ActionListener;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseScreen;
import ru.codesteps.base.BaseSprite;
import ru.codesteps.sprites.Background;
import ru.codesteps.sprites.ExitButton;
import ru.codesteps.sprites.PlayButton;

public class MenuScreen extends BaseScreen {

    private Texture shipTexture;
    private BaseSprite ship;
    private Texture bgTexture;
    private Background background;
    private TextureAtlas buttonAtlas;

    private PlayButton playBtn;
    private ExitButton exitBtn;

    public MenuScreen(final BattleSpaceGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        shipTexture = new Texture("ship_logo.png");
        shipTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ship = new BaseSprite(new TextureRegion(shipTexture));
        ship.setHeightProportion(0.5f);
        ship.setTop(1f);

        bgTexture = new Texture("bg.jpg");
        background = new Background(new TextureRegion(bgTexture));

        buttonAtlas = new TextureAtlas("buttons/pack.atlas");
        playBtn = new PlayButton(buttonAtlas, new ActionListener() {
            @Override
            public void actionPerformed(Object src) {
                game.setGameScreen();
            }
        });
        exitBtn = new ExitButton(buttonAtlas, new ActionListener() {
            @Override
            public void actionPerformed(Object src) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
//        exitBtn.update(delta);
//        playBtn.update(delta);
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        ship.draw(batch);
        exitBtn.draw(batch);
        playBtn.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        shipTexture.dispose();
        bgTexture.dispose();
        buttonAtlas.dispose();
        super.dispose();
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        background.resize(worldBounds);
        exitBtn.resize(worldBounds);
        playBtn.resize(worldBounds);
        super.resize(worldBounds);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        exitBtn.touchUp(touch, pointer);
        playBtn.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        exitBtn.touchDown(touch, pointer);
        playBtn.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

}
