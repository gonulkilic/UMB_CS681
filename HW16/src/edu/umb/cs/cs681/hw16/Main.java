package edu.umb.cs.cs681.hw16;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        StockQuoteObservable obs = new StockQuoteObservable();

        PieObserver P = new PieObserver();
        TableObserver T = new TableObserver();
        ThreeDObserver D = new ThreeDObserver();

        obs.addObserver(D);
        obs.addObserver(T);
        obs.addObserver(P);

        Random quote = new Random();

        String[] ticker = {"AUD","INR","EUR","TRY","USD"};


        System.out.print("OBSERVER PATTERN \n");

        for(int i=0;i<ticker.length;i++) {

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            obs.changeQuote(ticker[i], quote.nextFloat());

        }
    }
}
