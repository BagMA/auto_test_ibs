package ru.ibs.task3;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    /**Метод выполняется перед всеми тестами*/
    @BeforeAll
    public static void setUp()
    {
//        путь до драйвера
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
//        иницилизация драйвера
        driver = new ChromeDriver();
//        иницилизация явного ожидания
        wait = new WebDriverWait(driver, 3);
//        задание неявного ожидания
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        разворачивание окна браузера на весть экран
        driver.manage().window().maximize();
    }

    /**Метод закрывает веб-драйвер и браузер после всех тестов*/
    @AfterAll
    public static void tearDown()
    {
        driver.quit();
    }
}
