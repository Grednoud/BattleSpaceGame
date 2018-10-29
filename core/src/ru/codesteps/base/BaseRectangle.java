package ru.codesteps.base;

import com.badlogic.gdx.math.Vector2;

public class BaseRectangle {

    public Vector2 pos;
    public float width;
    public float height;

    public BaseRectangle() {
        pos = new Vector2();
    }

    public BaseRectangle(float x, float y, float width, float height) {
        pos = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public void setPosition(float x, float y) {
        pos.set(x, y);
    }

    public void setLeft(float left) {
        pos.x = left + width / 2f;
    }

    public void setRight(float right) {
        pos.x = right - width / 2f;
    }

    public void setBottom(float bottom) {
        pos.y = bottom + height / 2f;
    }

    public void setTop(float top) {
        pos.y = top - height / 2f;
    }

    public float getLeft() {
        return pos.x - width / 2f;
    }

    public float getRight() {
        return pos.x + width / 2f;
    }

    public float getBottom() {
        return pos.y - height / 2f;
    }

    public float getTop() {
        return pos.y + height / 2f;
    }

    void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void setHeight(float height) {
        this.height = height;
    }

    void setWidth(float width) {
        this.width = width;
    }
    
    public boolean isInside(Vector2 touch) {
        return touch.x >= getLeft() && touch.x <= getRight() && touch.y >= getBottom() && touch.y <= getTop();
    }
    
    public boolean isOutside(BaseRectangle other) {
        return getLeft() > other.getRight() || getRight() < other.getLeft() || getBottom() > other.getTop() || getTop() < other.getBottom();
    }
}
