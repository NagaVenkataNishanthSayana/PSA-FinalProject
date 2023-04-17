package org.TSP.util;

public class BenchmarkTimer {

    long startTime;

    long endTime;

    long timeElapsed;

    public void start(){
        this.startTime = System.nanoTime();
    }

    public void end(){
        endTime = System.nanoTime();
    }

    public double getElapsedTime(){
        timeElapsed = endTime - startTime;
        double secondsElapsed = (double) timeElapsed / 1_000_000_000.0;
        return secondsElapsed;
    }


}
