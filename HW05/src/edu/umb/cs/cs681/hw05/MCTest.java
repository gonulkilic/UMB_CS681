package edu.umb.cs.cs681.hw05;

import java.util.ArrayList;

public class MCTest {

    public static void main(String[] args) throws Exception {

        long start_time = System.currentTimeMillis();

        ArrayList<Thread> threads = new ArrayList<Thread>();

        final long nTimes  = Long.parseLong("2000000");
        final int nThread = Integer.parseInt("10");

        for (int i = 0; i< nThread ; i++) {
            Thread t = new Thread(
            		() -> {
                        int n = 25;
                        for (long j = 0; j < nTimes; j++) {
                            n *= 25;
                        }
                    });
            threads.add(t);
            t.start();
        }

        for (Thread t: threads) {
            t.join();
        }

        long end_time = System.currentTimeMillis();
        long final_time = end_time - start_time;

        System.out.println("MCTest:");
        System.out.println("Number of Times (25 * 25) calculations done: "  + nTimes);
        System.out.println("Number of Threads used:  "  + nThread);
        System.out.println("Time: " + final_time + " milli seconds ");
    }
}
