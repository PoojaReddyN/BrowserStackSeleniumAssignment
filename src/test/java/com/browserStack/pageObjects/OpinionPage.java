package com.browserStack.pageObjects;

import com.browserStack.utilities.Article;
import com.browserStack.utilities.ImageDownloader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpinionPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;


    @FindBy(xpath = "//h2[@class='c_t c_t-i ']/a")
    List<WebElement> articles;

    @FindBy(xpath = "//div[contains(@class, 'a_c')]//p")
    private List<WebElement> articleParagraphs;

    @FindBy(xpath = "//h1")
    private WebElement articleTitle;

    @FindBy(xpath = "//img[@class='_re  a_m-h']")
    WebElement coverImage;


    public OpinionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        js=(JavascriptExecutor) driver;
    }


    public List<Article> fetchTitleAndContentOfArticles() throws InterruptedException {

        List<Article> articleList = new ArrayList<>();
        articles = articles.stream().limit(5).toList();

        for (WebElement article : articles) {

            js.executeScript("arguments[0].scrollIntoView(true);", article);
            wait.until(ExpectedConditions.elementToBeClickable(article));
            article.click();

            String title = articleTitle.getText();
            StringBuilder content = new StringBuilder();
            for (WebElement para : articleParagraphs) {
                content.append(para.getText()).append("\n\n");
            }

            String imageSoure = coverImage.getAttribute("src");
            System.out.println("image source: "+imageSoure);


            articleList.add(new Article(title, content.toString(), imageSoure));

            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//h2[@class='c_t c_t-i ']/a")));

            }
        return articleList;
         }
}