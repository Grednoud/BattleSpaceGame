package ru.codesteps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import ru.codesteps.screens.GameScreen;

import ru.codesteps.screens.MenuScreen;

public class BattleSpaceGame extends Game {
    private Screen menuScreen;
    private Screen gameScreen;
    
    public void setMenuScreen() {
        setScreen(menuScreen);
    }
    
    public void setGameScreen() {
        setScreen(gameScreen);
    }

    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        setScreen(menuScreen);
    }

}
