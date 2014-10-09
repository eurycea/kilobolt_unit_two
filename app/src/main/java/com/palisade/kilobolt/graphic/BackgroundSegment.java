package com.palisade.kilobolt.graphic;


import com.palisade.framework.DrawActorInterface;
import com.palisade.framework.image.ImageHolder;
import com.palisade.framework.location.Coordinate;

import java.awt.*;

public class BackgroundSegment implements DrawActorInterface {
    private String mResource;
    private int width;
    private Coordinate mCoordinate;
    private ImageHolder sImageHolder;
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
        sImageHolder = ImageHolder.getInstance();
    }

    public void scrollPosition(int distance){
        mCoordinate.moveHorizontal(distance);
    }

    public Image getCurrentImage(){
        return sImageHolder.getImage(mResource);
    }

    public void update(int speed){
        scrollPosition(speed);
        update();
    }

    @Override
    public void draw(Graphics graphics){
        sImageHolder.draw(graphics, getCurrentImage(), mCoordinate.pointFromCurrentPosition());
    }

    @Override
    public void update() {
        if(mCoordinate.getX()<= -width){
            mCoordinate.moveRight(2*width);
        }
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
