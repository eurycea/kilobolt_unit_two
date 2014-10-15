package com.palisade.framework.tile;

import com.google.common.collect.ImmutableMap;
import com.palisade.framework.image.ImageHolder;

import java.awt.*;
import java.util.Map;

public class ElevatedTile extends Tile {
    final static int ELEVATION_UNITS = 10;
    final static int NUMBER_OF_SUBVIEWS = 4;

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
            .put(100, "hex/tileAutumn_full.png")
            .put(101, "hex/tileDirt_full.png")
            .put(102, "hex/tileGrass_full.png")
            .put(103, "hex/tileLava_full.png")
            .put(104, "hex/tileMagic_full.png")
            .put(105, "hex/tileRock_full.png")
            .put(106, "hex/tileSand_full.png")
            .put(107, "hex/tileSnow_full.png")
            .put(108, "hex/tileStone_full.png")
            .put(109, "hex/tileWater_full.png")
            .put(99, "hex/null.png")
            .build();
    static Map<Integer, Integer> associatedResourceMap = new ImmutableMap.Builder<Integer, Integer>()
            .put(0, 108)
            .put(1, 108)
            .put(2, 101)
            .put(3, 105)
            .put(4, 101)
            .put(5, 108)
            .put(6, 101)
            .put(7, 108)
            .put(8, 101)
            .put(9, 106)
            .build();

    static String getAssociatedResource(int key){
        if(key < 100){
            return hexResourceMap.get(associatedResourceMap.get(key));
        }else{
            return hexResourceMap.get(key);
        }
    }

    static Point DEF_HEX_OFFSET = new Point(32, 10);
    static Dimension DEFAULT_HEX_DIMENSION = new Dimension(65, 25);
    int elevation;
    Point offset;
    int mResourceKey;
    boolean isInEvenRow;


    public ElevatedTile(Point point, int elevation, int mapKey) {
        super(point, DEFAULT_HEX_DIMENSION);
        this.elevation = elevation;
        mResourceKey = mapKey;
        mDrawLocation.setLocation(
                mOrigin.getX()*mSize.width,
                mOrigin.getY()* 2*mSize.height + this.elevation*-ELEVATION_UNITS);
        isInEvenRow = getBaseLocation().getY() %2 == 0;
        if(!isInEvenRow){
            mDrawLocation.translate( 32, 0);
        } else {
            mDrawLocation.translate(0, 0);
        }
    }

    @Override
    public void draw(Graphics graphics, ImageHolder imageHolder) {
        drawSubViews(graphics, imageHolder);
        imageHolder.draw(graphics, imageHolder.getImage(hexResourceMap.get(mResourceKey)), getDrawLocation());

    }
    private void drawSubViews(Graphics graphics, ImageHolder imageHolder){
        Point subDrawLocation = new Point(mDrawLocation);
        subDrawLocation.translate(0, (NUMBER_OF_SUBVIEWS+elevation)*2*ELEVATION_UNITS);//start at the bottom
        for (int i = 0; i < NUMBER_OF_SUBVIEWS + elevation; i++){
            subDrawLocation.translate(0, -2*ELEVATION_UNITS);
            imageHolder.draw(graphics, imageHolder.getImage(getAssociatedResource(mResourceKey)), subDrawLocation);
        }
    }
}
