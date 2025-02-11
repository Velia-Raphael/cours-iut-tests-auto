package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bar {
    private String name;
    private final int capacity;
    private final List<Customer> customers;
    private boolean isFull;
    private static final int COCKTAIL_PRICE = 10;

    public Bar(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.customers = new ArrayList<>();
        this.isFull = false;
    }

    public void addCustomers(Customer... newCustomers) {
        if (customers.size() + newCustomers.length > capacity) {
            isFull = true;
            return;
        }
        Collections.addAll(customers, newCustomers);
        isFull = customers.size() >= capacity;
    }

    public static int getCocktailPrice() {
        return COCKTAIL_PRICE;
    }

    public int getTotalBill() {
        return customers.stream()
                .mapToInt(customer -> customer.getBill().getAmount())
                .sum();
    }

    public boolean isFull() {
        return isFull;
    }
}