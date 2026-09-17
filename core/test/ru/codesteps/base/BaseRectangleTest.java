package ru.codesteps.base;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BaseRectangle class.
 */
class BaseRectangleTest {

    private BaseRectangle rect;

    @BeforeEach
    void setUp() {
        rect = new BaseRectangle(0, 0, 10, 10);
    }

    @Test
    void constructor_default_shouldInitializeWithZeroPosition() {
        BaseRectangle defaultRect = new BaseRectangle();
        
        assertEquals(0, defaultRect.pos.x, 0.001f);
        assertEquals(0, defaultRect.pos.y, 0.001f);
    }

    @Test
    void constructor_withParameters_shouldSetPositionAndSize() {
        BaseRectangle paramRect = new BaseRectangle(5, 10, 20, 30);
        
        assertEquals(5, paramRect.pos.x, 0.001f);
        assertEquals(10, paramRect.pos.y, 0.001f);
        assertEquals(20, paramRect.width, 0.001f);
        assertEquals(30, paramRect.height, 0.001f);
    }

    @Test
    void setPosition_shouldUpdatePosition() {
        rect.setPosition(15, 25);
        
        assertEquals(15, rect.pos.x, 0.001f);
        assertEquals(25, rect.pos.y, 0.001f);
    }

    @Test
    void setLeft_shouldAdjustPositionBasedOnWidth() {
        rect.setLeft(5);
        
        assertEquals(10, rect.pos.x, 0.001f);
    }

    @Test
    void setRight_shouldAdjustPositionBasedOnWidth() {
        rect.setRight(15);
        
        assertEquals(10, rect.pos.x, 0.001f);
    }

    @Test
    void setBottom_shouldAdjustPositionBasedOnHeight() {
        rect.setBottom(5);
        
        assertEquals(10, rect.pos.y, 0.001f);
    }

    @Test
    void setTop_shouldAdjustPositionBasedOnHeight() {
        rect.setTop(15);
        
        assertEquals(10, rect.pos.y, 0.001f);
    }

    @Test
    void getLeft_shouldReturnLeftEdge() {
        rect.setPosition(10, 10);
        
        assertEquals(5, rect.getLeft(), 0.001f);
    }

    @Test
    void getRight_shouldReturnRightEdge() {
        rect.setPosition(10, 10);
        
        assertEquals(15, rect.getRight(), 0.001f);
    }

    @Test
    void getBottom_shouldReturnBottomEdge() {
        rect.setPosition(10, 10);
        
        assertEquals(5, rect.getBottom(), 0.001f);
    }

    @Test
    void getTop_shouldReturnTopEdge() {
        rect.setPosition(10, 10);
        
        assertEquals(15, rect.getTop(), 0.001f);
    }

    @Test
    void isInside_pointInside_shouldReturnTrue() {
        rect.setPosition(10, 10);
        Vector2 insidePoint = new Vector2(10, 10);
        
        assertTrue(rect.isInside(insidePoint));
    }

    @Test
    void isInside_pointOnEdge_shouldReturnTrue() {
        rect.setPosition(10, 10);
        Vector2 edgePoint = new Vector2(5, 10);
        
        assertTrue(rect.isInside(edgePoint));
    }

    @Test
    void isInside_pointOutside_shouldReturnFalse() {
        rect.setPosition(10, 10);
        Vector2 outsidePoint = new Vector2(100, 100);
        
        assertFalse(rect.isInside(outsidePoint));
    }

    @Test
    void isOutside_nonOverlappingRect_shouldReturnTrue() {
        rect.setPosition(0, 0);
        BaseRectangle other = new BaseRectangle(100, 100, 10, 10);
        
        assertTrue(rect.isOutside(other));
    }

    @Test
    void isOutside_overlappingRect_shouldReturnFalse() {
        rect.setPosition(5, 5);
        BaseRectangle other = new BaseRectangle(8, 8, 10, 10);
        
        assertFalse(rect.isOutside(other));
    }

    @Test
    void isOutside_touchingRect_shouldReturnFalse() {
        rect.setPosition(0, 0);
        BaseRectangle touching = new BaseRectangle(10, 0, 10, 10);
        
        assertFalse(rect.isOutside(touching));
    }
}
