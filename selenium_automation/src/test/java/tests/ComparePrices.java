package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ComparePrices {
    public static void main(String[] args) {

        System.setProperty("chromeDriver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.vatanbilgisayar.com/");
        driver.manage().window().maximize();

        WebElement search = driver.findElement(By.xpath("//*[@id='navbar-search-input']"));
        search.click();
        search.sendKeys("iphone 14 256gb");

        WebElement button = driver.findElement(By.xpath("/html/body/header/nav/div[3]/div[1]/div/div/div[2]/div[2]/div/div/button"));
        button.click();

        String vatanPrice = driver.findElement(By.xpath("//*[@id=\"productsLoad\"]/div/div[2]/div[2]/div[1]/span[1]")).getText();
        double vatanPrice1 = Double.parseDouble(vatanPrice.replaceAll("[^0-9.]", ""));


        driver.get("https://www.amazon.com.tr/");
        driver.manage().window().maximize();

        search = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        search.click();
        search.sendKeys("iphone 14 256gb gece yarısı");

        button = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]"));
        button.click();


        String amazonPrice = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div[2]/div[5]/div/div[1]/a/span/span[2]/span[1]")).getText();
        double amazonPrice1 = Double.parseDouble(amazonPrice.replaceAll("[^0-9.]", ""));


        driver.get("https://www.pazarama.com/");
        driver.manage().window().maximize();

        search = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div[1]/div[2]/div/div/div/div[1]/div/span/div/div/input"));
        search.click();
        search.sendKeys("iphone 14 256gb gece yarısı");

        button = driver.findElement(By.xpath("//*[@id=\"app\"]/header/div[1]/div[2]/div/div/div/div[1]/div/button"));
        button.click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pazaramaPrice = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div[2]/div[3]/div[2]/div/a/div[2]/div[4]/div[2]")).getText();
        double pazaramaPrice1 = Double.parseDouble(pazaramaPrice.replaceAll("[^0-9.]", ""));

        double cheapestPrice = Math.min(Math.min(vatanPrice1, amazonPrice1), pazaramaPrice1);
        double mostExpensivePrice = Math.max(Math.max(vatanPrice1, amazonPrice1), pazaramaPrice1);
        double averagePrice = (vatanPrice1 + amazonPrice1 + pazaramaPrice1) / 3;

        System.out.println("Cheapest Price: $" + cheapestPrice);
        System.out.println("Average Price: $" + averagePrice);
        System.out.println("Most Expensive Price: $" + mostExpensivePrice);
        System.out.println("-------------------------------------------------");

        if (cheapestPrice == vatanPrice1)
            System.out.println("Vatan Bilgisayar is the cheapest.");
        else if (cheapestPrice == amazonPrice1)
            System.out.println("Amazon is the cheapest.");
        else
            System.out.println("Pazarama is the cheapest.");

        if (mostExpensivePrice == vatanPrice1)
            System.out.println("Vatan Bilgisayar is the most expensive.");
        else if (mostExpensivePrice == amazonPrice1)
            System.out.println("Amazon is the most expensive.");
        else
            System.out.println("Pazarama is the most expensive.");

        driver.quit();
    }
}