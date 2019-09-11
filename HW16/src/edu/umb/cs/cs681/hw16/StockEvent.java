package edu.umb.cs.cs681.hw16;

public class StockEvent {

    private String ticker;
    private float quote;

    public StockEvent(String t, float q) {

        this.ticker=t;
        this.quote=q;


    }

    public String Ticker() {

        return this.ticker;

    }


    public void setTicker(String tick) {

        this.ticker=tick;

    }

    public float Quote() {

        return this.quote;
    }


    public void setQuote(float quot) {

        this.quote=quote;
    }
}
