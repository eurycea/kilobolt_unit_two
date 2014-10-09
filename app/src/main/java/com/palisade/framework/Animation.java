package com.palisade.framework;

import com.palisade.framework.image.ImageHolder;
import com.palisade.framework.util.ElapsedTimer;
import com.palisade.framework.location.Point;

import java.awt.*;
import java.util.ArrayList;

public class Animation {
    private ImageHolder mImageHolder;
    private ElapsedTimer mElapsedTimer;
    private ArrayList<AnimationFrame> frames;
    private Point mPoint;
    private int currentFrame;
    private long animTime;
    private long totalDuration;
    private String timerRegistrationKey;

    public Animation(Point point) {
        mImageHolder = ImageHolder.getInstance();
        mPoint = point;
        this.frames = new ArrayList<>();
        this.totalDuration = 0;
        mElapsedTimer = new ElapsedTimer();
        timerRegistrationKey = getClass().getSimpleName() + this.hashCode();
        mElapsedTimer.register(timerRegistrationKey);
        synchronized (this){
            animTime = 0;
            currentFrame = 0;
        }
    }
    public synchronized void addFrame(Image image, long duration){
        totalDuration += duration;
        frames.add(new ImageAnimationFrame( image, totalDuration));
    }
    public synchronized void addFrame(String resource, long duration){
        totalDuration += duration;
        frames.add(new ResourceAnimationFrame( resource, duration));
    }



    private AnimationFrame getFrame(int index){
        if (index < frames.size()){
            return frames.get(index);
        }
        return null;
    }
    private AnimationFrame getCurrentFrame(){
        if(currentFrame > frames.size()){
            currentFrame = 0;
        }
        return  getFrame(currentFrame);
    }


    public void draw(Graphics graphics, Point point) {
        getCurrentFrame().draw(graphics, mImageHolder, point);
    }
    private synchronized void drawWithTime(){

    }


    public void update() {
        if (frames.size() > 1){
            //animtime == timeSinceRegistration
            mElapsedTimer.updateTime(timerRegistrationKey);
            if(mElapsedTimer.timeSinceRegistration(timerRegistrationKey) > totalDuration){
                mElapsedTimer.resetRegistrationTime(timerRegistrationKey);
                currentFrame = 0;
            } else {
                while (mElapsedTimer.timeSinceRegistration(timerRegistrationKey) > getCurrentFrame().getEndTime()){
                    currentFrame++;
                }
            }
        }
    }
    private synchronized void updateWithTime(long elapsedTime){
        if(frames.size() > 1){
            animTime  += elapsedTime;
            if(animTime >= elapsedTime){
                animTime = animTime % totalDuration;
                currentFrame = 0;
            }
            while (animTime > getFrame(currentFrame).getEndTime()){
                currentFrame++;
            }
        }
    }

    private abstract class AnimationFrame{
        private long endTime;

        public AnimationFrame( long duration){
            setEndTime(duration);
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getEndTime(){
            return endTime;
        }
        abstract Image getImage();

        abstract void draw(Graphics graphics, ImageHolder imageHolder, Point point);


    }

    private class ImageAnimationFrame extends AnimationFrame{
        private Image image;
        public ImageAnimationFrame( Image  image, long duration) {
            super( duration);
            setImage(image);
        }

        @Override
        public Image getImage() {
            return image;
        }

        @Override
        void draw(Graphics graphics, ImageHolder imageHolder, Point point) {
            imageHolder.draw(graphics, getImage(), point);
        }

        public void setImage(Image image) {
            this.image = image;
        }
    }

    private class ResourceAnimationFrame extends AnimationFrame{
        private ImageHolder mImageHolder;
        private String resourceString;
        public ResourceAnimationFrame( String resource, long duration) {
            super( duration);
            setResourceString(resource);
            mImageHolder = ImageHolder.getInstance();
        }

        public String getResourceString() {
            return resourceString;
        }

        public void setResourceString(String resourceString) {
            this.resourceString = resourceString;
        }

        @Override
        Image getImage() {
            return mImageHolder.getImage(resourceString);
        }

        @Override
        void draw(Graphics graphics, ImageHolder imageHolder, Point point) {
            mImageHolder.draw(graphics, getImage(), point);
        }
    }
}
