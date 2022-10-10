package io.github.mfaisalkhatri;

import static drivers.DriverManager.createChromeDriver;
import static drivers.DriverManager.getDriver;
import static drivers.DriverManager.quitDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class StepDefinitions {

    @Before public void setUp() {
        createChromeDriver();
        getDriver ().get ("https://www.amazon.in");
    }
    @Given("I search for {string} Mobile")
    public void i_search_for_mobile(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("Search Result page appears")
    public void search_result_page_appears() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click on first search result")
    public void i_click_on_first_search_result() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should be able to see {string} mobile phone details and its price")
    public void i_should_be_able_to_see_mobile_phone_details_and_its_price(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("I add {string} mobile to cart")
    public void i_add_mobile_to_cart(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click on {string} button")
    public void i_click_on_button(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should be able to see {string} mobile phone details with its price")
    public void i_should_be_able_to_see_mobile_phone_details_with_its_price(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @After
    public void tearDown () {
        quitDriver ();
    }
}
