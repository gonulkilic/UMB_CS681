package edu.umb.cs.cs681.hw15;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class AdmissionControl {

    AtomicInteger currentVisitors = new AtomicInteger(0);
    private static ReentrantLock lock = new ReentrantLock();
    private static AdmissionControl Instance = null;

    private AdmissionControl() {}

    public void enter() {
        currentVisitors.incrementAndGet();
    }
    public void exit() {
        currentVisitors.decrementAndGet();
    }

    public static AdmissionControl getInstance() {
        lock.lock();
        try {
            if (Instance==null)
                Instance = new AdmissionControl();
        }
        finally {
            lock.unlock();
        }
        return Instance;
    }

    public int countCurrentVisitors() {
        return currentVisitors.intValue();
    }

}
