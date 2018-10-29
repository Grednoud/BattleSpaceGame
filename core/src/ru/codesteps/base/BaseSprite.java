package ru.codesteps.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseSprite extends BaseRectangle {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;

    public BaseSprite(TextureRegion... regions) {
        if (regions == null) {
            throw new NullPointerException("region is null");
        }
        this.regions = regions;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(regions[frame],
                getLeft(), getBottom(),
                width / 2f, height / 2f,
                width, height,
                scale, scale,
                angle);
    }

    public void setHeightProportion(float height) {
        this.height = height;
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        this.width = height * aspect;
    }

    public abstract void update(float delta);

    public abstract void resize(BaseRectangle worldBounds);

    public abstract boolean touchDown(Vector2 touch, int pointer);

    public abstract boolean touchUp(Vector2 touch, int pointer);

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
