package ru.codesteps.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public class MatrixUtils {

    private MatrixUtils() {
    }

    public static Matrix4 calculateTranslationMatrix(Matrix4 mat, Rectangle src, Rectangle dst) {
        float scaleX = dst.width / src.width;
        float scaleY = dst.height / src.height;
        mat.idt().translate(dst.x, dst.y, 0f).scale(scaleX, scaleY, 1f).translate(-src.x, -src.y, 0f);
        return mat;
    }
    
    public static Matrix3 calculateTranslationMatrix(Matrix3 mat, Rectangle src, Rectangle dst) {
        float scaleX = dst.width / src.width;
        float scaleY = dst.height / src.height;
        mat.idt().translate(dst.x, dst.y).scale(scaleX, scaleY).translate(-src.x, -src.y);
        return mat;
    }
}
