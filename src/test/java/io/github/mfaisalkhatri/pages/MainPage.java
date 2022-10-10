package io.github.mfaisalkhatri.pages;

import static drivers.DriverManager.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Faisal Khatri
 * @since 10/10/2022
 **/
public class MainPage {

    public WebElement searchBox () {
        return getDriver ().findElement (By.id ("twotabsearchtextbox"));
    }

    public WebElement searchButton() {
        return getDriver ().findElement (By.id ("nav-search-submit-button"));
    }

    public WebElement mobilePhoneTitle () {
        return getDriver ().findElement (By.cssSelector ("div[data-component-type=\"s-search-result\"] h2"));
    }
    public String getMobilePhoneTitle () {
        return mobilePhoneTitle ().getText ();
    }

    public String getMobilePhonePrice () {
        return getDriver ().findElement (By.cssSelector ("div[data-component-type=\"s-search-result\"] span.a-price-whole")).getText ();
    }

    public void searchForProduct(String productName) {
        searchBox ().sendKeys (productName);
        searchButton ().click ();
    }

    public void clickOnFirstSearchResult() {
        mobilePhoneTitle ().click ();
    }
}
