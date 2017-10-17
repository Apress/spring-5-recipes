package com.apress.springrecipes.court;

import java.util.Random;

public abstract class Delayer {

    private static final Random rnd = new Random();

    private Delayer() {}

    public static void delay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }

    public static void randomDelay(int max) {
        int delay = rnd.nextInt(max);
        delay(delay);
    }

    public static void randomDelay() {
        int delay = rnd.nextInt(5500);
        delay(delay);
    }
}
