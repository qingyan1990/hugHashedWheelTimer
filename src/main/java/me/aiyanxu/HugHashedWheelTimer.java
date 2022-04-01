package me.aiyanxu;

import io.netty.util.HashedWheelTimer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import cn.hutool.core.util.StrUtil;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class HugHashedWheelTimer extends HashedWheelTimer {

    private final static Map<String, Timeout> existTasks = new ConcurrentHashMap<>();

    public HugHashedWheelTimer() {
        super();
    }

    public HugHashedWheelTimer(long tickDuration, TimeUnit unit) {
        super(tickDuration, unit);
    }

    public HugHashedWheelTimer(long tickDuration, TimeUnit unit, int ticksPerWheel) {
        super(tickDuration, unit, ticksPerWheel);
    }

    public HugHashedWheelTimer(ThreadFactory threadFactory) {
        super(threadFactory);
    }

    public HugHashedWheelTimer(ThreadFactory threadFactory, long tickDuration, TimeUnit unit) {
        super(threadFactory, tickDuration, unit);
    }

    public HugHashedWheelTimer(ThreadFactory threadFactory, long tickDuration, TimeUnit unit, int ticksPerWheel) {
        super(threadFactory, tickDuration, unit, ticksPerWheel);
    }

    public Timeout newTimeout(TimerTask task, long delay, TimeUnit unit, String taskKey) {
        return newTimeout(task, delay, unit, taskKey, false);
    }

    public Timeout newTimeout(TimerTask task, long delay, TimeUnit unit, String taskKey, boolean removeOld) {
        Timeout timeout = super.newTimeout(task, delay, unit);
        if (StrUtil.isNotBlank(taskKey)) {
            if (removeOld) {
                Timeout oldTimeout = existTasks.getOrDefault(taskKey, null);
                if (Objects.nonNull(oldTimeout)) {
                    oldTimeout.cancel();
                }
            }
            existTasks.put(taskKey, timeout);
        }
        return timeout;
    }

}
