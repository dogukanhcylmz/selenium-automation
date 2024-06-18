## Application using Selenium Webdriver and TestNG
- Three different web sites are connected by using WebDriver and obtained the price of the same product which is the iPhone and wrote a simple report comparing their prices. The cheapest, the average price and the most expensive one. Drivers folders to keep the Chrome and Firefox driver exe’s there. The program starts by using ChromeDriver to navigate to websites to fetch the prices of the desired product.

- The program includes four test methods to verify various login scenarios: a valid login, an invalid login with both incorrect username and password, an invalid login with a correct username but incorrect password, and an invalid login with an incorrect username but correct password.

- Selenium TestNG framework-based test suite designed to verify the login functionality of a web application hosted at "https://practice.expandtesting.com/login". The test suite includes a FactoryTest class that handles the login operations and a nested TestFactory class to provide different sets of credentials for testing. The credentials are passed to the test class via a factory pattern, which allows the same test to be run with multiple sets of data.

