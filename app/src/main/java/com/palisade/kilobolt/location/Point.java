package com.palisade.kilobolt.location;


public class Point {

    protected int pointX, pointY;

    public Point(int x, int y){
        this.pointX = x;
        this.pointY = y;
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

    protected Point setPosition(int x, int y){
        setX(x);
        setY(y);
        return this;
    }

    public void setX(int x){
        this.pointX = x;
    }
    public void setY(int y){
        this.pointY = y;
    }
    public int getX(){
        return pointX;
    }
    public int getY(){
        return pointY;
    }


}
