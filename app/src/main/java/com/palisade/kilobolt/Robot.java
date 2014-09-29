package com.palisade.kilobolt;

import java.io.File;
import java.net.URL;

/**
 * Created by nicholascraig on 9/29/14.
 */
public class Robot {
    public static final String RES = "character.png";

    private int centerX = 100;
    private int centerY = 383;
    private boolean jumped = false;

    private int speedX=0;
    private int speedY=1;

    public void update(){
        // Moves Character or Scrolls Background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        } else if (speedX == 0) {
            System.out.println("Do not scroll the background.");

        } else {
            if (centerX <= 150) {
                centerX += speedX;
            } else {
                System.out.println("Scroll Background Here");
            }
        }

        // Updates Y Position

        if (centerY + speedY >= 382) {
            centerY = 382;
        }else{
            centerY += speedY;
        }

        // Handles Jumping
        if (jumped) {
            speedY += 1;

            if (centerY + speedY >= 382) {
                centerY = 382;
                speedY = 0;
                jumped = false;
            }

        }

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }
    }
    public void moveRight() {
        speedX = 6;
    }

    public void moveLeft() {
        speedX = -6;
    }

    public void stop() {
        speedX = 0;
    }

    public void jump() {
        if (!jumped) {
            speedY = -15;
            jumped = true;
        }

    }
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }
    public static URL getImageURL(Class clazz){
        URL url = null;
        try{
            ClassLoader classLoader = clazz.getClassLoader();
            File file = new File(classLoader.getResource(RES).getFile());
            url = file.toURI().toURL();
        } catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }
}
