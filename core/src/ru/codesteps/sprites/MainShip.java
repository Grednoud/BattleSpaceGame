package ru.codesteps.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.BaseSprite;
import ru.codesteps.pools.BulletPool;

public class MainShip extends BaseSprite {

    private Vector2 v0;
    private Vector2 v;

    private boolean pressedLeft;
    private boolean pressedRight;

    private BulletPool bulletPool;

    private TextureAtlas atlas;

    private BaseRectangle worldBounds;

    private Sound shootSound;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main-ship"), 1, 2, 2);
        v = new Vector2();
        v0 = new Vector2(0.5f, 0);
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sound/shoot.wav"));
        this.atlas = atlas;
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.03f);
    }

    @Override
    public void update(float delta) {
        if (worldBounds.getLeft() > getLeft()) {
            stop();
            setLeft(worldBounds.getLeft());
        }
        if (worldBounds.getRight() < getRight()) {
            stop();
            setRight(worldBounds.getRight());
        }
        pos.mulAdd(v, delta);
    }

    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }

    public boolean keyUp(int keyCode) {
        switch (keyCode) {
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public void touchDragged(Vector2 touch, int pointer) {
        if (isInside(touch)) {
            pos.x = touch.x;
        }
    }

    private void moveLeft() {
        v.set(v0).rotate(180);

    }

    private void moveRight() {
        v.set(v0);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        shootSound.play();
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, atlas.findRegion("bullet"), pos, new Vector2(0, 0.8f), 0.03f, worldBounds, 1);

    }

}
