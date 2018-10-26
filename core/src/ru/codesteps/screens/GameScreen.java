package ru.codesteps.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.codesteps.BattleSpaceGame;
import ru.codesteps.screens.base.BaseScreen;

public class GameScreen extends BaseScreen {

    private Texture background;
    private Texture ship;

    private Vector2 position;
    private Vector2 touch;

    public GameScreen(final BattleSpaceGame game) {
        super(game);
        background = new Texture("background.jpg");
        ship = new Texture("death-star.png");
        position = new Vector2(0, 0);
        touch = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0, 0.7f, 1f);
        game.batch.draw(ship, position.x, position.y, 0.075f, 0.075f);
        game.batch.end();
        moveShip();
    }

    @Override
    public void dispose() {
        ship.dispose();
        super.dispose();
    }

    private void moveShip() {
        
    }

}
