package edu.umb.cs.cs681.hw02;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Car mercedes = new Car("Mercedes ", 6900, 2016, 35);
        Car bentley = new Car("Bentley ", 8450, 2017, 40);
        Car cadillac = new Car("Cadillac ", 23870, 2017, 38);
        Car buick = new Car("Buick ", 4530, 2018, 32);

        ArrayList<Car> cars = new ArrayList<>();

        cars.add(mercedes);
        cars.add(bentley);
        cars.add(cadillac);
        cars.add(buick);

        System.out.println();
        System.out.println(" Car details:");
        System.out.println();
        for(Car car: cars){
            System.out.println(car.getCarMaker() + ": \n Price: " + car.getPrice() + " Year: " + car.getYear() + " Mileage:" + car.getMileage());
            System.out.println();
        }

  

        Integer minimumPrice = cars.stream().map((Car car) -> car.getPrice()).reduce(0 , (result, carPrice) -> {
           if (result == 0) {
               return carPrice;
           }
           else if (carPrice < result) {
               return carPrice;
           }
           else {
               return result;
           }
        });
        System.out.println();
        System.out.println("Min car price: " + minimumPrice);
        System.out.println();



        Integer maximumPrice = cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
            if (result == 0)
                return carPrice;
            else if (carPrice < result)
                return result;
            else
                return carPrice;
        });
        System.out.println();
        System.out.println("Max car price: " + maximumPrice);
        System.out.println();


        long count = cars.stream().filter((Car car) -> car.getPrice() < 20000).count();
        long totalCount = cars.stream().map((Car car) -> car.getCarMaker()).reduce(0, (result, carMaker) -> ++result, (result, r) -> result);
        System.out.println("Cars less than $20000 = " + count);
        System.out.println();
        System.out.println("Total Count of the cars = " + totalCount);

    }
}