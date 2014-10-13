package com.palisade.framework.tile;

import com.palisade.framework.image.ImageHolder;

import java.awt.*;

public class ImageTile extends Tile{
    private Image mImage;

    public ImageTile(Point point, Dimension dimension, Image image) {
        super(point, dimension);
        mImage = image;
    }


    public Image getImage(){
        return mImage;
    }
    public void setImage(Image image){
        mImage = image;
    }

    @Override
    public void draw(Graphics graphics, ImageHolder imageHolder) {
        imageHolder.draw(graphics, getImage(), mDrawLocation);
    }
}
