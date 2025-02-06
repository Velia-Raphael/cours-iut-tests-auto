package com.sqli.isc.iut.courses.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.jupiter.api.Assertions.*;

public class BarSteps {
    private Bar leJuste;
    private Customer mrPignon;
    private Customer mrLeblanc;
    private boolean entryAllowed;

    @Given("Le Juste is a cocktail bar")
    public void leJusteIsACocktailBar() {
        leJuste = new Bar("Le Juste", 10); // Initialisation avec 10 sièges par défaut
    }

    @And("the bar has only {int} seats")
    public void theBarHasOnlySeats(int seats) {
        leJuste = new Bar("Le Juste", seats);
        mrPignon = new Customer("Mr Pignon", true); // avec problème de foie
        mrLeblanc = new Customer("Mr Leblanc", false); // sans problème de foie
    }

    @Given("there are {int} people in the bar")
    public void thereArePeopleInTheBar(int people) {
        for (int i = 0; i < people; i++) {
            leJuste.addCustomers(new Customer("Customer " + i, false));
        }
    }

    @When("Mr Pignon and Mr Leblanc try to enter")
    public void mrPignonAndMrLeblancTryToEnter() {
        entryAllowed = leJuste.canEnter(2);
    }

    @Then("they are denied entry")
    public void theyAreDeniedEntry() {
        assertFalse(entryAllowed);
    }

    @And("the bar displays {string}")
    public void theBarDisplays(String message) {
        assertTrue(leJuste.isFull());
        assertEquals("Full", message);
    }

    @When("Mr Pignon and Mr Leblanc enter the bar")
    public void mrPignonAndMrLeblancEnterTheBar() {
        leJuste.addCustomers(mrPignon, mrLeblanc);
    }

    @Then("they are allowed to enter")
    public void theyAreAllowedToEnter() {
        assertTrue(leJuste.canEnter(0)); // Vérifie s'ils sont bien entrés
    }

    @When("they each order a cocktail of the month")
    public void theyEachOrderACocktailOfTheMonth() {
        mrPignon.orderDrink();
        mrPignon.getBill().addToBill(leJuste.getCocktailPrice());

        mrLeblanc.orderDrink();
        mrLeblanc.getBill().addToBill(leJuste.getCocktailPrice());
    }

    @And("Mr Leblanc pays for both drinks")
    public void mrLeblancPaysForBothDrinks() {
        int totalAmount = mrPignon.getBill().getAmount() + mrLeblanc.getBill().getAmount();
        mrLeblanc.getBill().addToBill(totalAmount);
        mrPignon.getBill().pay(); // La note de Pignon est effacée
    }

    @Then("the bill is verified")
    public void theBillIsVerified() {
        assertEquals(20, mrLeblanc.getBill().getAmount()); // 2 cocktails à 10€
    }

    @And("Mr Leblanc pays")
    public void mrLeblancPays() {
        mrLeblanc.getBill().pay();
        assertTrue(mrLeblanc.getBill().isPaid());
    }

    @And("Mr Pignon is happy because they only had one drink")
    public void mrPignonIsHappyBecauseTheyOnlyHadOneDrink() {
        assertEquals(1, mrPignon.getDrinksConsumed());
        assertTrue(mrPignon.isHappy());
    }

    @And("each person pays for their own drink")
    public void eachPersonPaysForTheirOwnDrink() {
        assertEquals(10, mrPignon.getBill().getAmount());
        assertEquals(10, mrLeblanc.getBill().getAmount());
    }

    @When("Mr Leblanc insists on buying another round")
    public void mrLeblancInsistsOnBuyingAnotherRound() {
        mrPignon.orderDrink();
        mrLeblanc.orderDrink();
        mrLeblanc.getBill().addToBill(leJuste.getCocktailPrice() * 2);
    }

    @And("he orders {int} more cocktails of the month")
    public void heOrdersMoreCocktailsOfTheMonth(int quantity) {
        for (int i = 0; i < quantity; i++) {
            mrLeblanc.getBill().addToBill(leJuste.getCocktailPrice());
        }
    }

    @Then("Mr Pignon is sad because he knows having more than one cocktail will lead to a bad night")
    public void mrPignonIsSadBecauseOfTooManyDrinks() {
        assertTrue(mrPignon.getDrinksConsumed() > 1);
        assertFalse(mrPignon.isHappy());
    }
}