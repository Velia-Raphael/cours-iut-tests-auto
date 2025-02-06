package com.sqli.isc.iut.courses.cucumber;

public class Bill {
    private int amount;
    private boolean isPaid;

    public Bill() {
        this.amount = 0;
        this.isPaid = false;
    }

    public void addToBill(int amount) {
        this.amount += amount;
    }

    public void pay() {
        this.isPaid = true;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }
}