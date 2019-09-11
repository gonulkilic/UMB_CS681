package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable {

    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private File File;

    public Editor(File file) {
        this.File = file;
    }

    public void setDone() {
        lock.lock();
        System.out.println("The editor is locked on setDone()");
        try {
            done=true;
        }
        finally {
            lock.unlock();
            System.out.println("The editor is unlocked on setDone()");
        }
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            System.out.println("The editor is locked on run()");
            try {
                if (done) {
                    System.out.println("Please Quit an Editor ! ");
                    break;
                }
                File.change();
                Thread.sleep(3000);
                File.save();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
                System.out.println("The editor is unlocked on run()");
            }
        }
    }

}
