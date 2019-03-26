package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo1 {


    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {


        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();

    }

    @Test
    public void test_case3() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.get("http://www.baidu.com");

        WebElement element = driver.findElement(By.id("kw"));

        element.sendKeys("hello Selenium!");

        //element.submit();


    }
}
