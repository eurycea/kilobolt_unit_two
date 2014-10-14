package com.palisade.framework.tile.demo;

import com.palisade.framework.image.ImageHolder;
import com.palisade.framework.tile.*;

import java.applet.Applet;
import java.awt.*;
public class TileMapViewer extends Applet implements TileBuilder{
    private TileMap tileMap;
    private ImageHolder imageHolder;

    private static final int[][] DEFAULT_MAP= {
            {0, 1,  2,  3,  4, 5},
            {0, 0, 1,  2,  3,  4   },
                {0, 1,  2,  3,  4, 5},
            {1000, 1000, 2001,  3002,  4003,  5004   },
                {0, 1,  2,  3,  4, 5},
            {0, 0, 1,  2,  3,  4   },
                {0, 1,  2,  3,  4, 5}
    };

    @Override
    public void init() {
        imageHolder = ImageHolder.getInstance(this);
        setSize(800, 800);
        tileMap = new TileMap(this);
        tileMap.setRawTileMap(DEFAULT_MAP);
        tileMap.initializeTiles();

    }

    @Override
    public void paint(Graphics g) {
        tileMap.draw(g, imageHolder);
    }

    @Override
    public Tile buildTile(Point point, int value) {

        int elevation = value / 1000;
        int tileValue = value % 1000;
        return new ElevatedTile(point, elevation, tileValue);

    }
}
