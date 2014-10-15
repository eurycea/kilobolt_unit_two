package com.palisade.framework.tile;

import org.json.JSONObject;

import java.awt.*;

public class TileParser {
    public static final String JSON_ELEVATED_TILE = "elevated_tile";
    public static final String JSON_X = "x";
    public static final String JSON_Y = "y";
    public static final String JSON_ELEVATION = "elevation";
    public static final String JSON_RES_KEY = "map_key";
    public static final String JSON_RESOURCE = "resource";
    public static final String JSON_SUB_RESOURCE = "sub_resource";

    public static ElevatedTile buildElevatedTileFromJSON(String json){
        JSONObject main = new JSONObject(json);
        int elevation= 0;
        int mapKey = 0;
        int x = 0;
        int y = 0;


        if(main.has(JSON_ELEVATED_TILE)){
            System.out.println("has elevated_tile");
            JSONObject tile = main.getJSONObject(JSON_ELEVATED_TILE);
            x = tile.optInt(JSON_X);
            y = tile.optInt(JSON_Y);
            mapKey = tile.optInt(JSON_RES_KEY);
            elevation = tile.optInt(JSON_ELEVATION);
        } else{
            System.out.println("does not have elevated_tile");
        }

        return new ElevatedTile(new Point(x, y), elevation, mapKey);
    }
}
