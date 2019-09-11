package edu.umb.cs.cs681.hw03;

import java.util.*;
import java.util.stream.Collectors;


public class Car 
{
	private int price;
	private int year;
	private float mileage;
	private int DominationCount;
	private String name;

	public void setDominationCount(int dominationCount) {
		DominationCount = dominationCount;
	}
	public int getDominationCount() {
		return DominationCount;
	}
	public Car(String name,int price, int year, float mileage)
	{   this.name=name;
		this.price = price;
		this.year = year;
		this.mileage = mileage;

	}
	public int getPrice()
	{
		return price;
	}

	public int getYear()
	{
		return year;
	}
	public float getMileage()
	{
		return mileage;
	}
	public String getName()
	{
		return name;
	}

public static void main(String args[])
	{		
		ArrayList<Car> usedCars = new ArrayList<Car>();
		
		usedCars.add(new Car("Audi_Q8",30000,2017,20000));
		usedCars.add(new Car("BMW",5000,2013,1750000));
		usedCars.add(new Car("Swift",20000,2011,70000));
		usedCars.add(new Car("Accord",4000,2016,40000));
		usedCars.add(new Car("Camry",2000,2006,95000));
		usedCars.add(new Car("Audi_A7",800,2017,1500));
		
		for(Car u:usedCars)
		{
			int dominationCount=0;
			for(Car ui:usedCars) {
				if(u.getMileage() > ui.getMileage() && u.getPrice() > ui.getPrice() && u.getYear() < ui.getYear()) {
					dominationCount++;
				}else if(u.getMileage() == ui.getMileage() && u.getPrice() == ui.getPrice() && u.getYear() == ui.getYear()) {
					
				}else if((u.getMileage() > ui.getMileage() || u.getPrice() > ui.getPrice()) && u.getYear() == ui.getYear()) {
					dominationCount++;
				}else if((u.getMileage() > ui.getMileage() || u.getYear() < ui.getYear()) && u.getPrice() == ui.getPrice()) {
					dominationCount++;
				}else if(u.getMileage() == ui.getMileage() && (u.getPrice() > ui.getPrice() || u.getYear() < ui.getYear())) {
					dominationCount++;
				}
				
			}
			
			u.setDominationCount(dominationCount);
		}
        
        List<Integer> collected1 = usedCars.stream()
        .map((Car car) -> car.getPrice())
        .sorted()
        .collect(Collectors.toList());
        
        for(Integer num : collected1){
            System.out.println(num.intValue() + " ");
        }
        
        List<Float> collected2 = usedCars.stream()
        .map((Car car) -> car.getMileage())
        .sorted()
        .collect(Collectors.toList());
        
        for(Float num : collected2){
            System.out.println(num.floatValue() + " ");
        }
        
        List<Integer> collected3 = usedCars.stream()
        .map((Car car) -> car.getYear())
        .sorted()
        .collect(Collectors.toList());
        
        for(Integer num : collected3){
            System.out.println(num.intValue() + " ");
        }
        
        List<Integer> collected4 = usedCars.stream()
        .map((Car car) -> car.getDominationCount())
        .sorted()
        .collect(Collectors.toList());
        
        for(Integer num : collected4){
            System.out.println(num.intValue() + " ");
        }
        
        
	}
}
