package com.palisade.framework.tile;

import com.palisade.framework.image.ImageHolder;

import java.awt.*;

public class SolidColorTile extends Tile {
    private Color mColor;

    public SolidColorTile(Point point, Dimension dimension, Color color) {
        super(point, dimension);
        mColor = color;
    }

    @Override
    public void draw(Graphics graphics, ImageHolder imageHolder) {
        graphics.setColor(mColor);
        graphics.fillRect((int) mDrawLocation.getX(), (int) mDrawLocation.getY(), mSize.width, mSize.height);
    }
}
