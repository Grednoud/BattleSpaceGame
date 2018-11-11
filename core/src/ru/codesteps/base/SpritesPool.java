package ru.codesteps.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.codesteps.base.BaseSprite;

public abstract class SpritesPool<T extends BaseSprite> {

    private static final Logger log = Logger.getLogger("SpritePool");

    protected final List<T> activeObjects;

    protected final List<T> freeObjects;

    public SpritesPool() {
        activeObjects = new ArrayList<T>();
        freeObjects = new ArrayList<T>();
    }

    public SpritesPool(int capacity) {
        activeObjects = new ArrayList<T>(capacity);
        freeObjects = new ArrayList<T>(capacity);
    }

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        log.log(Level.INFO, "active({0}) free({1})", new Object[]{activeObjects.size(), freeObjects.size()});
        return object;
    }

    public void drawActiveObjects(SpriteBatch batch) {
        for (T object : activeObjects) {
            if (!object.isDestroyed()) {
                object.draw(batch);
            }
        }
    }

    public void updateActiveObjects(float delta) {
        for (T object : activeObjects) {
            if (!object.isDestroyed()) {
                object.update(delta);
            }
        }
    }

    public void freeAllDestroyedObjects() {
        T object;
        for (int i = 0; i < activeObjects.size(); i++) {
            object = activeObjects.get(i);
            if (object.isDestroyed()) {
                free(object);
                i--;
                object.make();
            }
        }
    }

    private void free(T object) {
        if (activeObjects.remove(object)) {
            freeObjects.add(object);
        }
        log.log(Level.INFO, "active({0}) free({1})", new Object[]{activeObjects.size(), freeObjects.size()});
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }
}
