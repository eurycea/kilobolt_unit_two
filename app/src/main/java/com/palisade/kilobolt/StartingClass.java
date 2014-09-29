package com.palisade.kilobolt;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nicholascraig on 9/28/14.
 */
public class StartingClass extends Applet implements Runnable, KeyListener {
    private Robot mRobot;
    private Image mImage, character;
    private URL base;
    private Graphics second;

    @Override
    public void init() {
        super.init();
        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
        addKeyListener(this);

        character = getImage(Robot.getImageURL(this.getClass()));
    }

    @Override
    public void start() {
        super.start();
        mRobot = new Robot();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void run() {
        while(true){
            mRobot.update();
            repaint();
            try{
                Thread.sleep(17);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Graphics graphics) {
        if(mImage == null){
            mImage = createImage(this.getWidth(), this.getHeight());
            second = mImage.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        graphics.drawImage(mImage, 0, 0, this);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(character, mRobot.getCenterX()-61, mRobot.getCenterY()-63, this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                System.out.println("up pressed");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("down pressed");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("left pressed");
                mRobot.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("right pressed");
                mRobot.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("space pressed");
                mRobot.jump();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                log("up released");
                break;
            case KeyEvent.VK_DOWN:
                log("down released");
                break;
            case KeyEvent.VK_LEFT:
                log("left released");
                mRobot.stop();
                break;
            case KeyEvent.VK_RIGHT:
                log("right released");
                mRobot.stop();
                break;
            case KeyEvent.VK_SPACE:
                log("space released");
                break;

        }
    }
    private void log(String msg){
        System.out.println(msg);
    }
}
