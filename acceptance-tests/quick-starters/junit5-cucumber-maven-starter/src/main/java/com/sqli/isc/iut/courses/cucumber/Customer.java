package com.sqli.isc.iut.courses.cucumber;

public class Customer {
    private String name;
    private boolean hasLiverProblem;
    private int drinksConsumed;
    private Bill bill;

    public Customer(String name, boolean hasLiverProblem) {
        this.name = name;
        this.hasLiverProblem = hasLiverProblem;
        this.drinksConsumed = 0;
        this.bill = new Bill();
    }

    public void orderDrink() {
        drinksConsumed++;
    }

    public boolean isHappy() {
        if (hasLiverProblem) {
            return drinksConsumed == 1;
        }
        return true;
    }

    public Bill getBill() {
        return bill;
    }

    public int getDrinksConsumed() {
        return drinksConsumed;
    }

    public boolean hasLiverProblem() {
        return hasLiverProblem;
    }
}