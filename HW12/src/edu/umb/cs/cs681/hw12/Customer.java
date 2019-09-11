package edu.umb.cs.cs681.hw12;

public class Customer {
    private Address address;

    public Customer (Address address){this.address=address;}

    public synchronized Address getAddress(){

            return this.address;
    }
    public synchronized void setAddress(Address addr){

        this.address = addr;
    }
}
