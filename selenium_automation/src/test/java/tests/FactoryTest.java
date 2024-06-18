package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class FactoryTest {

    private final String USER_NAME;
    private final String PASSWORD;
    private final String URL = "https://practice.expandtesting.com/login";
    WebDriver driver;
    public FactoryTest(String username, String password){
        this.USER_NAME = username;
        this.PASSWORD = password;
    }
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
    }
    @Test
    public void loginTest(){
        driver.get(URL);
        WebElement username =  driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.click();
        username.sendKeys(USER_NAME);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.click();
        password.sendKeys(PASSWORD);

        WebElement button = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        button.click();

        //Assert.assertTrue(driver.getCurrentUrl().equals("https://practice.expandtesting.com/secure"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"flash\"]")).isDisplayed());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    public static class TestFactory{
        @Factory
        public Object[] createInstances(){
            return new Object[]{
                    new FactoryTest("practice","SuperSecretPassword!"),
                    new FactoryTest("invalidUser","invalidPassword")
            };
        }
    }
}
