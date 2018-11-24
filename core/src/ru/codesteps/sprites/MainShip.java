package ru.codesteps.sprites;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.codesteps.base.BaseRectangle;
import ru.codesteps.base.Ship;
import ru.codesteps.pools.BulletPool;
import ru.codesteps.pools.ExplosionPool;

public class MainShip extends Ship {

    private Vector2 v0 = new Vector2(0.5f, 0);

    private boolean pressedLeft;
    private boolean pressedRight;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound) {
        super(atlas.findRegion("main-ship"), 1, 2, 2, shootSound);

        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.bulletV.set(0, 0.5f);
        this.bulletHeight = 0.02f;
        this.bulletDamage = 1;
        this.reloadInterval = 0.15f;
        this.bulletRegion = atlas.findRegion("bullet");
        this.hp = 100;
    }

    @Override
    public void resize(BaseRectangle worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.03f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (worldBounds.getLeft() > getLeft()) {
            stop();
            setLeft(worldBounds.getLeft());
        }
        if (worldBounds.getRight() < getRight()) {
            stop();
            setRight(worldBounds.getRight());
        }
        if (!isDestroyed()) {
            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                shoot();
                reloadTimer = 0f;
            }
        }
    }

    @Override
    public void destroy() {
        boom();
        hp = 0;
        super.destroy();
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
                if (!isDestroyed()) {
                    shoot();
                }
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

    public boolean isDamaged(Bullet bullet) {
        if (!(bullet.getLeft() < getLeft() || bullet.getRight() > getRight()
                || bullet.getBottom() > pos.y || bullet.getTop() < getBottom())) {
            damage(bullet.getDamage());
            hit(bullet);
            bullet.destroy();
            return true;
        }
        return false;
    }

    public float getHp() {
        return hp;
    }

}
