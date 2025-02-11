package com.sqli.isc.iut.courses.cucumber;

public class Bill {
    private int amount;
    private boolean isPaid;

    public Bill() {
        this.amount = 0;
        this.isPaid = false;
    }

    public void addToBill(int amount) {
        if (amount > 0 && !isPaid) {
            this.amount += amount;
        }
    }

    public boolean pay() {
        if (!isPaid) {
            isPaid = true;
            return true;
        }
        return false;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }
}