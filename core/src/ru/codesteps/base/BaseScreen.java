package ru.codesteps.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.codesteps.math.MatrixUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.Level;

import java.util.logging.Logger;
import ru.codesteps.BattleSpaceGame;

public abstract class BaseScreen implements Screen, InputProcessor {

    private static final Logger log = Logger.getLogger("BaseScreen");

    private BaseRectangle screenBounds;
    private BaseRectangle glBounds;
    private Vector2 buffer;

    protected BaseRectangle worldBounds;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;
    protected final BattleSpaceGame game;

    protected SpriteBatch batch;

    public BaseScreen(BattleSpaceGame game) {
        this.game = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        log.info("show()");
        Gdx.input.setInputProcessor(this);
        screenBounds = new BaseRectangle();
        worldBounds = new BaseRectangle();
        glBounds = new BaseRectangle(0, 0, 2f, 2f);

        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();

        buffer = new Vector2();
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        log.log(Level.INFO, "resize({0},{1})", new Object[]{width, height});
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f * aspect);
        worldBounds.setBottom(0);
        worldToGl = MatrixUtils.calculateTranslationMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        screenToWorld = MatrixUtils.calculateTranslationMatrix(screenToWorld, screenBounds, worldBounds);

        resize(worldBounds);
    }

    public void resize(BaseRectangle worldBounds) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
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
        buffer.set(screenX, screenBounds.height - screenY).mul(screenToWorld);
        touchDown(buffer, pointer);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        log.log(Level.INFO, "touchDown({0}, {1})", new Object[]{touch.x, touch.y});
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        buffer.set(screenX, screenBounds.height - screenY).mul(screenToWorld);
        touchUp(buffer, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        log.log(Level.INFO, "touchUp({0}, {1})", new Object[]{touch.x, touch.y});
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        buffer.set(screenX, screenBounds.height - screenY).mul(screenToWorld);
        touchDragged(buffer, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        log.log(Level.INFO, "touchDragged({0}, {1})", new Object[]{touch.x, touch.y});
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
