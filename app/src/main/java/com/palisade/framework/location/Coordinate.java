package com.palisade.framework.location;


import com.palisade.kilobolt.stat.Mobility;

import java.awt.*;

public class Coordinate extends Point {
    private final Point mOrigin;

    public Coordinate(int x, int y){
        super(x, y);
        mOrigin = new Point(x,y);
    }

    public void moveDown(int distance){

        setLocation(getX(), getY() + Math.abs(distance));
    }
    public void moveUp(int distance){
        setLocation(getX(), getY() - Math.abs(distance));
    }
    public void moveRight(int distance){
        setLocation(getX() + Math.abs(distance), getY());
    }
    public void moveLeft(int distance){
        setLocation(getX() - Math.abs(distance), getY());
    }


    public void moveVertical(int delta){
        setLocation(getX(), (getY() + delta));
    }
    public void moveHorizontal(int delta){
        setLocation(getX() + delta, getY());
    }


    public void returnToOrigin(){
        setLocation(mOrigin.getX(), mOrigin.getY());
    }

    public Point pointFromCurrentPosition(){
        return new Point((int) getX(), (int) getY());
    }
    public final Point pointFromOffsetPosition( int horizontalOffset, int verticalOffset){
        return new Point( (int)(getX() - horizontalOffset), (int)( getY() - verticalOffset));
    }
    public double straightDistanceFromPoint(Point point){
        double distance = 0;
        double a = Math.abs(getX() - point.getX());
        double b = Math.abs(getY() - point.getY());
        distance = Math.sqrt(a*a+b*b);
        return distance;
    }

    public double distanceFromPoint(Point point){
        double distance = 0;
        distance += Math.abs(getX() - point.getX());
        distance += Math.abs(getY() - point.getY());
        return distance;
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

    public void setX(int x){
        setLocation(x, getY());
    }
    public void setY(int y){
        setLocation(getX(), y);
    }
}
