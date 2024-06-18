package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTestByUsingFactory {
    WebDriver driver;
    String browser;

    public LoginTestByUsingFactory(String browser) {
        this.browser = browser;
    }

    @Parameters("browser")
    @BeforeTest
    public void setBrowser(String browser) {

        System.out.println("Browser name is "+ browser);

        if(browser.equalsIgnoreCase("Firefox")){
            System.setProperty("geckoDriver","/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("chromeDriver", "/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser value!!");
        }
    }

    @Test
    public void testValidLogin() {
        driver.get("https://practice.expandtesting.com/login");

        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.click();
        username.sendKeys("practice");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.click();
        password.sendKeys("SuperSecretPassword!");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        WebElement logout = driver.findElement(By.xpath("/html/body/main/div[2]/div/a/i"));

        Assert.assertTrue(driver.getCurrentUrl().equals("https://practice.expandtesting.com/secure"));
        logout.click();
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://practice.expandtesting.com/login");

        WebElement username =  driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.click();
        username.sendKeys("invalidUsername");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.click();
        password.sendKeys("invalidPassword");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flash']/b")));
        String messageText = messageElement.getText();
        Assert.assertEquals(messageText, "Your username is invalid!");
    }

    @Test
    public void testInvalidUsername() {
        driver.get("https://practice.expandtesting.com/login");

        WebElement username =  driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.click();
        username.sendKeys("invalidUsername");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.click();
        password.sendKeys("SuperSecretPassword!");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flash']/b")));
        String messageText = messageElement.getText();
        Assert.assertEquals(messageText, "Your username is invalid!");
    }

    @Test
    public void testInvalidPassword() {
        driver.get("https://practice.expandtesting.com/login");

        WebElement username =  driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.click();
        username.sendKeys("practice");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.click();
        password.sendKeys("invalidPassword");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flash']/b")));
        String messageText = messageElement.getText();
        Assert.assertEquals(messageText, "Your password is invalid!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "browser")
    public static Object[][] browsers() {
        return new Object[][]{{"Firefox"}, {"Chrome"}};
    }

    @Factory(dataProvider = "browsers")
    public static Object[] createInstances(String browser) {
        return new Object[]{new LoginTestByUsingFactory(browser)};
    }
}
