package edu.umb.cs.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator	extends CancellablePrimeGenerator
        implements Runnable {

    private boolean done;
    private ReentrantLock lock;

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
        this.done = false;
        this.lock = new ReentrantLock();
    }

    public void setDone(){

  

        lock.lock();
        System.out.println(Thread.currentThread().toString() + " locked setDone()" );
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
            System.out.println("run() is locked by: " + Thread.currentThread().toString());
            try {
                if(done)
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
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1, 100000);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.setDone();
        try {
            thread.join(0);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + "\n") );
        System.out.println("Total Prime numbers : " + gen.getPrimes().size());
    }
}
