package com.palisade.kilobolt.actor;

import com.palisade.kilobolt.StartingClass;
import com.palisade.kilobolt.graphic.ImageHolder;

import java.awt.*;
import java.util.HashMap;


public class Robot {
    private StartingClass app;
    public static final String RES_STANDING = "character.png";
    public static final String RES_DUCKED = "character_ducked.png";
    public static final String RES_JUMPED = "character_jumped.png";

    private HashMap<SpriteState, Image> imageHashMap = new HashMap<SpriteState, Image>();

    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    final int GROUND = 382;

    private int centerX = 100;
    private int centerY = 383;

    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;


    private int speedX=0;
    private int speedY=1;

    private SpriteState mSpriteState;
    private ImageHolder sImageHolder;

    public Robot(StartingClass app) {
        this.app = app;
        mSpriteState = SpriteState.STANDING;
        sImageHolder = ImageHolder.getInstance();
    }

    public void update(){
        // Moves Character or Scrolls Background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        }

        if (speedX <= 0) {
//            app.log("Do not scroll the background.");
            app.setBackgroundSpeed(0);
        }
        if (centerX <= 200 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX >200){
            app.setBackgroundSpeed(-MOVESPEED);
        }

        // Updates Y Position
        centerY += speedY;
        if (centerY + speedY >= GROUND) {
            centerY = GROUND;
        }

        // Handles Jumping
        if (jumped) {
            speedY += 1;
            if (centerY + speedY >= GROUND) {
                centerY = GROUND;
                speedY = 0;
                jumped = false;
                mSpriteState = SpriteState.STANDING;
            }
        }

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }
    }
    public void moveRight() {
        if(!ducked){
            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        if(!ducked){
            speedX = -MOVESPEED;
        }
    }

    public void stopRight(){
        setMovingRight(false);
        stop();
    }

    public void stopLeft(){
        setMovingLeft(false);
        stop();
    }

    public void stop() {
        if (!isMovingRight() && !isMovingLeft() ) {
            speedX = 0;
        }

        if (!isMovingRight() && isMovingLeft()) {
            moveLeft();
        }

        if (isMovingRight() && !isMovingLeft()) {
            moveRight();
        }
    }

    public void jump() {
        if (!jumped) {
            speedY = JUMPSPEED;
            jumped = true;
            mSpriteState = SpriteState.JUMPING;
        }

    }

    public Image getImage(){
        return sImageHolder.getImage(getCurrentResource());
    }
    private String getCurrentResource(){
        String resourceName = null;
        switch (mSpriteState) {
            case STANDING:
                resourceName = RES_STANDING;
                break;
            case DUCKED:
                resourceName = RES_DUCKED;
                break;
            case JUMPING:
                resourceName = RES_JUMPED;
                break;
        }
        return resourceName;
    }
    public enum SpriteState{
        STANDING, DUCKED, JUMPING
    }
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
        mSpriteState = ducked ? SpriteState.DUCKED : SpriteState.STANDING;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
        mSpriteState = jumped ? SpriteState.JUMPING : SpriteState.STANDING;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

}
