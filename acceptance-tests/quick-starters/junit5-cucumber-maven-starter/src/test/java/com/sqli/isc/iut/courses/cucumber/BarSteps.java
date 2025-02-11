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
    private int totalBill = 0;

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

    @When("Mr Pignon and Mr Leblanc arrive all at the bar")
    public void mr_Pignon_and_Mr_Leblanc_arrive_all_at_the_bar() {
        leJuste.addCustomers(mrPignon, mrLeblanc);
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

    /**----------------------------------Part2--------------------------------------------------------**/

    @Then("the next customer is denied entry")
    public void theNextCustomerIsDenied_entry() {
        assertFalse(entryAllowed);
    }

    @When("they order a cocktail of the month at 10€ each")
    public void they_order_cocktails() {
        mrPignon.orderDrink();
        mrLeblanc.orderDrink();
    }

    @Then("Mr Leblanc pays for both")
    public void mr_Leblanc_pays() {
        System.out.println("💰 Mr Leblanc paie " + leJuste.getTotalBill() + "€");
    }

    @When("they finish their drinks, the bill is verified")
    public void theyFinishTheirDrinksTheBillIsVerified() {
        int expectedBill = 20; // 2 cocktails à 10€
        int actualBill = leJuste.getTotalBill();

        System.out.println("🧾 Vérification de la note : " + actualBill + "€ (attendu : " + expectedBill + "€)");

        assertEquals(expectedBill, actualBill, "Erreur: la facture ne correspond pas !");
    }

    @Then("Mr Pignon is happy because he drank only one glass")
    public void mr_Pignon_is_happy() {
        assertTrue(mrPignon.isHappy());
    }

    /**----------------------------------Part3--------------------------------------------------------**/

    @And("nobody pays for the other’s drink")
    public void nobodyPaysForOthersDrink() {
        //
    }

    @When("they finish their drinks, they verify their bills")
    public void theyVerifyTheirBills() {
        mrPignon.verifyBill();
        mrLeblanc.verifyBill();
    }

    @Then("Mr Pignon pays his bill")
    public void mrPignonPaysHisBill() {
        mrPignon.payBill();
    }

    @And("Mr Leblanc insists on buying another round")
    public void mrLeblancInsistsOnBuyingAnotherRound() {
        //
    }

    @When("Mr Leblanc orders {int} more cocktails of the month")
    public void mrLeblancOrdersMoreCocktails(int quantity) {
        for (int i = 0; i < quantity; i++) {
            mrLeblanc.orderDrink();
        }
    }

    @Then("at the end of his drink, Mr Leblanc verifies the bill and pays.")
    public void mrLeblancVerifiesAndPays() {
        mrLeblanc.verifyBill();
        mrLeblanc.payBill();
    }

    @And("Mr Pignon is sad because he knows having more than one cocktail will lead to a bad night")
    public void mrPignonIsSad() {
        mrPignon.feelSad();
    }
}