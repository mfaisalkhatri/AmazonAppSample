package io.github.mfaisalkhatri.pages;

import static drivers.DriverManager.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public WebElement mobilePhoneLink() {
        return getDriver ().findElement (By.cssSelector ("div[data-component-type=\"s-search-result\"] h2 > a"));
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
        WebDriverWait wait = new WebDriverWait (getDriver(),Duration.ofSeconds (10));
        final String originalWindow = getDriver ().getWindowHandle ();
        mobilePhoneLink ().click ();
        wait.until (ExpectedConditions.numberOfWindowsToBe (2));
        for (final String windowHandle : getDriver ().getWindowHandles ()) {
            if (!originalWindow.contentEquals (windowHandle)) {
                getDriver ().switchTo ()
                    .window (windowHandle);
                break;
            }
        }
    }

}
