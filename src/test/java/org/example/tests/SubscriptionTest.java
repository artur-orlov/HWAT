package org.example.tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://a1.by/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testEmailSubscription() {
        String testEmail = "t2334est@2423423.ru";
        String expectedMessage = "Вы успешно подписались на нашу новостную рассылку.";

        mainPage.acceptCoockie();
        // Пролистать до поля ввода email
        mainPage.scrollToBottomWithDelay(5);

        // Ввести email и нажать на кнопку подписки
        mainPage.enterEmail(testEmail);

        mainPage.scrollToBottomWithDelay(5);

        mainPage.clickSubscribe();

        // Проверить сообщение подтверждения
        String actualMessage = mainPage.getConfirmationMessage();
        assertEquals(expectedMessage, actualMessage, "Сообщение подтверждения не совпадает с ожидаемым!");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

