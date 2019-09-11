package edu.umb.cs.cs681.hw04;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {
    protected long from, to;
    protected List<Long> primes;

    public PrimeGenerator(long from, long to){
        if(from >= 1 && to >= from){
            this.primes = new ArrayList<Long>();
            this.from = from;
            this.to = to;
        }else{
            throw new RuntimeException("Incorrect input # : from=" + from + " to=" + to);
        }
    }

    public List<Long> getPrimes(){ return primes; };

    protected boolean isPrime(long n){
        
        if(n == 1)
            { return false; }
        
        if( n > 2 && isEven(n) )
            { return false; }
        long i;

        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--)
        {}
 
        if (i == 1)
            { return true; }

        else
            { return false; }
    }

    protected boolean isEven(long n){
        if(n%2 == 0)
            { return true; }
        else
            { return false; }
    }

 

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            if( isPrime(n) )
                { primes.add(n); }
        }
    }

    public static void main(String[] args) {
        PrimeGenerator generator = new PrimeGenerator(1, 100);
        generator.generatePrimes();
        generator.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + generator.getPrimes().size() + " prime numbers are found.");

    }
}
