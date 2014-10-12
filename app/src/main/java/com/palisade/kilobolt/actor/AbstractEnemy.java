package com.palisade.kilobolt.actor;

import com.palisade.framework.DrawActorInterface;
import com.palisade.framework.location.Coordinate;
import com.palisade.framework.image.ImageHolder;
import com.palisade.kilobolt.stat.Defense;
import com.palisade.kilobolt.stat.Defense.HasArmor;
import com.palisade.kilobolt.stat.Health;
import com.palisade.kilobolt.stat.Health.HasHealth;
import com.palisade.kilobolt.stat.Mobility;
import com.palisade.kilobolt.stat.Offense;
import com.palisade.kilobolt.actor.SpriteStates.EnemySpriteState;

import java.awt.*;


abstract public class AbstractEnemy implements DrawActorInterface, HasHealth{
    protected EnemySpriteState mSpriteState;
    protected Offense mOffense;
    protected Health mHealth;
    protected Coordinate mCoordinate;
    protected Mobility mMobility;
    protected EnemyInterface mEnemyInterfaceCallback;
    protected ImageHolder mImageHolder;

    public AbstractEnemy(EnemyInterface enemyInterface){
        mEnemyInterfaceCallback = enemyInterface;
        mImageHolder = ImageHolder.getInstance();
        mOffense = new Offense(getBasePower());
        mHealth = new Health(getMaxHealth());
        mMobility = new Mobility(0,0);
        mCoordinate = new Coordinate(0, 0);
        mSpriteState = EnemySpriteState.NORMAL;
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
        throw new IllegalArgumentException("Invalid EnemeySpriteState!");
    }

    public Image getCurrentImage(){
        return mImageHolder.getImage(getStateResource(mSpriteState));
    }

    protected abstract String getResNormal();
    protected abstract String getResAttacking();
    protected abstract String getResJumping();
    protected abstract int getMaxHealth();
    protected abstract int getBasePower();
    protected abstract void die();
    protected abstract void attack();

    public void update(){
        mCoordinate.moveHorizontal(mEnemyInterfaceCallback.getReferenceSpeed());
    }

    @Override
    public void draw(Graphics graphics){
        mImageHolder.draw(graphics, getCurrentImage(), getCurrentPoint());
    }

    @Override
    public void takeDamage(int damage) {
        int damageToTake = damage;
        try{
            HasArmor armored = (Defense.HasArmor) this;
            damageToTake = armored.reduceDamage(damage);
        }catch (ClassCastException e){
            //
        }
        mHealth.damage(damageToTake);
    }

    protected Point getCurrentPoint(){
        return mCoordinate;
    }

    protected void makeNormal(){
        mSpriteState = EnemySpriteState.NORMAL;
    }
    protected void makeAttacking(){
        mSpriteState = EnemySpriteState.ATTACKING;
    }
    protected void makeJumping(){
        mSpriteState = EnemySpriteState.JUMPING;
    }

    protected void setHealth(Health health){
        mHealth = health;
    }
    protected void setCoordinate(Coordinate coordinate){
        mCoordinate = coordinate;
    }
    protected void setOffense(Offense offense){
        mOffense = offense;
    }
    protected void setMobility(Mobility mobility){
        mMobility = mobility;
    }

    public interface EnemyInterface{
        public int getReferenceSpeed();
        public boolean isInRangeOfMainCharacter(Coordinate coordinate, int attackRange);
    }

}
