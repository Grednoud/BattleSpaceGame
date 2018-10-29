package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.BattleSpaceGame;
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
    private PlayButton playBtn;
    private ExitButton exitBtn;

    private Vector2 touch;

    private TextureAtlas buttonAtlas;

    public MenuScreen(final BattleSpaceGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        touch = new Vector2();

        shipTexture = new Texture("ship_logo.png");
        ship = new Background(new TextureRegion(shipTexture));
        ship.setHeightProportion(0.5f);
        ship.setTop(1f);
        bgTexture = new Texture("bg.jpg");
        background = new Background(new TextureRegion(bgTexture));

        buttonAtlas = new TextureAtlas("./buttons/pack.atlas");
        playBtn = new PlayButton(buttonAtlas, game);
        playBtn.setHeightProportion(0.25f);
        playBtn.setTop(0.4f);
        exitBtn = new ExitButton(buttonAtlas);
        exitBtn.setHeightProportion(0.25f);
        exitBtn.setTop(0.2f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
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
    }

    private void update(float delta) {

    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        ship.draw(batch);
        playBtn.draw(batch);
        exitBtn.draw(batch);
        batch.end();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        if (playBtn.isInside(touch)) {
            playBtn.touchUp(touch, pointer);
        }
        if (exitBtn.isInside(touch)) {
            exitBtn.touchUp(touch, pointer);
        }
        
        exitBtn.setScale(1f);
        playBtn.setScale(1f);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        if (playBtn.isInside(touch)) {
            playBtn.touchDown(touch, pointer);
        }
        if (exitBtn.isInside(touch)) {
            exitBtn.touchDown(touch, pointer);
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

}
