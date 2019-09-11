package edu.umb.cs.cs681.hw04;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

    public RunnablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void run() {
        generatePrimes();
    }

    public static void main(String[] args) {

        RunnablePrimeGenerator gen_1 = new RunnablePrimeGenerator(1, 100);
        RunnablePrimeGenerator gen_2 = new RunnablePrimeGenerator(101, 200);
        long thread1_start_time = System.currentTimeMillis();
        Thread time_1 = new Thread(gen_1);
        long thread2_start_time = System.currentTimeMillis();
        Thread time_2 = new Thread(gen_2);
        time_1.start();
        time_2.start();
        try {
            time_1.join();
            time_2.join();
        } catch (InterruptedException e) {}

        long thread1_end_time = System.currentTimeMillis();
        long thread2_end_time = System.currentTimeMillis();

        long thread1_final = thread1_end_time - thread1_start_time;
        long thread2_final = thread2_end_time - thread2_start_time;

        System.out.println("Prime # of threads: " );
        gen_1.getPrimes().forEach( (Long prime)->System.out.print(prime + "\n ") );
        gen_2.getPrimes().forEach( (Long prime)->System.out.print(prime + "\n") );
        long prime_number = gen_1.getPrimes().size() + gen_2.getPrimes().size();
        System.out.println("Total Prime #: " + prime_number);
        System.out.println("Time for thread1: " + thread1_final + " milli sec ");
        System.out.println("Time for thread2: " + thread2_final + " milli sec ");


    }

}

