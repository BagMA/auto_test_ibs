package ru.ibs;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class AppTest
{
    private static WebDriver driver;
    private static WebDriverWait wait;


    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:8080/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testAddVegetable() throws InterruptedException {
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.xpath("//div/a[@href='/food']")).click();
        driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
        driver.findElement(By.id("name")).sendKeys("Картофель");
        driver.findElement(By.id("save")).click();
        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//tr[5]/td[1]")));
        Assert.assertTrue("картофель не найден",
                driver.findElement(By.xpath("//tr[5]/td[1]")).isDisplayed());
        Assert.assertEquals("не картофель", "Картофель",
                driver.findElement(By.xpath("//tr[5]/td[1]")).getText());
        Assert.assertEquals("не овощ","Овощ",
                driver.findElement(By.xpath("//tr[5]/td[2]")).getText());
        Assert.assertEquals("не false","false",
                driver.findElement(By.xpath("//tr[5]/td[3]")).getText());
    }

    @Test
    public void testAddFruit() {
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
        Assert.assertEquals("не ананас","Ананас",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:first-of-type")).getText());
        Assert.assertEquals("не фрукт", "Фрукт",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(2)")).getText());
        Assert.assertEquals("не true", "true",
                driver.findElement(By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(3)")).getText());
    }
}