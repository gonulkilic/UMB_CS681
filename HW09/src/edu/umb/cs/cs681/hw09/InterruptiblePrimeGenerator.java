package edu.umb.cs.cs681.hw09;

public class InterruptiblePrimeGenerator extends PrimeGenerator{
	
	public InterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
	}
			
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {


			if(Thread.interrupted()){
				System.out.println("Because of thread interruption prime number generation is stopped.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}	
}
