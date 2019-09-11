package edu.umb.cs.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {

    private HashMap<Path, Integer> Map = new HashMap<Path, Integer>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public void increment(Path path) {
        lock.writeLock().lock();
        try {
            if (Map.containsKey(path)) {
                Map.put(path, Map.get(path) + 1);
            }
            else {
                Map.put(path, 1);
            }
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public Integer getCount(Path path) {
        lock.readLock().lock();
        try {
            if (Map.containsKey(path)) {
                return Map.get(path);
            }
            else {
                return 0;
            }
        }
        finally {
            lock.readLock().unlock();
        }
    }
}