package com.palisade.framework.tile;

import com.palisade.framework.image.ImageHolder;

import java.awt.*;

public class ResourceTile extends Tile {
    private String resource;
    public ResourceTile(Point point, Dimension dimension, String resource) {
        super(point, dimension);
        this.resource = resource;
    }

    @Override
    public void draw(Graphics graphics, ImageHolder imageHolder) {
        imageHolder.draw(graphics, imageHolder.getImage(resource), getDrawLocation());
    }
}
