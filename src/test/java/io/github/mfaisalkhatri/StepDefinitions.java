package io.github.mfaisalkhatri;

import static drivers.DriverManager.createChromeDriver;
import static drivers.DriverManager.getDriver;
import static drivers.DriverManager.quitDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.mfaisalkhatri.pages.MainPage;
import io.github.mfaisalkhatri.pages.ProductDetailPage;
import io.github.mfaisalkhatri.pages.ShoppingCartPage;

public class StepDefinitions {
    private String expectedTitle;
    private String expectedPrice;
    private String productTitle;
    private String productPrice;

    private static MainPage          mainPage;
    private static ProductDetailPage productDetailPage;

    @BeforeAll
    public static void setUp () {
        mainPage = new MainPage ();
        productDetailPage = new ProductDetailPage ();
        createChromeDriver ();
        getDriver ().get ("https://www.amazon.in");
    }

    @Given ("I search for {string} Mobile")
    public void i_search_for_mobile (String mobile) {
        mainPage.searchForProduct (mobile);
    }

    @When ("I click on first search result")
    public void i_click_on_first_search_result () {
        assertEquals (
            "Samsung Galaxy S20 FE 5G (Cloud Navy, 8GB RAM, 128GB Storage) with No Cost EMI & Additional Exchange Offers",
            mainPage.getMobilePhoneTitle ());
        assertEquals ("29,990", mainPage.getMobilePhonePrice ());
        expectedTitle = mainPage.getMobilePhoneTitle ();
        expectedPrice = mainPage.getMobilePhonePrice ();
        mainPage.clickOnFirstSearchResult ();
    }

    @Then ("I should be able to see Samsung Galaxy S20 mobile phone details and its price")
    public void i_should_be_able_to_see_mobile_phone_details_and_its_price () {
        assertEquals (expectedTitle, productDetailPage.getProductTitle ());
        assertTrue (productDetailPage.getProductPrice ().contains (expectedPrice));
    }

    @Given ("I add Samsung Galaxy S20 mobile to cart")
    public void i_add_mobile_to_cart () {
        productTitle = productDetailPage.getProductTitle ();
        productPrice = productDetailPage.getProductPrice ();
        productDetailPage.addProductToCart ();
    }

    @When ("I click on Cart button")
    public void i_click_on_button () {
        productDetailPage.openShoppingCart ();
    }

    @Then ("I should be able to see Samsung Galaxy S20 mobile phone details with its price")
    public void i_should_be_able_to_see_mobile_phone_details_with_its_price () {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage ();
        assertEquals (productTitle, shoppingCartPage.getProductTitle ());
        assertTrue (shoppingCartPage.getProductPrice ()
            .contains (productPrice));
    }

    @AfterAll
    public static void tearDown () {
        quitDriver ();
    }
}
