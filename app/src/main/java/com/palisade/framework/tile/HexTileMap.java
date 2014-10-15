package com.palisade.framework.tile;

import java.util.ArrayList;
import java.util.List;

public class HexTileMap {
    private List<List<ResourceTile>> mTiles;

    public HexTileMap(){
        mTiles = new ArrayList<>();
    }

    public List<ResourceTile> getRow(int index){
        return mTiles.get(index);
    }

    public ResourceTile getTile(int row, int column){
        return getRow(row).get(column);
    }

    public List<ResourceTile> addRow(){
        List<ResourceTile> row = new ArrayList<>();
        mTiles.add(row);
        return row;
    }
    public List<ResourceTile> addTileToRow(int index, ResourceTile tile){
        getRow(index).add(tile);
        return getRow(index);
    }
}
