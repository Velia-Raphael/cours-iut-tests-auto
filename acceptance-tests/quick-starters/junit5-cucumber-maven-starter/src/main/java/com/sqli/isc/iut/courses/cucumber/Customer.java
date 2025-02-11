package com.sqli.isc.iut.courses.cucumber;

public class Customer {
    private String name;
    private boolean hasLiverProblem;
    private int drinksConsumed;
    private Bill bill;
    private boolean isSad;

    public Customer(String name, boolean hasLiverProblem) {
        this.name = name;
        this.hasLiverProblem = hasLiverProblem;
        this.drinksConsumed = 0;
        this.bill = new Bill();
    }

    public void orderDrink() {
        drinksConsumed++;
        bill.addToBill(Bar.getCocktailPrice());
    }

    public boolean isHappy() {
        if (hasLiverProblem) {
            return drinksConsumed == 1;
        }
        return true;
    }

    public void verifyBill() {
        System.out.println(name + " vérifie sa note: " + bill.getAmount() + "€");
    }

    public void payBill() {
        System.out.println(name + " paie sa note de " + bill.getAmount() + "€");
        bill.pay();
    }

    public void feelSad() {
        if (drinksConsumed > 1) {
            isSad = true;
            System.out.println(name + " est triste, il sait qu'il va passer une mauvaise nuit...");
        }
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