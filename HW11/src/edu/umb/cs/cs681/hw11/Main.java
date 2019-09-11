package edu.umb.cs.cs681.hw11;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Thread> t = new ArrayList<Thread>();

        RequestHandler req = new RequestHandler();


        for (int val = 0; val < 13; val++) 
        {
            Thread t1 = new Thread(req);
            t.add(t1);
            t1.start();
            System.out.println("All the created Thread is starting: " + t1.getId());
        }
        for (Thread t2 : t) 

        {
            t2.interrupt();
            System.out.println("for:" + t2.getId() + "interrupt requested");
        }
        req.setInterruption();
    }
}
