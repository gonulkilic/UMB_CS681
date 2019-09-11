package edu.umb.cs.cs681.hw18;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {

	 private Map<String,Float> Market = new HashMap<String,Float>();
	 private ReentrantLock lockTQ = new ReentrantLock();
	 
	 
	 public void changeQuote(String s,float f) {
		 lockTQ.lock();
		 try {
		 Market.put(s, f);
		 
		 this.setChange();
		 
		 this.notifyObservers(new StockEvent(s,f));
		 }
		 finally {
		 lockTQ.unlock();
		 }
		 
	 }
	
	
}
