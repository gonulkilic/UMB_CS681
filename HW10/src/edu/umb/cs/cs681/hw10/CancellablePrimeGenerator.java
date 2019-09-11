package edu.umb.cs.cs681.hw10;

public class CancellablePrimeGenerator extends PrimeGenerator{
    private boolean done = false;

    public CancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        done = true;
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {

            if(done)
            {
                System.out.println("Stopped generating prime numbers.");
                this.primes.clear();
                break;
            }
            if( isPrime(n) )
                { this.primes.add(n); }
        }
    }
}
