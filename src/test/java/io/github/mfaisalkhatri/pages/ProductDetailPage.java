package io.github.mfaisalkhatri.pages;

import static drivers.DriverManager.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Faisal Khatri
 * @since 10/10/2022
 **/
public class ProductDetailPage {

    public static ProductDetailPage productDetailPage () {
        return new ProductDetailPage ();
    }

    public String getProductPrice () {
        return getDriver ().findElement (By.cssSelector ("h5 > div:nth-child(2) > div > span.a-color-price"))
            .getText ().trim ();
    }

    public String getProductTitle () {
        return getDriver ().findElement (By.id ("productTitle"))
            .getText ();
    }

    public WebElement addToCartButton () {
        return getDriver ().findElement (By.id ("add-to-cart-button"));
    }

    public WebElement cartButton () {
        return getDriver ().findElement (By.cssSelector ("#attach-sidesheet-view-cart-button > span > input"));
    }

    public void addProductToCart () {
        addToCartButton ().click ();
    }

    public void openShoppingCart () {
        cartButton ().click ();
    }



}
