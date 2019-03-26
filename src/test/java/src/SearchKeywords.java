package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchKeywords {
    WebDriver driver = null;

    @BeforeMethod
    private void setup(){

    }
    @AfterMethod
    private void teardown(){
        driver.quit();
    }
    @Test
    private void searchTest(){
        driver.get("https://testerhome.com/");

        By logBy = By.cssSelector("");
        WebElement keyword = driver.findElement(By.id(""));

    }
}
