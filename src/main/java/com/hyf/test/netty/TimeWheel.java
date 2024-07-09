package com.hyf.test.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.ImmediateExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author baB_hyf
 * @date 2022/07/14
 */
public class TimeWheel {

    public static void main(String[] args) {
        new TimeWheel().play();
    }

    public void play() {
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(
                new ThreadFactory() {
                    private final AtomicInteger idx = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("test-hashedWheelTimer-" + idx.getAndIncrement());
                        return t;
                    }
                },
                1200L,
                TimeUnit.MILLISECONDS,
                60,
                true,
                -1,
                ImmediateExecutor.INSTANCE
        );

        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println(System.currentTimeMillis());
                timeout.timer().newTimeout(this, 1, TimeUnit.SECONDS);
            }
        }, 1, TimeUnit.SECONDS);

    }
}
