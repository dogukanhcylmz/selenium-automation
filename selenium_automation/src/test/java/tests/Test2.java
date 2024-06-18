package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test2 {

    public static void main(String[] args) {
        System.setProperty("chromeDriver","/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/checkbox");

        String homeCheckboxCSSValue = "label[for='tree-node-home'] span.rct-checkbox svg.rct-icon";
        WebElement homeCheckbox = driver.findElement(new By.ByCssSelector(homeCheckboxCSSValue));
        homeCheckbox.click();

        homeCheckbox = driver.findElement(new By.ByCssSelector(homeCheckboxCSSValue));
        String homeCheckboxClassName = homeCheckbox.getAttribute("class");

        if(homeCheckboxClassName.equals("rct-icon rct-icon-check")){
            System.out.println("Checkbox is checked!");
        }else{
            System.out.println("Checkbox is unchecked!");
        }
    }
}
