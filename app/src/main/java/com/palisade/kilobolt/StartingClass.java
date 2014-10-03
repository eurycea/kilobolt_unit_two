package com.palisade.kilobolt;

import com.palisade.kilobolt.actor.MainCharacterInterface;
import com.palisade.kilobolt.constant.Constants;
import com.palisade.kilobolt.constant.TextConstants;
import com.palisade.kilobolt.graphic.BaseBackground;
import com.palisade.kilobolt.graphic.ImageHolder;
import com.palisade.kilobolt.actor.Robot;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class StartingClass extends Applet implements Runnable, KeyListener, MainCharacterInterface {
    private com.palisade.kilobolt.actor.Robot mRobot;
    private Image mImage;
    private Image mCharacterImage;
    private Graphics second;
    private BaseBackground mBackground;
    private ImageHolder sImageHolder;
    private final int BACKGROUND_WIDTH = 2160;

    @Override
    public void init() {
        super.init();
        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle(TextConstants.APPLET_NAME);
        addKeyListener(this);
        sImageHolder = ImageHolder.getInstance(this);
        mBackground = new BaseBackground(BACKGROUND_WIDTH);
    }

    @Override
    public void start() {
        super.start();
        mBackground.addBackgroundSegments(2, Constants.RES_BACKGROUND);
        mRobot = new Robot(this);
        mCharacterImage = mRobot.getImage();
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
            mCharacterImage = mRobot.getImage();
            mBackground.update();
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
        mBackground.draw(graphics);
        graphics.drawImage(mCharacterImage, mRobot.getCenterX()-61, mRobot.getCenterY()-63, this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                if(!mRobot.isJumped()){
                    mRobot.setDucked(true);
                    mRobot.setSpeedX(0);
                }
                break;
            case KeyEvent.VK_LEFT:
                mRobot.moveLeft();
                mRobot.setMovingLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                mRobot.moveRight();
                mRobot.setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                mRobot.jump();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                mRobot.setDucked(false);
                break;
            case KeyEvent.VK_LEFT:
                mRobot.stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                mRobot.stopRight();
                break;
            case KeyEvent.VK_SPACE:
                break;

        }
    }

    @Override
    public void setBackgroundSpeed(int speed) {
        mBackground.setSpeed(speed);
    }

    public void log(String msg){
        System.out.println(msg);
    }

}
