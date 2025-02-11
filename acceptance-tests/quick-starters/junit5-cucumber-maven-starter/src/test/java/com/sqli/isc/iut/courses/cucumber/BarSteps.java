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

    @Given("Mr Pignon and Mr Leblanc go to the bar Le Juste")
    public void leJusteIsACocktailBar() {
        mrPignon = new Customer("Mr Pignon", true);
        mrLeblanc = new Customer("Mr Leblanc", false);
        leJuste = new Bar("Le Juste", 10);
    }

    @And("the bar has only {int} seats")
    public void theBarHasOnlySeats(int seats) {
        leJuste.setCapacity(seats);
    }

    @Given("there are {int} people in the bar")
    public void thereArePeopleInTheBar(int people) {
        for (int i = 0; i < people; i++) {
            leJuste.addCustomers(new Customer("Customer " + i, false));
        }
    }

    @When("Mr Pignon and Mr Leblanc try to enter")
    public void mrPignonAndMrLeblancTryToEnter() {
        if (leJuste.canEnter(2)) {
            leJuste.addCustomers(mrPignon, mrLeblanc);
            entryAllowed = true;
        } else {
            entryAllowed = false;
            leJuste.setIsFull();
        }
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
        assertTrue(leJuste.canEnter(0));
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
        int mrPignonBill = mrPignon.getBill().getAmount();
        mrLeblanc.getBill().addToBill(mrPignonBill);
        mrPignon.getBill().pay();
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

    @Then("they check their individual bills")
    public void theyCheckTheirIndividualBills() {
        assertEquals(10, mrPignon.getBill().getAmount());
        assertEquals(10, mrLeblanc.getBill().getAmount());
    }

    @And("Mr Pignon pays his bill")
    public void mrPignonPaysHisBill() {
        mrPignon.getBill().pay();
        assertTrue(mrPignon.getBill().isPaid());
    }

    @And("Mr Leblanc orders 2 more cocktails of the month")
    public void mrLeblancOrders2MoreCocktailsOfTheMonth() {
        mrPignon.orderDrink();
        mrLeblanc.orderDrink();
        mrLeblanc.getBill().addToBill(leJuste.getCocktailPrice() * 2);
    }


    @When("Mr Leblanc checks the bill and pays")
    public void mrLeblancChecksTheBillAndPays() {
        mrLeblanc.getBill().pay();
        assertTrue(mrLeblanc.getBill().isPaid());
    }

    @Then("Mr Pignon is sad because he knows having more than one cocktail will lead to a bad night")
    public void mrPignonIsSadBecauseOfTooManyDrinks() {
        assertTrue(mrPignon.getDrinksConsumed() > 1);
        assertFalse(mrPignon.isHappy());
    }

    @Then("the person behind them cannot enter")
    public void thePersonBehindThemCannotEnter() {
        assertFalse(leJuste.canEnter(1));
    }
}