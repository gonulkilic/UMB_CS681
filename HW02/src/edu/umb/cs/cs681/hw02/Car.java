package edu.umb.cs.cs681.hw02;

public class Car {

    private int Price;
    private int Year;
    private int Mileage;
    private String CarMaker;

    public Car(String CarMaker, int Price, int Year, int Mileage) {

        this.Year = Year;
        this.Mileage = Mileage;
        this.Price = Price;
        this.CarMaker = CarMaker;
    }

    public String getCarMaker() {
        return this.CarMaker;
    }

    public int getPrice() {
        return this.Price;
    }

    public int getYear() {
        return this.Year;
    }


    public int getMileage() {
        return this.Mileage;
    }

  

  
}
