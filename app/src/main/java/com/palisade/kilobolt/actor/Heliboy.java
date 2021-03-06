package com.palisade.kilobolt.actor;


import com.palisade.framework.Animation;
import com.palisade.kilobolt.constant.Constants;
import com.palisade.kilobolt.stat.Mobility;

import java.awt.*;

public class Heliboy extends AbstractEnemy {
    private final int MAX_HEALTH = 20;
    private final int POWER = 5;
    private final int VERTICAL_IMAGE_OFFSET = 48;
    private final int HORIZONT_IMAGE_OFFSET = 48;
    private final int HOVER_AMPLITUDE = 10;
    private Animation mAnimation;

    public Heliboy(EnemyInterface enemyInterface, int startX, int startY){
        super(enemyInterface);
        mCoordinate.setLocation(startX, startY);
        builNormalAnimation();
    }

    private void builNormalAnimation(){
        mAnimation = new Animation(mCoordinate);
        mAnimation.addFrames(Constants.HELIBOY_NORMAL_RESOURCES, 40);
    }

    @Override
    protected String getResNormal() {
        return mAnimation.getCurrentResource();
    }

    @Override
    public void update() {
        mAnimation.update();
        super.update();
    }

    @Override
    protected String getResAttacking() {
        return Constants.RES_ENEMY_ONE_ATTACKING;
    }

    @Override
    protected String getResJumping() {
        return Constants.RES_ENEMY_ONE_JUMPING;
    }

    @Override
    protected int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    protected int getBasePower() {
        return POWER;
    }

    @Override
    protected void die() {

    }

    @Override
    protected void attack() {

    }

    @Override
    protected Point getCurrentPoint() {
        final int vOffset = VERTICAL_IMAGE_OFFSET - Math.abs(Mobility.sinusoidalOffset(HOVER_AMPLITUDE));
        return mCoordinate.pointFromOffsetPosition(HORIZONT_IMAGE_OFFSET, vOffset);
    }
}
