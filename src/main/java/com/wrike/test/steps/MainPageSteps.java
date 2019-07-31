package com.wrike.test.steps;

import com.wrike.test.pages.MainPage;
import io.qameta.allure.Step;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wrike.test.util.ConfigProperties.*;

public class MainPageSteps {

    private WebDriver webDriver;

    private MainPage mainPage;

    private static String startURL = getConfigProperties().getProperty("test.startURL");
    private static String redirectedURLRegex = getConfigProperties().getProperty("test.redirectedURLRegex");
    private static long waitTimeSeconds = Long.parseLong(getConfigProperties().getProperty("test.waitTimeSeconds"));

    public MainPageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        mainPage = new MainPage(webDriver);
        webDriver.get(startURL);
    }

    @Step("Submit form with {email} email")
    public void startForFreeWithEmail(String email) {
        mainPage.startForFree();
        mainPage.enterEmail(email);
        mainPage.submitEmail();
    }

    @Step("Check success email submitting")
    public void checkSubmit() {
        new WebDriverWait(webDriver, waitTimeSeconds)
                .withMessage("Email submit failed")
                .until(ExpectedConditions.urlMatches(redirectedURLRegex));
    }

    @Step("Compare valid and present twitter reference")
    public String getTwitterReference() {
        return mainPage.getTwitterReference();
    }

    /*
    @return Pair of "d" tag and "fill" tag
     */
    @Step("Compare valid and present twitter icon")
    public Pair<String,String> getTwitterIcon() {
        String relativePathToIcon = mainPage.getTwitterIconReference().getAttribute("xlink:href"); //get icon
        webDriver.get(startURL + relativePathToIcon); //downloading picture
        String iconCode = webDriver.findElement(By.xpath("//*[@id='twitter']")).getAttribute("innerHTML");
        Pattern dataPattern = Pattern.compile("<path.*? d=\"([^\"]*?)\"");
        Matcher dataMatcher = dataPattern.matcher(iconCode);
        String d, fill;
        if (dataMatcher.find())
            d = dataMatcher.group(1);
        else
            throw new IllegalArgumentException();
        Pattern colourPattern = Pattern.compile("<path.*? fill=\"([^\"]*?)\"");
        Matcher colourMatcher = colourPattern.matcher(iconCode);
        if (colourMatcher.find())
            fill = colourMatcher.group(1);
        else
            throw new IllegalArgumentException();
        return new Pair<>(d,fill);
    }
}