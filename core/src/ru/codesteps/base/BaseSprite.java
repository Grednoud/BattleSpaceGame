package ru.codesteps.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.utils.RegionsUtil;

public class BaseSprite extends BaseRectangle {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;

    private boolean isDestroyed;

    public BaseSprite() {

    }

    public BaseSprite(TextureRegion region) {
        if (region == null) {
            throw new NullPointerException("region is null");
        }
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public BaseSprite(TextureRegion region, int rows, int columns, int frames) {
        regions = RegionsUtil.split(region, rows, columns, frames);
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

    public void update(float delta) {

    }

    public void resize(BaseRectangle worldBounds) {

    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

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

    public void destroy() {
        isDestroyed = true;
    }

    public void make() {
        isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

}
