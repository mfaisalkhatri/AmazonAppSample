
## :question: What is this Repository about?
This repository contains the example code for running tests web and mobile automation tests using cucumber.

### Following are the details of Application Under Test 
 - Application under Test: Amazon Website and Amazon Android app.

### Following are the steps that are automated as an example

**Steps:**
1. Navigate to `https://www.amazon.in` / Open the amazon app in mobile device.
1. Search for "Samsung Galaxy S20" mobile phone.
1. On the Search result page, save the title and price of the product.
1. On the Search result page, click on the first result.
1. In the product detail page, verify the title and price.
1. In the product detail page, click on the "Add to Cart" button.
1. In the side popup window, click on the "Cart" button.
1. Check for title and price in the shopping cart.

### Tech Stack is as follows: 
Programming Language: Java
Test Framework:  Cucumber JVM, Selenium WebDriver and Appium.

### Running the tests
- Right-click on the feature file in the `src\test\resources\features` folder and select `Run Feature:`

> **Mobile tests are currently not in working state due to challenges in getting the locators.**