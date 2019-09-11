package edu.umb.cs.cs681.hw16;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {

    private ArrayList<Observer> observers = new ArrayList<>();
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

    public void addObserver(Observer obs) {
        lockObs.lock();
        try {
            observers.add(obs);
        }
        finally {
            lockObs.unlock();
        }

    }

    public boolean hasChanged() {
        return Change;
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

    public void notifyObservers(Object obj) {
        Object[] arrLocal;
        lockObs.lock();
        if(!hasChanged()) 
        {
            return;
        }
        arrLocal = observers.toArray();
        Change=false;
        lockObs.unlock();
        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, obj);
    }

    public void notifyObservers() {

    }


}
