package com.palisade.kilobolt.stat;

/**
 * Created by nicholascraig on 10/4/14.
 */
public class Mobility {
    private int maxSpeed, currentSpeed;

    public static double sinusoidalOffset(int hoverMagnitude){
        long currentMillis = System.currentTimeMillis();
        currentMillis = currentMillis%1000;
        System.out.println("currentMillis%1000 = "+currentMillis);

        double fractionOfSecond = (double) currentMillis / 1000;
        System.out.println("(double) currentMillis / 1000 = "+fractionOfSecond);

        double sinOfFraction = Math.sin(fractionOfSecond);
        System.out.println("Math.sin(fractionOfSecond) = "+sinOfFraction);

        double magnified = sinOfFraction * hoverMagnitude;
        System.out.println("sinOfFraction * hoverMagnitude = "+magnified);

        int normalized = (int) magnified;
        System.out.println("(int) magnified = "+normalized);

        return fractionOfSecond;
    }

}
