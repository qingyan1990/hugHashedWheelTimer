package me.aiyanxu;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class HugHashedWheelTimerTest {

    @Test
    public void notRemoveOldTest() throws InterruptedException {
        HugHashedWheelTimer timer = new HugHashedWheelTimer();
        timer.newTimeout(timeout -> System.out.println("I reach here 1 " + System.currentTimeMillis() / 1000), 1, TimeUnit.SECONDS);
        timer.newTimeout(timeout -> System.out.println("I reach here 2 " + System.currentTimeMillis() / 1000), 3, TimeUnit.SECONDS, "test");
        timer.newTimeout(timeout -> System.out.println("I reach here 3 " + System.currentTimeMillis() / 1000), 5, TimeUnit.SECONDS, "test");
        Thread.sleep(10000);
    }

    @Test
    public void removeOldTest() throws InterruptedException {
        HugHashedWheelTimer timer = new HugHashedWheelTimer();
        timer.newTimeout(timeout -> System.out.println("I reach here 1 " + System.currentTimeMillis() / 1000), 1, TimeUnit.SECONDS);
        timer.newTimeout(timeout -> System.out.println("I reach here 2 " + System.currentTimeMillis() / 1000), 3, TimeUnit.SECONDS, "test");
        timer.newTimeout(timeout -> System.out.println("I reach here 3 " + System.currentTimeMillis() / 1000), 4, TimeUnit.SECONDS, "test");
        timer.newTimeout(timeout -> System.out.println("I reach here 4 " + System.currentTimeMillis() / 1000), 5, TimeUnit.SECONDS, "test", true);
        Thread.sleep(10000);
    }
}
