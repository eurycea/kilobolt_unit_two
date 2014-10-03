package com.palisade.kilobolt.graphic;


import com.palisade.kilobolt.location.Coordinate;

import java.awt.*;

public class BackgroundSegment {
    private String mResource;
    private int width;
    private Coordinate mCoordinate;
    private ImageHolder mImageHolder;
    private int id = -1;

    public BackgroundSegment(){
        new BackgroundSegment("", 0, 0, 0);
    }
    public BackgroundSegment(String resource){
       new BackgroundSegment(resource, 0, 0, 0);
    }
    public BackgroundSegment(String resource, int width, int startX, int startY){
        this.mResource = resource;
        this.width = width;
        mCoordinate = new Coordinate(startX, startY);
        mImageHolder = ImageHolder.getInstance();
    }

    public void scrollPosition(int distance){
        mCoordinate.moveHorizontal(distance);
    }

    public Image getCurrentImage(){
        return mImageHolder.getImage(mResource);
    }

    public void update(int speed){
        scrollPosition(speed);
        if(mCoordinate.getX()<= -width){
            mCoordinate.moveRight(2*width);
        }
    }

    public void draw(Graphics graphics){
        mImageHolder.draw(graphics, getCurrentImage(),  mCoordinate.pointFromCurrentPosition());
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
