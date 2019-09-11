package edu.umb.cs.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class File {

    private boolean changed = false;
    private ReentrantLock lock = new ReentrantLock();

    public void save() {
        lock.lock();
        System.out.println(" The file is locked on save()");
        try {
            if(!changed) 
            {
                return;
            }
            if (changed) 
            {
                if (Thread.currentThread().getId() == 15) 
                {
                    System.out.println("EDITOR Thread saved the changes");
                }
                else 
                {
                    System.out.println("AUTOSAVER Thread saved the changes");
                }
                changed = false;
            }
        }
        finally {
            lock.unlock();
            System.out.println(" The file is unlocked on save()");
        }
    }
    public void change() {
        lock.lock();
        System.out.println(" The file is locked on change()");
        try {
            System.out.println("Changes applied by EDITOR Thread applied the changes");
            changed = true;
        }
        finally {
            lock.unlock();
            System.out.println(" The file is unlocked on change()");
        }
    }

    public static void main(String[] args) throws Exception {

        File aFile = new File();
        Editor editor = new Editor(aFile);
        AutoSaver autoSave = new AutoSaver(aFile);
        Thread editorThread = new Thread(editor);
        Thread autoSaveThread = new Thread(autoSave);
        editorThread.start();
        autoSaveThread.start();
        Thread.sleep(1000);
        editor.setDone();
        autoSave.setDone();
        editorThread.join();
        autoSaveThread.join();
    }
}
