package ru.codesteps.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseSprite;

public class Explosion extends BaseSprite {

    private Sound explosionSound;

    private final float frameInterval = 0.0125f;
    private float frameTimer;

    public Explosion(TextureRegion region, int rows, int columns, int frames, Sound explosionSound) {
        super(region, rows, columns, frames);
        this.explosionSound = explosionSound;
    }

    public void set(float height, Vector2 pos) {
        setHeightProportion(height);
        this.pos.set(pos);
        explosionSound.play(0.8f);
    }

    @Override
    public void update(float delta) {
        frameTimer += delta;
        if (frameTimer >= frameInterval) {
            frameTimer = 0f;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        frame = 0;
        super.destroy();
    }

}
