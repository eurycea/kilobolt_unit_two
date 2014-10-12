package com.palisade.framework.util;

import com.palisade.framework.time.ElapsedTimer;
import com.palisade.framework.time.TimedRunner;
import junit.framework.TestCase;

public class TImeUtilTest extends TestCase {
    private final String DEFAULT_TIME_KEY = "default_time_key";
    private final static Object lock = new Object();
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testTimeUtilRegistersTimes() throws InterruptedException{
        synchronized (lock) {
            long testInitalTime = System.currentTimeMillis();
            ElapsedTimer elapsedTimer = new ElapsedTimer();
            elapsedTimer.register(DEFAULT_TIME_KEY);
            Thread.sleep(2000);
            long lastTime = elapsedTimer.timeOfLastUpdate(DEFAULT_TIME_KEY);
            assertTrue(lastTime >= testInitalTime);
        }
    }

    public void testTimeUtilCanUpdateRegistrees() throws InterruptedException{
        synchronized (lock){
            long testInitalTime = System.currentTimeMillis();
            ElapsedTimer elapsedTimer = new ElapsedTimer();
            elapsedTimer.register(DEFAULT_TIME_KEY);
            Thread.sleep(2000);
            long lastTime = elapsedTimer.timeSinceLastUpdate(DEFAULT_TIME_KEY);
            assertTrue(lastTime >= 2000);
            elapsedTimer.updateTime(DEFAULT_TIME_KEY);
            Thread.sleep(3000);
            lastTime = elapsedTimer.timeSinceLastUpdate(DEFAULT_TIME_KEY);
            assertTrue(lastTime >=2000 && lastTime<= System.currentTimeMillis() - testInitalTime);
        }
    }

    public void testTimeUtilCanTrackMultipleRegistrations() throws InterruptedException{
        synchronized (lock){
            final String secondKey = DEFAULT_TIME_KEY+"different";
            ElapsedTimer elapsedTimer = new ElapsedTimer();

            elapsedTimer.register(DEFAULT_TIME_KEY);
            Thread.sleep(2000);
            elapsedTimer.register(secondKey);
            Thread.sleep(2000);

            long lastTime1 = elapsedTimer.timeSinceLastUpdate(DEFAULT_TIME_KEY);
            long lastTime2 = elapsedTimer.timeSinceLastUpdate(secondKey);
            assertFalse(lastTime1 == lastTime2);
            assertTrue(elapsedTimer.timeSinceLastUpdate(DEFAULT_TIME_KEY) >=4000);
            assertTrue(elapsedTimer.timeSinceLastUpdate(secondKey) >= 2000 && elapsedTimer.timeSinceLastUpdate(secondKey) < 4000 );
        }
    }

    public void testTimedRunner() throws InterruptedException{
        synchronized (lock){
            int counter = 0;
            ElapsedTimer timer = new ElapsedTimer();

            timer.register("testTimedRunner");

            TimedRunner timedRunner = new TimedRunner(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });

            timer.updateTime("testTimedRunner");
            timedRunner.runFor(1000);
            assertTrue(timedRunner.getRunCount() == 10);

            timedRunner.resetRunCount();
            assertTrue(timedRunner.getRunCount() == 0);

            timedRunner.runFor(1);
            assertTrue(timedRunner.getRunCount() == 1);

            timedRunner.resetRunCount();
            timedRunner.runFor(1010);
            assertTrue(timedRunner.getRunCount() == 11);
        }
    }


}
