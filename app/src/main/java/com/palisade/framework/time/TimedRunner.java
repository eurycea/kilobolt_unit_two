package com.palisade.framework.time;

public class TimedRunner {
    private Runnable mRunnable;
    private ElapsedTimer mElapsedTimer;
    private String timerKey = getClass().getSimpleName() + hashCode();
    private long runCounter;

    public TimedRunner(Runnable runnable){
        mRunnable = runnable;
        mElapsedTimer = new ElapsedTimer();
        mElapsedTimer.register(timerKey);
        runCounter = 0;
    }

    public TimedRunner runFor(long millis){
        mElapsedTimer.updateTime(timerKey);
        while(mElapsedTimer.timeSinceLastUpdate(timerKey) < millis){
            mRunnable.run();
            runCounter++;
        }
        return this;
    }

    public long getRunCount(){
        return runCounter;
    }
    public void resetRunCount(){
        runCounter = 0;
    }

}
