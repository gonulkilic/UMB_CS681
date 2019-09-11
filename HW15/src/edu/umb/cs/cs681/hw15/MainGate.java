package edu.umb.cs.cs681.hw15;

public class MainGate implements Runnable {

    private AdmissionControl admissionControl;

    public MainGate() {
        admissionControl = AdmissionControl.getInstance();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new MainGate());
        Thread thread2 = new Thread(new MainGate());
        Thread thread3 = new Thread(new MainGate());
        Thread thread4 = new Thread(new MainGate());
        Thread thread5 = new Thread(new MainGate());
        Thread thread6 = new Thread(new MainGate());
        Thread thread7 = new Thread(new MainGate());
        Thread thread8 = new Thread(new MainGate());
        Thread thread9 = new Thread(new MainGate());
        Thread thread10 = new Thread(new MainGate());



        thread1.start();
        thread2.start();
        Thread.sleep(100);
        thread3.start();
        Thread.sleep(100);
        thread4.start();
        Thread.sleep(100);
        thread5.start();
        thread6.start();
        thread7.start();
        Thread.sleep(100);
        thread8.start();
        thread9.start();
        Thread.sleep(100);
        thread10.start();

    }

    @Override
    public void run() {

        System.out.println("Thread ID " + Thread.currentThread().getId() + " Entered ");
        admissionControl.enter();
        admissionControl.exit();
        System.out.println("Thread ID " + Thread.currentThread().getId() + " Exited ");
        admissionControl.countCurrentVisitors();
        System.out.println("Thread ID " + Thread.currentThread().getId() + " Number of Visitors are: " + admissionControl.countCurrentVisitors());

    }
}
