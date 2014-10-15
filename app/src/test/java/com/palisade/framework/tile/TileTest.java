package com.palisade.framework.tile;

import junit.framework.TestCase;
import org.json.JSONException;

public class TileTest extends TestCase {

    public void testCanCreateElevatedTileFromJson() throws JSONException{
        String jsonforcreation = "{\"elevated_tile\":{\"x\":\"1\",\"y\":\"2\",\"map_key\":\"5\",\"elevation\":\"3\"}}";

        ElevatedTile testTile = TileParser.buildElevatedTileFromJSON(jsonforcreation);
       // assertEquals(3, testTile.elevation);
        assertEquals(5, testTile.mResourceKey);
        assertEquals(1, testTile.mOrigin.x);
        assertEquals(2, testTile.mOrigin.y);
    }
}
