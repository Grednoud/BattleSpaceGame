package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.codesteps.BattleSpaceGame;
import ru.codesteps.screens.base.BaseScreen;

public class GameScreen extends BaseScreen {

    private final BattleSpaceGame game;
    private final float SHIP_SPEED = 10f;

    private Texture ship;
    private Vector2 position;
    private Vector2 direction;
    private Vector2 destination;

    public GameScreen(final BattleSpaceGame game) {
        this.game = game;
        ship = new Texture("death-star.png");
        position = new Vector2(0, 0);
        destination = new Vector2(0, 0);
        direction = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(ship, position.x, position.y);
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
        direction = destination.cpy().sub(position).nor().scl(SHIP_SPEED);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 19:
                direction.set(0, 1).scl(SHIP_SPEED);
                break;
            case 20:
                direction.set(0, -1).scl(SHIP_SPEED);
                break;
            case 21:
                direction.set(-1, 0).scl(SHIP_SPEED);
                break;
            case 22:
                direction.set(1, 0).scl(SHIP_SPEED);
                break;
            default:
                break;
        }
        destination.set(-100, -100);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 19:
            case 20:
            case 21:
            case 22:
                destination.set(position.x, position.y);
                direction.set(0, 0);
                break;
            default:
                break;
        }
        return super.keyUp(keycode);
    }

    private void moveShip() {
        if (direction.len() != 0) {
            float distance = destination.cpy().sub(position).len();
            if (distance <= SHIP_SPEED) {
                position.set(destination.x, destination.y);
                direction.set(0, 0);
            }
            position.add(direction);
        }
    }
}
