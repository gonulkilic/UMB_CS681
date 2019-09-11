package edu.umb.cs.cs681.hw18;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {

	
	private ConcurrentLinkedQueue<Observer> observers = new ConcurrentLinkedQueue<>();
	private boolean Change=false;
	private ReentrantLock lockObs = new ReentrantLock();
	
	public void setChange() {
		lockObs.lock();
		try {
		this.Change=true;	
		}
		finally {
		lockObs.unlock();
		}
	}
	
	public boolean getChange() {
		return this.Change;
	}

	public void clearChanged() {
		lockObs.lock();
		try {
		this.Change=false;
		}
		finally {
			lockObs.unlock();
		}
	}
	
	public void addObserver(Observer obs) {
	  observers.add(obs);
	}
	
	public boolean hasChanged() {
		return Change;
	}
	
	
	public void notifyObservers() {
		
	}
	
	public void notifyObservers(Object obj) {
		lockObs.lock();
		if(!hasChanged()) 
		{
			return;
		}
		
		Iterator<Observer> it = observers.iterator();
		Change=false;
		lockObs.unlock();
		while( it.hasNext() ){
			it.next().update(this,obj);
			}
	}
	
}
