package com.palisade.kilobolt.actor;

import com.palisade.kilobolt.graphic.ImageHolder;
import com.palisade.kilobolt.StartingClass;

import java.awt.*;


abstract public class AbstractEnemy {
    private StartingClass app;
    protected EnemySpriteState mSpriteState;

    protected ImageHolder mImageHolder;

    public AbstractEnemy(StartingClass app){
        this.app = app;
        mImageHolder = ImageHolder.getInstance();
    }

    public enum EnemySpriteState{
        NORMAL, ATTACKING, JUMPING;
    }

    private String getStateResource(EnemySpriteState state){
        switch(state){
            case NORMAL:
                return getResNormal();
            case ATTACKING:
                return getResAttacking();
            case JUMPING:
                return getResJumping();
        }
        return null;
    }

    public Image getCurrentImage(){
        return mImageHolder.getImage(getStateResource(mSpriteState));
    }

    protected abstract String getResNormal();
    protected abstract String getResAttacking();
    protected abstract String getResJumping();


    public void makeNormal(){
        mSpriteState = EnemySpriteState.NORMAL;
    }
    public void makeAttacking(){
        mSpriteState = EnemySpriteState.ATTACKING;
    }
    public void makeJumping(){
        mSpriteState = EnemySpriteState.JUMPING;
    }





}
