package com.palisade.kilobolt;

import com.palisade.kilobolt.actor.MainCharacterInterface;
import com.palisade.kilobolt.constant.Constants;
import com.palisade.kilobolt.constant.TextConstants;
import com.palisade.kilobolt.graphic.BaseBackground;
import com.palisade.kilobolt.graphic.ImageHolder;
import com.palisade.kilobolt.actor.Robot;
import com.palisade.kilobolt.stat.Mobility;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class StartingClass extends Applet implements Runnable, KeyListener, MainCharacterInterface {
    private com.palisade.kilobolt.actor.Robot mRobot;
    private Image mImage;
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
        mRobot.draw(graphics);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        mRobot.handleKeyPressedEvent(keyEvent.getKeyCode());

        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_F:
                log("final sine offset: "+Mobility.sinusoidalOffset(15));
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        mRobot.handleKeyReleasedEvent(keyEvent.getKeyCode());
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_F:
                log("final sine offset: "+Mobility.sinusoidalOffset(15));
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
