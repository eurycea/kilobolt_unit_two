package com.palisade.framework.image;

import com.palisade.framework.location.Point;

import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class ImageHolder {
    private static ImageHolder sInstance;
    private static Applet sApp;

    private static HashMap<String, Image> imageHashMap = new HashMap<String, Image>();

    public static ImageHolder getInstance(Applet app){

        if(sInstance == null){
            sInstance = new ImageHolder(app);
        }
        return sInstance;
    }
    public static ImageHolder getInstance(){
        if(sApp == null){
            throw new ExceptionInInitializerError("Instance not yet initialized by applet!");
        } else {
            return sInstance;
        }
    }

    private ImageHolder(Applet app){
        sApp = app;
    }
    private URL getImageURL(String resource){
        URL url = null;
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(resource).getFile());
            url = file.toURI().toURL();
        } catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }


    public Image getImage(String resource){
        if(imageHashMap.containsKey(resource)){
            return imageHashMap.get(resource);
        }else{
            System.out.println("ImageHolder loading new image");
            Image image = sApp.getImage(getImageURL(resource));
            imageHashMap.put(resource, image);
            return image;
        }
    }

    public void clear(){
        imageHashMap.clear();
    }

    public static Applet getsApp() {
        return sApp;
    }
    public void draw(Graphics graphics, Image image, Point point){
        graphics.drawImage(image,  point.getX(),  point.getY(), sApp);
    }
}
