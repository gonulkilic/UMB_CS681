package edu.umb.cs.cs681.hw12;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Runnable CustTask = () -> {



            Customer customer = new Customer(new Address("Street 133", "North Quincy", "MA", 02171));
            Address first = customer.getAddress();
            System.out.println("\nFirst addr :" + first.toString());

            customer.setAddress(new Address("Street 7", "Dorchester", "MA", 02125));
            Address second = customer.getAddress();
            System.out.println("\nSecond addr :" + second.toString());

            customer.setAddress(customer.getAddress().change("Street 93 S", "New York", "NY", 10009));
            Address third = customer.getAddress();
            System.out.println("\nThird addr :" + third.toString());
        };




        ArrayList <Thread> allThreads = new ArrayList<Thread>();
        for(int i=0;i<20;i++){allThreads.add(new Thread(CustTask));}
        allThreads.forEach(t -> t.start());
    }

}