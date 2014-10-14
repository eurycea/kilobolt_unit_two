package com.palisade.framework.tile;

import com.palisade.framework.image.ImageHolder;

import java.awt.*;

public class TileMap {
    private Tile[][] mTiles;
    private Dimension mTileDimension;
    private int[][] mRawMap;
    private int rows;
    private int columns;
    private TileBuilder builder;

    public TileMap(TileBuilder tileBuilder){        
        builder = tileBuilder;
    }
    public void initializeTiles(){
        mTiles = new Tile[rows][columns];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c <columns; c++){
                mTiles[r][c] = builder.buildTile(new Point(c,r), mRawMap[r][c]);
            }
        }
    }

    public void draw(Graphics graphics, ImageHolder imageHolder){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c <columns; c++){
                mTiles[r][c].draw(graphics, imageHolder);
            }
        }
    }
    public void drawReverse(Graphics graphics, ImageHolder imageHolder){
        for(int r = rows-1; r >= 0; r--){
            for(int c = 0; c <columns; c++){
                mTiles[r][c].draw(graphics, imageHolder);
            }
        }
    }
    public void setRawTileMap(int[][] rawMap){
        mRawMap=rawMap;
        rows = mRawMap.length;
        columns = mRawMap[0].length;

    }

}
