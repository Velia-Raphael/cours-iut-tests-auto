package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.List;

public class Bar {
    private String name;
    private int capacity;
    private List<Customer> customers;
    private boolean isFull;
    private static final int COCKTAIL_PRICE = 10;

    public Bar(String name, int capacity) {
        this.name = name;
        this.customers = new ArrayList<>();
        this.isFull = false;
        this.capacity = capacity;
    }

    public boolean canEnter(int numberOfPeople) {
        return customers.size() + numberOfPeople <= capacity;
    }

    public void addCustomers(Customer... newCustomers) {
        for (Customer customer : newCustomers) {
            if (customers.size() < capacity) {
                customers.add(customer);
            }
        }
        isFull = customers.size() >= capacity;
    }

    public int getCocktailPrice() {
        return COCKTAIL_PRICE;
    }

    public boolean isFull() {
        return isFull;
    }

    public int getCurrentOccupancy() {
        return customers.size();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setIsFull() {
        this.isFull = true;
    }
}