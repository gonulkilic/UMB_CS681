package edu.umb.cs.cs681.hw01;

import java.util.LinkedList;

public class Observable {

    private LinkedList<Observer> observers = new LinkedList<>();

    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void deleteObserver(Observer obs){
        observers.remove(obs);
    }

    public Observer getObservers(){
        return this.observers.get(0);
    }

    private boolean Changed = false;

    public void setChanged(boolean change){
        this.Changed = change;
    }

    public boolean hasChanged() {
        return this.Changed;
    }

    public void clearChanged(){
        this.Changed = false;
    }

    public void notifyObservers(Object obj){
        if (hasChanged()){
            System.out.println("Inform all the Observers.");
            System.out.println();

            for(Observer obs: observers){
                for(int i=0; i<=observers.size(); i++){
                    System.out.println("New record for Observer" +i+ ",is " +obs);
                }
                obs.update(this, obj);
            }
            this.clearChanged();

        }
    }
}
