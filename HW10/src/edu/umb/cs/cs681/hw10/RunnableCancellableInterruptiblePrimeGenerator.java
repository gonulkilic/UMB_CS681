package edu.umb.cs.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeGenerator extends RunnableCancellablePrimeGenerator
        implements Runnable {

    private boolean done;
    private ReentrantLock lock;


    public RunnableCancellableInterruptiblePrimeGenerator(long from, long to) {
        super(from, to);
        this.done = false;
        this.lock = new ReentrantLock();

    }

    public void setDone(){



        lock.lock();
        System.out.println(Thread.currentThread().toString() + " locked setDone()"  );
        try {
            done = true; }
        finally {
            lock.unlock();
            System.out.println(Thread.currentThread().toString() + " unlocked setDone()");
        }
    }

    public void run(){
        for (long n = from; n <= to; n++) {

            lock.lock();
            System.out.println(Thread.currentThread().toString() + " locked run ()");
            try {
                if(done)
                {
                    System.out.println("No more prime number generation.");
                    break;
                }
                if( isPrime(n) )
                {
                    System.out.println("Prime number generation");
                    this.primes.add(n);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread get inturrupted." );
                    }
                }
            }
            finally {
                lock.unlock();
                System.out.println(Thread.currentThread().toString() + " unlocked run ()");
            }
        }
    }

    public static void main(String[] args) {

        RunnableCancellableInterruptiblePrimeGenerator ren = new RunnableCancellableInterruptiblePrimeGenerator(1 , 1000);
        Thread thread = new Thread(ren);
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();
        ren.setDone();
        try {
            thread.join(0);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        ren.getPrimes().forEach((Long prime) -> System.out.print(prime + "\n"));
        System.out.println("Total Prime numbers between 1 to 1000 found: " + ren.getPrimes().size());
    }
}
