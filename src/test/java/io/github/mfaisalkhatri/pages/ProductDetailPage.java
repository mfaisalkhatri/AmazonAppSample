package io.github.mfaisalkhatri.pages;

import static drivers.DriverManager.getDriver;

import org.openqa.selenium.By;

/**
 * @author Faisal Khatri
 * @since 10/10/2022
 **/
public class ProductDetailPage {

    public String productPrice () {
        return getDriver().findElement (By.cssSelector ("#corePrice_desktop .a-lineitem .apexPriceToPay")).getText ();
    }

    public String productTitle () {
        return getDriver ().findElement (By.id ("productTitle")).getText ();
    }
}
