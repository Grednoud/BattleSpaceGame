package ru.codesteps.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import ru.codesteps.math.MatrixUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.Level;

import java.util.logging.Logger;
import ru.codesteps.BattleSpaceGame;

public abstract class BaseScreen implements Screen, InputProcessor {

    private static final Logger log = Logger.getLogger("BaseScreen");

    private Rectangle screenBounds;
    private Rectangle worldBounds;
    private Rectangle glBounds;
    private Vector2 buffer;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;
    protected final BattleSpaceGame game;

    public BaseScreen(BattleSpaceGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        log.info("show()");
        Gdx.input.setInputProcessor(this);
        screenBounds = new Rectangle();
        worldBounds = new Rectangle();
        glBounds = new Rectangle(-1f, -1f, 2f, 2f);

        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();

        buffer = new Vector2();
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        log.log(Level.INFO, "New size w={0} h={1}", new Object[]{width, height});
        screenBounds.setSize(width, height);
        screenBounds.setPosition(0, 0);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f * aspect);
        worldToGl = MatrixUtils.calculateTranslationMatrix(worldToGl, worldBounds, glBounds);
        game.batch.setProjectionMatrix(worldToGl);
        screenToWorld = MatrixUtils.calculateTranslationMatrix(screenToWorld, screenBounds, worldBounds);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        log.log(Level.INFO, "keyDown({0})", keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        log.log(Level.INFO, "keyUp({0})", keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        log.log(Level.INFO, "touchDown({0}, {1}) screen coordinates", new Object[]{screenX, screenY});
        buffer.set(screenX, screenBounds.height - screenY).mul(screenToWorld);
        log.log(Level.INFO, "touchDown({0}, {1}) world coordinates", new Object[]{buffer.x, buffer.y});
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
