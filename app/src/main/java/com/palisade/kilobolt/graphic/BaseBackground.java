package com.palisade.kilobolt.graphic;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BaseBackground {
    private List<BackgroundSegment> mBackgroundSegments;
    private int width;
    private int speed;

    public BaseBackground(int width){
        mBackgroundSegments = new ArrayList<>();
        this.width = width;
    }
    public void addBackgroundSegment(BackgroundSegment segment){
        mBackgroundSegments.add(segment);
    }
    public void addBackgroundSegment(String resource){
        BackgroundSegment segment = new BackgroundSegment(resource, width, width*mBackgroundSegments.size(),0);
        segment.setId(mBackgroundSegments.size());
        System.out.println("Adding bgsegment "+ segment.getId());
        mBackgroundSegments.add(segment);
    }
    public void addBackgroundSegments(int count, String resource){
        if (count <=0 ) return;
        for(int i =0; i<count; i++){
            addBackgroundSegment(resource);
        }
    }

    public void update(){
        for(BackgroundSegment segment: mBackgroundSegments){
            segment.update(speed);
        }
    }



    public void draw(Graphics graphics){
        for(BackgroundSegment segment: mBackgroundSegments){
            segment.draw(graphics);
        }
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
