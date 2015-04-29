package com.gmail.saintgeo23.calcobot;


/**
 * Operations
 */
public class CalcOps {

    public static double percent(double a, double b) {
        return b * (a/100);
    }

    public static double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException();
        }
        return a / b;
    }

    public static double mult(double a, double b) {
        return a * b;
    }

    public static double sub(double a, double b) {
        return a - b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double percentAdd(double a, double b) {
        return a * (1 + b/100);
    }

    public static double percentSub(double a, double b) {
        return a * (1 - b/100);
    }



}
