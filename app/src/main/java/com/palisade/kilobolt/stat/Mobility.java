package com.palisade.kilobolt.stat;


public class Mobility {
    private int currentSpeedY, currentSpeedX;
    private int moveSpeedY, moveSpeedX;

    public Mobility(int moveSpeedX, int moveSpeedY){
        setMoveSpeedX(moveSpeedX);
        setMoveSpeedY(moveSpeedY);
        setCurrentSpeedX(0);
        setCurrentSpeedY(0);
    }


    public static int sinusoidalOffset(int hoverMagnitude){
        double workingDouble = System.currentTimeMillis();
        workingDouble = workingDouble%1000;
        System.out.println(workingDouble);
        workingDouble = workingDouble / 1000;
        System.out.println(workingDouble);
        workingDouble = Math.sin(workingDouble * Math.PI*2);
        System.out.println(workingDouble);
        workingDouble = workingDouble * hoverMagnitude;
        System.out.println(workingDouble);
        return  (int) workingDouble;
    }

    public boolean isMovingLeft(){
        return getCurrentSpeedX() < 0;
    }
    public boolean isMovingRight(){
        return getCurrentSpeedX() > 0;
    }
    public boolean isStopped(){
        return getCurrentSpeedX() == 0;
    }

    public void startMovingRight(){
        setCurrentSpeedX(getMoveSpeedX());
    }
    public void startMovingLeft(){
        setCurrentSpeedX(-getMoveSpeedX());
    }
    public void stopMovingX(){
        setCurrentSpeedX(0);
    }

    public void startMovingUp(){
        System.out.println("#startMovingUp setting current speed y to "+ getMoveSpeedY());
        setCurrentSpeedY(getMoveSpeedY());
    }
    public void stopMovingY(){
        setCurrentSpeedY(0);
    }
    public void accelerateY(int acceleration){
        setCurrentSpeedY(getCurrentSpeedY() + acceleration);
    }
    public void accelerateX(int acceleration){
        setCurrentSpeedX(getCurrentSpeedX() + acceleration);
    }

    public int getCurrentSpeedX() {
        return currentSpeedX;
    }

    public void setCurrentSpeedX(int currentSpeedX) {
        this.currentSpeedX = currentSpeedX;
    }

    public int getCurrentSpeedY() {
        return currentSpeedY;
    }

    public void setCurrentSpeedY(int currentSpeedY) {
        this.currentSpeedY = currentSpeedY;
    }

    public int getMoveSpeedY() {
        return moveSpeedY;
    }

    protected void setMoveSpeedY(int moveSpeedY) {
        this.moveSpeedY = moveSpeedY;
    }

    public int getMoveSpeedX() {
        return moveSpeedX;
    }

    protected void setMoveSpeedX(int moveSpeedX) {
        this.moveSpeedX = moveSpeedX;
    }
}
