package com.palisade.kilobolt.actor;


import com.palisade.kilobolt.constant.Constants;
import com.palisade.kilobolt.graphic.DrawActorInterface;
import com.palisade.kilobolt.graphic.ImageHolder;
import com.palisade.kilobolt.location.Coordinate;
import com.palisade.kilobolt.location.Point;
import com.palisade.kilobolt.stat.Health;
import com.palisade.kilobolt.stat.Mobility;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Robot implements DrawActorInterface, Health.HasHealth, BaseProjectile.ProjectileParentInterface{

    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    final int STATIC_Y_SPEED = 1;
    final int MAX_HEALTH = 100;
    final int GROUND = 382;
    final int START_CENTER_X = 100;
    final int START_CENTER_Y = 383;
    final int RIGHT_MOVEMENT_BARRIER = 200;
    final int LEFT_MOVEMENT_BARRIER = 60;
    final int HORIZTONAL_CENTER_OFFSET = 61;
    final int VERTICAL_CENTER_OFFSET = 63;

    private final int PROJECTILE_DRAW_OFFSET_VERTICAL = 25;
    private final int PROJECTILE_DRAW_OFFSET_HORIZONTAL = -45;

    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private SpriteState mSpriteState;
    private Mobility mMobility;
    private Coordinate mCoordinate;
    private Health mHealth;
    private ImageHolder sImageHolder;
    private MainCharacterInterface mMainCharacterCallback;
    private ArrayList<BaseProjectile> mProjectiles;

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
        mHealth = new Health(MAX_HEALTH);
        mProjectiles = new ArrayList<>();
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
            case KeyEvent.VK_A:
                fire();
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
        updateProjectiles();
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

    private void updateProjectiles(){
        try{
            if(mProjectiles.size() > 0){
                for(BaseProjectile projectile : mProjectiles){
                    projectile.update();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
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
        sImageHolder.draw(graphics, getCurrentImage(), getCurrentPoint());
        drawProjectiles(graphics);
    }
    private void drawProjectiles(Graphics graphics){
        for(BaseProjectile projectile: mProjectiles){
            projectile.draw(graphics);
        }
    }

    @Override
    public void takeDamage(int damage) {
        mHealth.damage(damage);
    }

    @Override
    public void remove(BaseProjectile projectile) {

    }

    @Override
    public boolean isOutsideView(Point point, int maxDistance) {
        return mCoordinate.distanceFromPoint(point) > maxDistance;
    }

    @Override
    public void fire() {
        if(!isDucked()){
            mProjectiles.add(new BaseProjectile(this, mCoordinate.pointFromOffsetPosition(PROJECTILE_DRAW_OFFSET_HORIZONTAL, PROJECTILE_DRAW_OFFSET_VERTICAL)));
        }
    }

    public enum SpriteState{
        STANDING, DUCKED, JUMPING
    }

    private Point getCurrentPoint(){
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
    public Coordinate getCoordinate(){
        return mCoordinate;
    }

}
