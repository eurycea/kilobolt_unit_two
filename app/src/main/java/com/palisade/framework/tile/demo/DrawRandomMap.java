package com.palisade.framework.tile.demo;

import com.google.common.collect.ImmutableMap;

import java.applet.Applet;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DrawRandomMap extends Applet {
    static int[][] tilemap;
    static int rows, columns;
    static HashMap<Integer, Color> colorHashMap = new HashMap<>();

    static Map<Integer, Color> colorMap = ImmutableMap.of(
        0, Color.RED,
        1, Color.WHITE,
        2, Color.BLUE,
        3, Color.GREEN,
        4, Color.YELLOW
    );

    @Override
    public void init() {
        super.init();
        //initColorHashMap();
        setSize(new Dimension(800, 480));
        setBackground(Color.BLACK);
        createTileMap();
    }

    static void initColorHashMap(){
        colorHashMap.put(0, Color.RED);
        colorHashMap.put(1, Color.WHITE);
        colorHashMap.put(2, Color.BLUE);
        colorHashMap.put(3, Color.GREEN);
        colorHashMap.put(4, Color.YELLOW);
    }

    @Override
    public void paint(Graphics graphics) {
        System.out.println("painting");
        paintTileMap(graphics);
    }

    private void createTileMap() {
        System.out.println("creating");
        rows = 30;
        columns = 50;
        tilemap = new int[rows][columns];

        Random random = new Random();
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                tilemap[row][column]=random.nextInt(5);
                System.out.print(tilemap[row][column]);
            }
            System.out.println("");
        }

    }

    private void paintTileMap(Graphics graphics){
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                int tileValue= tilemap[row][column];
                int scaledX = 16*column;
                int scaledY= 16*row;
                graphics.setColor(colorMap.get(tileValue));
                graphics.fillRect(scaledX, scaledY, 16, 16);
                System.out.print(tileValue);
            }
            System.out.println("");
        }
    }

}
