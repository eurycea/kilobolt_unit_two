package com.palisade.framework.util;

import java.util.HashMap;

public class ElapsedTimer {
    private static ElapsedTimer sInstance;
    private long timeOfInitialization;
    private HashMap<String, Long> times;
    private HashMap<String, Long> registrationTimes;

    public ElapsedTimer(){
        timeOfInitialization = getCurrentTime();
        times = new HashMap<>();
        registrationTimes = new HashMap<>();
    }

    public static long getCurrentTime(){
        return System.currentTimeMillis();
    }

    public void register(String key){
        final long registrationTime = getCurrentTime();
        times.put(key, registrationTime);
        registrationTimes.put(key, registrationTime);
    }

    //Updates
    public long timeOfLastUpdate(String key){
        return times.get(key);
    }
    public synchronized void updateTime(String key){
        times.put(key, getCurrentTime());
    }
    public long timeSinceLastUpdate(String key){
        return getCurrentTime() - timeOfLastUpdate(key);
    }

    //Registrations
    public long timeOfRegistration(String key){
        return registrationTimes.get(key);
    }
    public long timeSinceRegistration(String key){
        return getCurrentTime() - timeOfRegistration(key);
    }
    public void resetRegistrationTime(String key){
        registrationTimes.put(key, getCurrentTime());
    }
}
