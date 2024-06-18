package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test1 {
    public static void main(String[] args) {
        System.setProperty("chromeDriver","/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/text-box");
        //driver.manage().window().maximize();

        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.click();
        fullName.sendKeys("Doğukan");

        WebElement email = driver.findElement(new By.ByCssSelector(".mr-sm-2[id='userEmail']"));
        email.click();
        email.sendKeys("dogukan123@gmail.com");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebElement currentAddress = driver.findElement(new By.ByCssSelector("textarea[placeholder='Current Address']"));
        currentAddress.click();
        currentAddress.sendKeys("Ankara");

        actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.click();
        permanentAddress.sendKeys("Turkey");

        WebElement submitButton = driver.findElement(new By.ByCssSelector("button.btn"));
        submitButton.click();
        submitButton.click();

        WebElement nameText = driver.findElement(By.xpath("//div/p[@id='name']"));
        String name = nameText.getText();
        System.out.println(name);
    }
}
