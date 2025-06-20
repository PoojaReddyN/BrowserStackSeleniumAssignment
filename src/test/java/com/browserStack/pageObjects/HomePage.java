package com.browserStack.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='sm _df']/a[text()='Opinión']")
    WebElement opinionButton;

    public HomePage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    public void acceptCookiesIfPresent() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='didomi-notice-agree-button']")));
            System.out.println(acceptButton.getText());
            acceptButton.click();
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Cookie consent iframe not found or already handled.");
        }
    }

    public OpinionPage clickOnOpinionButton(){
        wait.until(ExpectedConditions.elementToBeClickable(opinionButton));
        opinionButton.click();
        return new OpinionPage(driver);
    }


}
