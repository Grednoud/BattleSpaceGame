package ru.codesteps;

import com.badlogic.gdx.Game;
import ru.codesteps.screens.GameScreen;

import ru.codesteps.screens.MenuScreen;

public class BattleSpaceGame extends Game {

    public void setMenuScreen() {
        setScreen(new MenuScreen(this));
    }
    
    public void setGameScreen() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void create() {
        setMenuScreen();
    }

}
