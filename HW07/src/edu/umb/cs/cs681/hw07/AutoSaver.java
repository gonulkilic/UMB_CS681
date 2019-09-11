package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver implements Runnable {

    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private File File;

    public AutoSaver(File file) {
        this.File = file;
    }

    public void setDone() {
        lock.lock();
        System.out.println("The autosave is locked on setDone()");
        try {
            done = true;
        }
        finally {
            lock.unlock();
            System.out.println("The autosave is unlocked on setDone()");
        }
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            System.out.println("The autosave is locked on run()");
            try {
                if (done) {
                    System.out.println("Please Quit Autosaver!");
                    break;
                }
                File.save();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
                System.out.println("The autosave is unlocked on run()");
            }
        }

    }

}
