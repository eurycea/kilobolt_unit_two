package com.palisade.kilobolt.actor;


import com.palisade.kilobolt.constant.Constants;
import com.palisade.kilobolt.graphic.DrawActorInterface;
import com.palisade.kilobolt.graphic.ImageHolder;
import com.palisade.kilobolt.location.Coordinate;
import com.palisade.kilobolt.location.Point;
import com.palisade.kilobolt.stat.Mobility;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Robot implements DrawActorInterface{

    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    final int STATIC_Y_SPEED = 1;

    final int GROUND = 382;
    final int START_CENTER_X = 100;
    final int START_CENTER_Y = 383;
    final int RIGHT_MOVEMENT_BARRIER = 200;
    final int LEFT_MOVEMENT_BARRIER = 60;
    final int HORIZTONAL_CENTER_OFFSET = -61;
    final int VERTICAL_CENTER_OFFSET = -63;

    private Coordinate mCoordinate;

    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private Mobility mMobility;

    private  SpriteState mSpriteState;
    private ImageHolder sImageHolder;

    private MainCharacterInterface mMainCharacterCallback;

    public Robot(Applet app) {
        try{
            mMainCharacterCallback = (MainCharacterInterface) app;
        }catch (ClassCastException e){
            throw new ClassCastException(app.getClass() + " must implement MainCharacterInterface");
        }
        mSpriteState = SpriteState.STANDING;
        sImageHolder = ImageHolder.getInstance();

        mMobility = new Mobility(MOVESPEED, JUMPSPEED);
        mMobility.setCurrentSpeedY(STATIC_Y_SPEED);

        mCoordinate = new Coordinate(START_CENTER_X, START_CENTER_Y);
    }

    public void handleKeyPressedEvent(int eventCode){
        switch (eventCode){
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                duck();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_SPACE:
                jump();
                break;
        }
    }

    public void handleKeyReleasedEvent(int eventCode){
        switch (eventCode){
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                setDucked(false);
                break;
            case KeyEvent.VK_LEFT:
                stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                stopRight();
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    public void update(){
        updateXMovement();
        updateYMovement();
        updateJumping();
        updateCheckLeftOutOfBounds();
    }

    private void updateXMovement(){
        // Moves Character or Scrolls Background accordingly.
        if(mMobility.isMovingLeft()){
            mCoordinate.moveHorizontal(mMobility.getCurrentSpeedX());
        }
        if(mMobility.isMovingLeft() || mMobility.isStopped()){
            mMainCharacterCallback.setBackgroundSpeed(0);
        }
        if (mCoordinate.getX() <= RIGHT_MOVEMENT_BARRIER && mMobility.isMovingRight()){
            mCoordinate.moveHorizontal(mMobility.getCurrentSpeedX());
        }
        if(mMobility.isMovingRight() && mCoordinate.getX() > RIGHT_MOVEMENT_BARRIER){
            mMainCharacterCallback.setBackgroundSpeed( -mMobility.getMoveSpeedX() );
        }
    }

    private void updateCheckLeftOutOfBounds(){
        if(mCoordinate.getX() + mMobility.getCurrentSpeedX() <= LEFT_MOVEMENT_BARRIER){
            mCoordinate.setX(LEFT_MOVEMENT_BARRIER+1);
        }
    }

    private void updateJumping(){
        if (jumped){
            mMobility.accelerateY(1);
            if(mCoordinate.getY() + mMobility.getCurrentSpeedY() >= GROUND){//going to fall through ground
                mCoordinate.setY(GROUND);//land on ground
                mMobility.stopMovingY();
                jumped = false;// done jumping
                mSpriteState = SpriteState.STANDING;
            }
        } else{
        }
    }

    private void updateYMovement(){
        mCoordinate.moveVertical(mMobility.getCurrentSpeedY());
        if(mCoordinate.getY() + mMobility.getCurrentSpeedY() >= GROUND){
            mCoordinate.setY(GROUND);
        }
    }

    public void moveRight() {
        if(!ducked){
            setMovingRight(true);
            mMobility.startMovingRight();
        }
    }

    public void moveLeft() {
        if(!ducked){
            setMovingLeft(true);
            mMobility.startMovingLeft();
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
            mMobility.stopMovingX();
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
            mMobility.startMovingUp();
            jumped = true;
            mSpriteState = SpriteState.JUMPING;
        }

    }

    private void duck(){
        if(!isJumped()){
            setDucked(true);
            mMobility.stopMovingX();
        }
    }

    public Image getCurrentImage(){
        return sImageHolder.getImage(getCurrentResource());
    }

    private String getCurrentResource(){
        String resourceName = null;
        switch (mSpriteState) {
            case STANDING:
                resourceName = Constants.RES_MAIN_CHARACHTER_STANDING;
                break;
            case DUCKED:
                resourceName = Constants.RES_MAIN_CHARACHTER_DUCKED;
                break;
            case JUMPING:
                resourceName = Constants.RES_MAIN_CHARACHTER_JUMPED;
                break;
        }
        return resourceName;
    }

    @Override
    public void draw(Graphics graphics) {
        sImageHolder.draw(graphics, getCurrentImage(), buildPointForDraw());
    }

    public enum SpriteState{
        STANDING, DUCKED, JUMPING
    }

    private Point buildPointForDraw(){
        if(!isJumped() && !isDucked()){
            final int vOffset = VERTICAL_CENTER_OFFSET - Math.abs(Mobility.sinusoidalOffset(10));
            return mCoordinate.pointFromOffsetPosition(HORIZTONAL_CENTER_OFFSET, vOffset);
        }
        return mCoordinate.pointFromOffsetPosition(HORIZTONAL_CENTER_OFFSET, VERTICAL_CENTER_OFFSET);
    }

    public boolean isJumped() {
        return jumped;
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

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
        mSpriteState = jumped ? SpriteState.JUMPING : SpriteState.STANDING;
    }

}
