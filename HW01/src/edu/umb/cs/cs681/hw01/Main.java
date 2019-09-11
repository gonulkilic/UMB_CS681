package edu.umb.cs.cs681.hw01;

public class Main {

    public static void main(String[] args){
        Observable obs = new Observable();

        obs.addObserver((observer, obj) -> {System.out.println(obj.toString()); });
        obs.setChanged(true);
        obs.notifyObservers("Observer  has informed.");
        System.out.println("Removing an Observer.");
        System.out.println();
        obs.deleteObserver((observer, obj) -> {obs.getObservers(); });
        System.out.println("Send another notification to the Observer.");
        System.out.println();
        obs.setChanged(true);
        obs.notifyObservers("Observer informed.");

    }
}
