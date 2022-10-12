package io.github.mfaisalkhatri;

import static drivers.DriverManager.createAndroidDriver;
import static drivers.DriverManager.createChromeDriver;
import static drivers.DriverManager.getDriver;
import static drivers.DriverManager.quitDriver;
import static io.github.mfaisalkhatri.pages.MainPage.mainPage;
import static io.github.mfaisalkhatri.pages.ProductDetailPage.productDetailPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.mfaisalkhatri.pages.ShoppingCartPage;

public class StepDefinitions {
    private String expectedTitle;
    private String expectedPrice;
    private String productTitle;
    private String productPrice;

    @Before ("@web")
    public static void setUp () {
        createChromeDriver ();
        getDriver ().get ("https://www.amazon.in");
    }

    @Before ("@android")
    public static void mobileSetup () {
        createAndroidDriver ();
    }

    @Given ("I search for {string} Mobile")
    public void i_search_for_mobile (String mobile) {
        mainPage ().searchForProduct (mobile);
    }

    @When ("I click on first search result")
    public void i_click_on_first_search_result () {
        assertEquals (
            "Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage) with No Cost EMI & Additional Exchange Offers",
            mainPage ().getMobilePhoneTitle ());
        assertEquals ("29,990", mainPage ().getMobilePhonePrice ());
        expectedTitle = mainPage ().getMobilePhoneTitle ();
        expectedPrice = mainPage ().getMobilePhonePrice ();
        mainPage ().clickOnFirstSearchResult ();
        assertEquals (expectedTitle, productDetailPage ().getProductTitle ());
        assertTrue (productDetailPage ().getProductPrice ()
            .contains (expectedPrice));

    }
    @And ("I add Samsung Galaxy S20 mobile to cart")
    public void i_add_mobile_to_cart () {
        productTitle = productDetailPage ().getProductTitle ();
        productPrice = productDetailPage ().getProductPrice ();
        productDetailPage ().addProductToCart ();
    }
    @And ("I click on Cart button")
    public void i_click_on_button () {
        productDetailPage ().openShoppingCart ();
    }

    @Then("I should be able to see Samsung Galaxy S20 mobile phone details and its price")
    public void i_should_be_able_to_see_samsung_galaxy_s20_mobile_phone_details_and_its_price() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage ();
        assertEquals (productTitle, shoppingCartPage.getProductTitle ());
        assertTrue (shoppingCartPage.getProductPrice ()
            .contains (productPrice));
    }

    @After()
    public static void tearDown () {
        quitDriver ();
    }

}

