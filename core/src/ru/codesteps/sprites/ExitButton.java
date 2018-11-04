package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.codesteps.base.ActionListener;
import ru.codesteps.base.BaseButton;
import ru.codesteps.base.BaseRectangle;

public class ExitButton extends BaseButton {
    
    public ExitButton(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("exit_btn"), actionListener);
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setTop(0.20f);
    }
    
}
