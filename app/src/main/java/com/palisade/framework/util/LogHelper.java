package com.palisade.framework.util;

public class LogHelper {

    public static void log(CharSequence sequence){
        System.out.println(sequence);
    }
    public static void log(Object object, CharSequence sequence){
        final String c = object.getClass().getSimpleName();
        final int hc = object.hashCode();
        System.out.println(c + hc + " " + sequence);
    }
    public static void nullTest(String description, Object testObject){
        if(testObject ==null){
            log(description + " is null");
        } else{
            log(description + " is not null");
        }
    }
}
