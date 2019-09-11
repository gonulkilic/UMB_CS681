package edu.umb.cs.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {

    private ConcurrentSingleton() {};
    private static ConcurrentSingleton Instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    public static ConcurrentSingleton getInstance() {
        lock.lock();
        try {
            if (Instance == null)
                Instance = new ConcurrentSingleton();
        }
        finally {
            lock.unlock();
        }
        return Instance;
    }

    public static void main(String[] args) {

        Thread instance1 = new Thread(()->{
            System.out.println("Instance for Thread ID " + Thread.currentThread().getId() + "is" + ConcurrentSingleton.getInstance());
        });
        Thread instance2 = new Thread(()->{
            System.out.println("Instance for Thread ID " + Thread.currentThread().getId() + "is" + ConcurrentSingleton.getInstance());
        });
        Thread instance3 = new Thread(()->{
            System.out.println("Instance for Thread ID " + Thread.currentThread().getId() + "is" + ConcurrentSingleton.getInstance());
        });
        Thread instance4 = new Thread(()->{
            System.out.println("Instance for Thread ID " + Thread.currentThread().getId() + "is" + ConcurrentSingleton.getInstance());
        });
        Thread instance5 = new Thread(()->{
            System.out.println("Instance for Thread ID " + Thread.currentThread().getId() + "is" + ConcurrentSingleton.getInstance());
        });
        Thread instance6 = new Thread(()->{
            System.out.println("Instance for Thread ID " + Thread.currentThread().getId() + "is" + ConcurrentSingleton.getInstance());
        });

        instance1.start();
        instance2.start();
        instance3.start();
        instance4.start();
        instance5.start();
        instance6.start();
    }

}
