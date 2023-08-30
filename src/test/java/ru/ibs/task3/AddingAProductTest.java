package ru.ibs.task3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.ConfProperties;


public class AddingAProductTest extends BaseClass {

    By btnPesochnisa = By.id("navbarDropdown");
    By fldName = By.id("name");
    By btnSave = By.id("save");
    By fldType = By.id("type");
    By cbExotic = By.id("exotic");
    By title = By.id(ConfProperties.getProperty("title"));
//    переменные имеющие на конце 1 искались по xpath
//    переменные имеющие на конце 2 искались по css
    By btnDownList1 = By.xpath("//div[@class='dropdown-menu show']");
    By btnDownList2 = By.cssSelector("div.dropdown-menu.show");
    By btnFood1 = By.xpath("//a[@href='/food']");
    By btnFood2 = By.cssSelector(".dropdown-item[href='/food']");
    By btnAdd1 = By.xpath("//button[@data-toggle='modal']");
    By btnAdd2 = By.cssSelector("button.btn.btn-primary");
    By strPotato = By.xpath("//tr[5]/child::*[2]");
    By strVegetable = By.xpath("//tr[5]/child::*[3]");
    By strFalse = By.xpath("//tr[5]/child::*[4]");
    By typeFruit = By.cssSelector("[value='FRUIT']");
    By strPineapple = By.cssSelector("tbody > tr:last-of-type > td:first-of-type");
    By strFruit = By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(2)");
    By strTrue = By.cssSelector("tbody > tr:last-of-type > td:nth-of-type(3)");

    /**Этот метод добавляет картофель в список*/
    @Test
    public void testAddVegetable() {
//       переход на сайт
        driver.navigate().to(ConfProperties.getProperty("url"));
//       клик по кнопке "Песочница"
        driver.findElement(btnPesochnisa).click();
//       проверка видимости выпадающего списка
        Assertions.assertTrue(driver.findElement(btnDownList1).isDisplayed(),
                ConfProperties.getProperty("msgDownList"));
//       клик по кнопке "Товары"
        driver.findElement(btnFood1).click();
//       проверка перехода на страницу "Товары"
        Assertions.assertEquals(ConfProperties.getProperty("urlFood"),
                driver.getCurrentUrl(), ConfProperties.getProperty("msgNotFood"));
//       клик на кнопку "Добавить"
        driver.findElement(btnAdd1).click();
//       явное ожидание заголовка "Добавление товара"
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
//       проверка видимости заголовка "Добваление товара"
        Assertions.assertTrue(driver.findElement(title).isDisplayed(), ConfProperties.getProperty("msgNoTitle"));
//       заполнение поля "Наименование"
        driver.findElement(fldName).sendKeys("Картофель");
//       клик на кнопку "Сохранить"
        driver.findElement(btnSave).click();
//       явное ожидание строки "Картофель" на странице
        wait.until(ExpectedConditions.visibilityOfElementLocated(strPotato));
//       проверка видимости строки с названием "Картофель" на странице
        Assertions.assertTrue(driver.findElement(strPotato).isDisplayed(), "картофель не найден");
//       проверка того, что найдено наименование "Картофель"
        Assertions.assertEquals( "Картофель", driver.findElement(strPotato).getText(), "не картофель");
//       проверка того, что картофель имеет тип "овощ"
        Assertions.assertEquals("Овощ", driver.findElement(strVegetable).getText(), "не овощ");
//       проверка того, что картофель имеет значение false в поле "Экзотический"
        Assertions.assertEquals("false", driver.findElement(strFalse).getText(), "не false");
    }

    /**Этот метод добавляет ананас в список*/
    @Test
    public void testAddFruit() {
//        переход на сайт
        driver.navigate().to(ConfProperties.getProperty("url"));
//        клик по кнопке "Песочница"
        driver.findElement(btnPesochnisa).click();
//        проверка видимости выпадающего списка
        Assertions.assertTrue(driver.findElement(btnDownList2).isDisplayed(),
                ConfProperties.getProperty("msgDownList"));
//        клик по кнопке "Товары"
        driver.findElement(btnFood2).click();
//        проверка перехода на страницу "Товары"
        Assertions.assertEquals(ConfProperties.getProperty("urlFood"), driver.getCurrentUrl(),
                ConfProperties.getProperty("msgNotFood"));
//        клик на кнопку "Добавить"
        driver.findElement(btnAdd2).click();
//        явное ожидание заголовка "Добавление товара"
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
//        проверка видимости заголовка "Добваление товара"
        Assertions.assertTrue(driver.findElement(title).isDisplayed(), ConfProperties.getProperty("msgNoTitle"));
//        заполнение поля "Наименование"
        driver.findElement(fldName).sendKeys("Ананас");
//        клик на кнопку "Тип"
        driver.findElement(fldType).click();
//        выбор типа "Фрукт"
        driver.findElement(typeFruit).click();
//        отметка чек-бокса "Экзотический"
        driver.findElement(cbExotic).click();
//        клик на кнопку "Сохранить"
        driver.findElement(btnSave).click();
//        явное ожидание строки "Ананас" на странице
        wait.until(ExpectedConditions.visibilityOfElementLocated(strPineapple));
//        проверка видимости строки с названием "Ананас" на странице
        Assertions.assertTrue(driver.findElement(strPineapple).isDisplayed(), "ананас не найден");
//        проверка того, что найдено наименование "Ананас"
        Assertions.assertEquals("Ананас", driver.findElement(strPineapple).getText(), "не ананас");
//        проверка того, что ананас имеет тип "фрукт"
        Assertions.assertEquals("Фрукт", driver.findElement(strFruit).getText(), "не фрукт");
//        проверка того, что ананас имеет значение true в поле "Экзотический"
        Assertions.assertEquals("true", driver.findElement(strTrue).getText(), "не true");
    }
}