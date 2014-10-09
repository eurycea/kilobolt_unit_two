package com.palisade.kilobolt.actor;

import com.palisade.framework.DrawActorInterface;
import com.palisade.framework.location.Coordinate;
import com.palisade.kilobolt.stat.Mobility;
import com.palisade.framework.location.Point;

import java.awt.*;

public class BaseProjectile implements DrawActorInterface{
    private final int DEFAULT_PROJECTILE_WIDTH = 15;
    private final int DEFAULT_PROJECTILE_HEIGTH = 7;
    private final int DEFAULT_PROJECTILE_SPEED_X = 10;
    private final int DEFAULT_PROJECTILE_SPEED_Y = 0;
    private final int MAX_DISTANCE_FROM_PARENT = 2000;

    private Coordinate mCoordinate;
    private Mobility mMobility;
    private ProjectileParentInterface mParent;

    public BaseProjectile(ProjectileParentInterface parent, int startX, int startY, int speedX, int speedY){
        mCoordinate = new Coordinate(startX, startY);
        mMobility = new Mobility(speedX, speedY);
        mParent = parent;
    }
    public BaseProjectile(ProjectileParentInterface parent, Point point){
        mCoordinate = new Coordinate(point.getX(), point.getY());
        mMobility = new Mobility(DEFAULT_PROJECTILE_SPEED_X, DEFAULT_PROJECTILE_SPEED_Y);
        mMobility.startMoving();
        mParent = parent;
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(mCoordinate.getX() , mCoordinate.getY(), DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGTH);
    }

    @Override
    public void update() {
        mCoordinate.update(mMobility);
        if (mParent.isOutsideView(mCoordinate, MAX_DISTANCE_FROM_PARENT)){
            mParent.remove(this);
        }
    }

    public void setSpeed(int speedX, int speedY){
        mMobility.setCurrentSpeedX(speedX);
        mMobility.setCurrentSpeedY(speedY);
    }

    public interface ProjectileParentInterface{
        public void remove(BaseProjectile projectile);
        public boolean isOutsideView(Point point, int maxDistance);
        public void fire();
    }
}
