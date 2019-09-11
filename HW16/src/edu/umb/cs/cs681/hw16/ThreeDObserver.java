package edu.umb.cs.cs681.hw16;

public class ThreeDObserver implements Observer{

    @Override
    public void update(Observable obs, Object arg) {

        StockEvent se = (StockEvent)arg;


        System.out.println(this.getClass());

        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ticker " +  se.Ticker() + " Quote   : "  + se.Quote() );




    }

}
