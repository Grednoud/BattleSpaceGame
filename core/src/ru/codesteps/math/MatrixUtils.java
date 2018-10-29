package ru.codesteps.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import ru.codesteps.base.BaseRectangle;

public class MatrixUtils {

    private MatrixUtils() {
    }

    public static Matrix4 calculateTranslationMatrix(Matrix4 mat, BaseRectangle src, BaseRectangle dst) {
        float scaleX = dst.width / src.width;
        float scaleY = dst.height / src.height;
        mat.idt().translate(dst.pos.x, dst.pos.y, 0f).scale(scaleX, scaleY, 1f).translate(-src.pos.x, -src.pos.y, 0f);
        return mat;
    }
    
    public static Matrix3 calculateTranslationMatrix(Matrix3 mat, BaseRectangle src, BaseRectangle dst) {
        float scaleX = dst.width / src.width;
        float scaleY = dst.height / src.height;
        mat.idt().translate(dst.pos.x, dst.pos.y).scale(scaleX, scaleY).translate(-src.pos.x, -src.pos.y);
        return mat;
    }
}
