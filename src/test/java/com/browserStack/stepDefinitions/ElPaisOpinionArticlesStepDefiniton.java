package com.browserStack.stepDefinitions;

import com.browserStack.APIUtils.SpanishToEnglishTranslateAPI;
import com.browserStack.pageObjects.HomePage;
import com.browserStack.pageObjects.OpinionPage;
import com.browserStack.utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ElPaisOpinionArticlesStepDefiniton {

    WebDriver driver;
    HomePage homePage;
    OpinionPage opinionPage;
    List<Article> articles;
    List<String> headers;


    @Given("I open the browser and navigate to the El País Opinion section")
    public void iOpenTheBrowserAndNavigateToTheElPaísOpinionSection() {
        driver = DriverSingleton.getDriver();
        driver.get(ConfigLoader.getProperty("uiURI"));
    }

    @And("I am on the El País Opinion page")
    public void iAmOnTheElPaísOpinionPage() {
        homePage=new HomePage(driver);
        homePage.acceptCookiesIfPresent();
        opinionPage=homePage.clickOnOpinionButton();
    }

    @When("I scrape the first five articles")
    public void iScrapeTheFirstFiveArticles() throws InterruptedException {
        articles=opinionPage.fetchTitleAndContentOfArticles();
    }

    @Then("I should see article titles and content in Spanish")
    public void iShouldSeeArticleTitlesAndContentInSpanish() {
        for (Article article : articles) {
            System.out.println("title: "+article.getTitle());
           // System.out.println("content: "+article.getContent());
        }
    }

    @And("the titles should be translated to English and printed")
    public void theTitlesShouldBeTranslatedToEnglishAndPrinted() {
        for (Article article : articles) {
            Response response=SpanishToEnglishTranslateAPI.translateText(article.getTitle());
            String title=response.jsonPath().getString("trans");
            System.out.println(title);
            headers.add(title);
        }

    }

    @And("images should be downloaded locally")
    public void imagesShouldBeDownloadedLocally() {
        for(Article article: articles) {
            ImageDownloader.downloadImage(article.getTitle(), article.getImageUrl());
        }

    }

    @And("The headers should be analyzed accordingly")
    public void theHeadersShouldBeAnalyzedAccordingly() {
        HeaderAnalyzer.analyzeRepeatedWords(headers);
    }

    @And("The driver should quit")
    public void theDriverShouldQuit() {
        DriverSingleton.quitDriver();
    }
}
