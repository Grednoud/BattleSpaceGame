package ru.codesteps.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.codesteps.base.ActionListener;
import ru.codesteps.base.BaseButton;
import ru.codesteps.base.BaseRectangle;

public class PlayButton extends BaseButton {

    public PlayButton(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("start_btn"), actionListener);
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        setTop(0.35f);
    }

}
