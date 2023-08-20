package ru.ibs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testAddVegetable() throws InterruptedException {
        driver.navigate().to("http://localhost:8080/");
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.cssSelector(".dropdown-item[href='/food']")).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        driver.findElement(By.id("name")).sendKeys("Картофель");
        driver.findElement(By.id("save")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")));
        Assert.assertTrue("картофель не найден",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")).isDisplayed());
        Assert.assertEquals("Картофель",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")).getText());
        Assert.assertEquals("Овощ",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(2)")).getText());
        Assert.assertEquals("false",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(3)")).getText());
    }

    @Test
    public void testAddFruit() {
        driver.navigate().to("http://localhost:8080/");
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.cssSelector(".dropdown-item[href='/food']")).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        driver.findElement(By.id("name")).sendKeys("Ананас");
        driver.findElement(By.id("type")).click();
        driver.findElement(By.cssSelector("[value='FRUIT']")).click();
        driver.findElement(By.id("exotic")).click();
        driver.findElement(By.id("save")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")));
        Assert.assertTrue("ананас не найден",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")).isDisplayed());
        Assert.assertEquals("Ананас",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")).getText());
        Assert.assertEquals("Фрукт",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(2)")).getText());
        Assert.assertEquals("true",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(3)")).getText());
    }
}