package ru.codesteps.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseButton;

public class ExitButton extends BaseButton {
    
    public ExitButton(TextureRegion... regions) {
        super(regions);
    }
    
    public ExitButton(TextureAtlas textureAtlas) {
        super(textureAtlas.findRegion("exit_btn"), textureAtlas.findRegion("exit_btn_clicked"));
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
        System.out.println("EXIT GAME");
        Gdx.app.exit();
    }
    
}
