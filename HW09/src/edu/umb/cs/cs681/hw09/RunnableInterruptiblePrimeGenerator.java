package edu.umb.cs.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableInterruptiblePrimeGenerator extends InterruptiblePrimeGenerator
        implements Runnable {

    private boolean done;
    private boolean interrupted = false;
    private ReentrantLock lock;

    public RunnableInterruptiblePrimeGenerator(long from, long to) {
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
                if(interrupted)
                {
                    System.out.println("No more prime number generation.");
                    break;
                }
                if(isPrime(n))
                {
                    System.out.println("Prime number generation");
                    this.primes.add(n); }
            }
            finally {
                lock.unlock();
                System.out.println(Thread.currentThread().toString() + " unlocked run ()");
            }
        }
    }

    public static void main(String[] args) {
        RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1, 500);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.setDone();
        try {
            thread.join(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + "\n") );
        System.out.println("Total Prime numbers found between 1 to 500: " + gen.getPrimes().size());
    }
}
