package io.github.mfaisalkhatri.pages;

import static drivers.DriverManager.getDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Faisal Khatri
 * @since 10/11/2022
 **/
public class ShoppingCartPage {

    public String getProductTitle () {
        return getDriver ().findElement (By.cssSelector ("li:nth-child(1) > span > a"))
            .getText ();
    }

    public String getProductPrice () {
        WebDriverWait wait = new WebDriverWait (getDriver (), Duration.ofSeconds (10));
        return wait.until (ExpectedConditions.visibilityOfElementLocated (By.cssSelector ("span.sc-product-price")))
            .getText ();

    }

}
