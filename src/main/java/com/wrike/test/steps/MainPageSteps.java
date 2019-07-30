package com.wrike.test.steps;

import com.wrike.test.config.ConfigProperties;
import com.wrike.test.pages.MainPage;
import org.openqa.selenium.WebDriver;

public class MainPageSteps {

    private WebDriver webDriver;

    private MainPage mainPage;

    private static String startURL = ConfigProperties.getConfigProperties().getProperty("test.startURL");

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

}
