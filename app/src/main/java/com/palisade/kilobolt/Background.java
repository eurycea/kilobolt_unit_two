package com.palisade.kilobolt;

import java.io.File;
import java.net.URL;

/**
 * Created by nicholascraig on 9/29/14.
 */
public class Background {

    public static final String RES = "background.png";

    private int x;
    private int y;
    private int speedX;

    public Background(int x, int y){
        this.x = x;
        this.y = y;
        speedX = 0;
    }
    public void update(){
        x += speedX;

        if(x <= -2160){
            x += 4320;
        }
    }

    public int getY() {
        return y;
    }

    public int getX(){
        return x;
    }

    public int getSpeedX(){
        return speedX;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }


    public static URL getImageURL(Object object){
        URL url = null;
        try{
            ClassLoader classLoader = object.getClass().getClassLoader();
            File file = new File(classLoader.getResource(RES).getFile());
            url = file.toURI().toURL();
        } catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

}
