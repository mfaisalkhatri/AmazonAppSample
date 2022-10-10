package io.github.mfaisalkhatri;

import static drivers.DriverManager.createChromeDriver;
import static drivers.DriverManager.getDriver;
import static drivers.DriverManager.quitDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.mfaisalkhatri.pages.MainPage;

public class StepDefinitions {

    private        String   mobilePhoneTitle;
    private        String   mobilePhonePrice;
    private static MainPage mainPage;

    @BeforeAll
    public static void setUp () {
        mainPage = new MainPage ();
        createChromeDriver ();
        getDriver ().get ("https://www.amazon.in");
    }

    @Given ("I search for {string} Mobile")
    public void i_search_for_mobile (String mobile) {
        mainPage.searchForProduct (mobile);

    }

    @When ("I click on first search result")
    public void i_click_on_first_search_result () {
        mainPage.clickOnFirstSearchResult ();
    }

    @Then ("I should be able to see {string} mobile phone details and its price")
    public void i_should_be_able_to_see_mobile_phone_details_and_its_price (String string) {
        mobilePhoneTitle = mainPage.getMobilePhoneTitle ();
        mobilePhonePrice = mainPage.getMobilePhonePrice ();
        assertEquals (mobilePhoneTitle, "Samsung Galaxy S20 FE 5G (Cloud Mint, 8GB RAM, 128GB Storage)");
        assertEquals (mobilePhonePrice, "29,990");
    }

    @Given ("I add {string} mobile to cart")
    public void i_add_mobile_to_cart (String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException ();
    }

    @When ("I click on {string} button")
    public void i_click_on_button (String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException ();
    }

    @Then ("I should be able to see {string} mobile phone details with its price")
    public void i_should_be_able_to_see_mobile_phone_details_with_its_price (String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException ();
    }

    @AfterAll
    public static void tearDown () {
        quitDriver ();
    }
}
