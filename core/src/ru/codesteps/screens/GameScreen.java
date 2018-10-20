package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.codesteps.BattleSpaceGame;
import ru.codesteps.screens.base.BaseScreen;

public class GameScreen extends BaseScreen {

    private final BattleSpaceGame game;
    private final float SHIP_SPEED = 20f;

    private Texture ship;
    private Vector2 pos;
    private Vector2 direction;
    private Vector2 destination;

    public GameScreen(final BattleSpaceGame game) {
        this.game = game;
        ship = new Texture("death-star.png");
        pos = new Vector2(0, 0);
        destination = new Vector2(0, 0);
        direction = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(ship, pos.x, pos.y);
        game.batch.end();
        moveShip();
    }

    @Override
    public void dispose() {
        ship.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        destination.set(screenX - ship.getWidth() / 2,
                Gdx.graphics.getHeight() - screenY - ship.getHeight() / 2);
        direction = destination.cpy().sub(pos).nor().scl(SHIP_SPEED);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case 19:
                destination.set(pos.add(direction.set(0, 1).scl(SHIP_SPEED)));
                break;
            case 20:
                destination.set(pos.add(direction.set(0, -1).scl(SHIP_SPEED)));
                break;
            case 21:
                destination.set(pos.add(direction.set(-1, 0).scl(SHIP_SPEED)));
                break;
            case 22:
                destination.set(pos.add(direction.set(1, 0).scl(SHIP_SPEED)));
                break;
            default:
                break;
        }
        return super.keyDown(keycode);
    }

    private void moveShip() {
        if (direction.len() != 0) {
            float distance = destination.cpy().sub(pos).len();
            if (distance <= SHIP_SPEED) {
                pos.set(destination.x, destination.y);
                direction.set(0, 0);
            }
            pos.add(direction);
        }
    }
}
