package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;



    private By coockieButton = By.xpath("//div[@class='cookies-buttons-wrap']//button[@data-action-button='acceptAll']");
    private By emailInput = By.xpath("//legend[text()='Подпишитесь на новости']/..//input");
    private By subscribeButton = By.xpath("//legend[text()='Подпишитесь на новости']/..//button");
    private By confirmationMessage = By.xpath("//p[@class='iziToast-message slideIn']//div[@class='toast-content-text']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void acceptCoockie() {
        driver.findElement(coockieButton).click();
    }


    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickSubscribe() {

        WebElement element = driver.findElement(subscribeButton);
        element.click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public void scrollToBottomWithDelay(int delayInSeconds) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        long lastHeight = (long) jsExecutor.executeScript("return document.body.scrollHeight");

        while (true) {
            // Прокрутить в самый низ
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Задержка для подгрузки контента(сколько не пытался, ничего толкового кроме sleep придумать не удалось)
            try {
                Thread.sleep(delayInSeconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Проверить, изменилась ли высота страницы
            long newHeight = (long) jsExecutor.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break; // Выход из цикла, если больше нечего подгружать
            }
            lastHeight = newHeight;
        }
    }
}
