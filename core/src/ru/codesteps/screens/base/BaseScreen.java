package ru.codesteps.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import java.util.logging.Level;

import java.util.logging.Logger;

public  abstract class BaseScreen implements Screen, InputProcessor {
    private static final Logger log = Logger.getLogger("BaseScreen");

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
        log.log(Level.INFO, "touchDown({0}, {1})", new Object[]{screenX, screenY});
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
