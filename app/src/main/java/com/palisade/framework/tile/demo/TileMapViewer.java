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
    static Map<Integer, String> hexResourceMap = new ImmutableMap.Builder<Integer, String>()
            .put(0, "hex/tileAutumn.png")
            .put(1, "hex/tileDirt.png")
            .put(2, "hex/tileGrass.png")
            .put(3, "hex/tileLava.png")
            .put(4, "hex/tileMagic.png")
            .put(5, "hex/tileRock.png")
            .put(6, "hex/tileSand.png")
            .put(7, "hex/tileSnow.png")
            .put(8, "hex/tileStone.png")
            .put(9, "hex/tileWater.png")
            .put(10, "hex/tileAutumn_full.png")
            .put(11, "hex/tileDirt_full.png")
            .put(12, "hex/tileGrass_full.png")
            .put(13, "hex/tileLava_full.png")
            .put(14, "hex/tileMagic_full.png")
            .put(15, "hex/tileRock_full.png")
            .put(16, "hex/tileSand_full.png")
            .put(17, "hex/tileSnow_full.png")
            .put(18, "hex/tileStone_full.png")
            .put(19, "hex/tileWater_full.png")
            .put(99, "hex/null.png")
            .build();


    private static final int[][] DEFAULT_MAP= {
            {0, 0, 1,  2,  3,  4   },
                {0, 1,  2,  3,  4, 99},
            {18, 18, 18, 11, 15, 11  },
                {18, 18, 11, 15, 11, 99},
            {99, 18, 18, 11, 15, 11},
                {99, 99, 99, 99, 99, 99},
            {99, 18, 18, 11, 15, 11},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 0, 1,  2,  3,  4   },
                {1, 1, 1, 1, 1, 1},
            {99, 18, 18, 11, 15, 11  },

                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 99},
            {1, 1, 1, 1, 1, 1},

    };

    private static final Dimension DEFAULT_MAP_DIMENSION = new Dimension(DEFAULT_MAP.length, DEFAULT_MAP[0].length);
    private static Dimension DEFAULT_TILE_DIMENSION = new Dimension(40, 40);
    private static Point hexOffset = new Point(32, 60);

    @Override
    public void init() {
        imageHolder = ImageHolder.getInstance(this);
        setSize(800, 800);
//        imageTest = imageHolder.getImage("teyel.png");

        tileMap = new TileMap(this, DEFAULT_TILE_DIMENSION);
        tileMap.setRawTileMap(DEFAULT_MAP);
        tileMap.initializeTiles();

    }

    @Override
    public void paint(Graphics g) {
        tileMap.drawReverse(g, imageHolder);
    }

    @Override
    public Tile buildTile(Point point, int value) {

        return new HexagonalTile(point, new Dimension(65, 20), hexResourceMap.get(value), hexOffset);
//        if (value == 1){
//            return new ImageTile(point, DEFAULT_TILE_DIMENSION, imageTest);
//        } else if(value == 0){
//            return new ResourceTile(point, DEFAULT_TILE_DIMENSION, "pink.png");
//        } else {
//            return new SolidColorTile(point, DEFAULT_TILE_DIMENSION, colorMap.get(value));
//        }
    }
}
