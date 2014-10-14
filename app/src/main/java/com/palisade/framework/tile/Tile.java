package com.palisade.framework.tile;

import com.palisade.framework.image.ImageHolder;

import java.awt.*;

public abstract class Tile {
    protected Point mDrawLocation;
    protected Point mOrigin;
    protected Dimension mSize;

    public Tile(Point point, Dimension dimension){
        mOrigin = point;
        mDrawLocation = new Point();
        setDimension(dimension);
    }

    abstract public void draw(Graphics graphics, ImageHolder imageHolder);

    public Point getBaseLocation() {
        return mOrigin;
    }
    public Point getDrawLocation(){
        return mDrawLocation;
    }
    public void setBaseLocation(Point point) {
        mOrigin = point;
    }
    public void setDimension(Dimension dimension){
        mSize = dimension;
        mDrawLocation.setLocation(mOrigin.getX()*mSize.width, mOrigin.getY()*mSize.height);
    }

    protected void setDrawLocation(Point point){
        mDrawLocation = point;
    }

}
