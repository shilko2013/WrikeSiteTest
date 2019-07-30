package com.wrike.test.steps;

import com.wrike.test.util.ConfigProperties;
import com.wrike.test.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageSteps {

    private WebDriver webDriver;

    private MainPage mainPage;

    private static String startURL = ConfigProperties.getConfigProperties().getProperty("test.startURL");
    private static String redirectedURLRegex = ConfigProperties.getConfigProperties().getProperty("test.redirectedURLRegex");
    private static long redirectedWaitTimeSeconds = Long.parseLong(ConfigProperties.getConfigProperties().getProperty("test.redirectedWaitTimeSeconds"));

    public MainPageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        mainPage = new MainPage(webDriver);
    }

    public void startForFree() {
        webDriver.get(startURL);
        mainPage.startForFree();
        mainPage.enterRandomEmail();
        mainPage.submitEmail();
    }

    public void checkSubmit() {
        new WebDriverWait(webDriver, redirectedWaitTimeSeconds)
                .withMessage("Email submit failed")
                .until(ExpectedConditions.urlMatches(redirectedURLRegex));
    }
}
