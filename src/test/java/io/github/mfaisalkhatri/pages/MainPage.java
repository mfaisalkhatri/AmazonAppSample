package io.github.mfaisalkhatri.pages;

import static drivers.DriverManager.getDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import drivers.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Faisal Khatri
 * @since 10/10/2022
 **/
public class MainPage {

    public static MainPage mainPage() {
        return new MainPage ();
    }

//    public Map<String,By> pageTitle () {
//        Map<String, By> pageTitleWebelements = new HashMap<> ();
//        pageTitleWebelements.put ("web", By.id ("pageTitle"));
//        pageTitleWebelements.put ("android", AppiumBy.accessibilityId ("pageTitle"));
//        return pageTitleWebelements;
//    }
//    public void getPageTitle () {
//        if(DriverManager.getPlatform ().equals ("web")) {
//            getDriver ().findElement (pageTitle ().get ("web")).getText ();
//        } else {
//            getDriver ().findElement (pageTitle ().get ("mobile")).getText ();
//        }
//    }

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
