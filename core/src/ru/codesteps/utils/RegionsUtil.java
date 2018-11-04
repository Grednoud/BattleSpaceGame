package ru.codesteps.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RegionsUtil {

    private RegionsUtil() {
    }

    public static TextureRegion[] split(
            TextureRegion region,
            int rows, int columns,
            int frames) {
        if (region == null) {
            throw new RuntimeException("Split null region");
        }
        TextureRegion[] textureRegions = new TextureRegion[frames];
        int taleWidth = region.getRegionWidth() / columns;
        int taleHeight = region.getRegionHeight() / rows;

        int frame = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                textureRegions[frame] = new TextureRegion(region, taleWidth * i, taleHeight * j, taleWidth, taleHeight);
                if (frame == frames - 1) {
                    return textureRegions;
                }
                frame++;
            }
        }
        return textureRegions;
    }
}
