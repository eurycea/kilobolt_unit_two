package com.palisade.framework.location;


import com.palisade.kilobolt.stat.Mobility;

public class Coordinate extends Point {
    private final Point mOrigin;

    public Coordinate(int x, int y){
        super(x, y);
        mOrigin = new Point(x,y);
    }

    public void moveDown(int distance){
        setY(getY() + Math.abs(distance));
    }
    public void moveUp(int distance){
        setY(getY() - Math.abs(distance));
    }
    public void moveRight(int distance){
        setX( getX() + Math.abs(distance) );
    }
    public void moveLeft(int distance){
        setX( getX() - Math.abs(distance) );
    }


    public void moveVertical(int delta){
        setY(getY() + delta);
    }
    public void moveHorizontal(int delta){
        setX( getX() + delta );
    }


    public void returnToOrigin(){
        setPosition(mOrigin.getX(), mOrigin.getY());
    }

    public Point pointFromCurrentPosition(){
        return new Point(getX(), getY());
    }
    public final Point pointFromOffsetPosition( int horizontalOffset, int verticalOffset){
        return new Point(getX()-horizontalOffset, getY()-verticalOffset);
    }

    public double distanceFromOrigin(){
        return distanceFromPoint(mOrigin);
    }

    public void update(Mobility mobility){
        updateVertical(mobility);
        updateHorizontal(mobility);
    }
    public void updateHorizontal(Mobility mobility){
        moveHorizontal(mobility.getCurrentSpeedX());
    }
    public void updateVertical(Mobility mobility){
        moveVertical(mobility.getCurrentSpeedY());
    }
}
