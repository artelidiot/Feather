package me.artel.feather.math;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class Timer {
    @Getter
    private final TimeUnit unit;
    @Getter
    private final long start;

    /**
     * Start a new timer from the current system time
     *
     * @param unit - The time unit to receive the result in
     */
    public Timer(TimeUnit unit) {
        this.unit = unit;
        this.start = System.nanoTime();
    }

    /**
     * Start a new timer from the specified starting time
     *
     * @param unit - The time unit to receive the result in
     * @param start - The time to start from
     */
    public Timer(TimeUnit unit, long start) {
        this.unit = unit;
        this.start = start;
    }

    /**
     * Get the elapsed time
     *
     * @return - The elapsed time since the start, converted to the specified time unit
     */
    public long get() {
        return TimeUnit.NANOSECONDS.convert(System.nanoTime() - start, unit);
    }
}