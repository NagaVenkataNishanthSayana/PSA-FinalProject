package org.TSP.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BenchmarkTimerTest {

    private BenchmarkTimer benchmarkTimer;

    @BeforeEach
    void setUp() {
        benchmarkTimer = new BenchmarkTimer();
    }

    @Test
    void testStartAndEnd() throws InterruptedException {
        benchmarkTimer.start();
        Thread.sleep(100);
        benchmarkTimer.end();

        long startTime = benchmarkTimer.startTime;
        long endTime = benchmarkTimer.endTime;

        assertTrue(endTime >= startTime, "End time should be greater than or equal to start time");
    }

    @Test
    void testGetElapsedTime() throws InterruptedException {
        benchmarkTimer.start();
        Thread.sleep(100);
        benchmarkTimer.end();
        double elapsedTime = benchmarkTimer.getElapsedTime();

        assertTrue(elapsedTime >= 0.1, "Elapsed time should be greater than or equal to the sleep time (0.1 seconds)");
    }
}
