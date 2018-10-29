package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.BattleSpaceGame;
import ru.codesteps.base.BaseButton;

public class PlayButton extends BaseButton {
    private BattleSpaceGame game;

    public PlayButton(TextureRegion region) {
        super(region);
    }
    
    public PlayButton(TextureAtlas textureAtlas, BattleSpaceGame game) {
        super(textureAtlas.findRegion("start_btn"), textureAtlas.findRegion("start_btn_clicked"));
        this.game = game;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        frame = 0;
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        frame = 1;
        return super.touchDown(touch, pointer);
    }

    @Override
    public void action() {
        System.out.println("GAME STARTED");
        game.setGameScreen();
    }
    
}
