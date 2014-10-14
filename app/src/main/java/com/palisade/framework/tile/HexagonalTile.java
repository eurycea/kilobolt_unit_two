package com.palisade.framework.tile;

import java.awt.*;

public class HexagonalTile extends ResourceTile {
    protected Point offset;

    public HexagonalTile(Point point, Dimension dimension, String resource, Point hexOffset) {
        super(point, dimension, resource);
        this.offset = hexOffset;

        double drawX;
        double drawY;
        if(getBaseLocation().getY() %2 >0) {
            drawX = mOrigin.getX()*mSize.width + offset.getX();
            drawY = ( (mOrigin.getY()/2) + 1 ) * mSize.height + offset.getY();
        } else {
            drawX = mOrigin.getX()*mSize.width;
            drawY = (mOrigin.getY()/2)*mSize.height;
        }
        mDrawLocation.setLocation( drawX, drawY );
    }

}
