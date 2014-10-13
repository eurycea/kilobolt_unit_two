package com.palisade.framework.tile.demo;

import com.google.common.collect.ImmutableMap;
import com.palisade.framework.image.ImageHolder;
import com.palisade.framework.tile.*;

import java.applet.Applet;
import java.awt.*;
import java.util.Map;

public class TileMapViewer extends Applet implements TileBuilder{
    private TileMap tileMap;
    private ImageHolder imageHolder;
    private Image imageTest;
    static Map<Integer, Color> colorMap = ImmutableMap.of(
            0, Color.RED,
            1, Color.WHITE,
            2, Color.BLUE,
            3, Color.GREEN,
            4, Color.YELLOW
    );
    private static final int[][] DEFAULT_MAP= {
            {0,1,2,3,4},
            {1,0,1,2,3},
            {2,1,0,1,2},
            {3,2,1,0,1}};

    private static final Dimension DEFAULT_MAP_DIMENSION = new Dimension(DEFAULT_MAP.length, DEFAULT_MAP[0].length);
    private static Dimension DEFAULT_TILE_DIMENSION = new Dimension(40, 40);

    @Override
    public void init() {
        imageHolder = ImageHolder.getInstance(this);

        imageTest = imageHolder.getImage("teyel.png");

        tileMap = new TileMap(this, DEFAULT_TILE_DIMENSION);
        tileMap.setRawTileMap(DEFAULT_MAP);
        tileMap.initializeTiles();

    }

    @Override
    public void paint(Graphics g) {
        tileMap.draw(g, imageHolder);
    }

    @Override
    public Tile buildTile(Point point, int value) {
        if (value == 1){
            return new ImageTile(point, DEFAULT_TILE_DIMENSION, imageTest);
        } else if(value == 0){
            return new ResourceTile(point, DEFAULT_TILE_DIMENSION, "pink.png");
        } else {
            return new SolidColorTile(point, DEFAULT_TILE_DIMENSION, colorMap.get(value));
        }
    }
}
