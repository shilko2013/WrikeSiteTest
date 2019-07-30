package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.pages.MainPage;
import com.wrike.test.pages.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BrowserTest {

    protected WebDriver webDriver;
    private MainPage mainPage;
    private static String startURL = "https://www.wrike.com/";

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    public void test() {
        webDriver.get(startURL);
        mainPage = new MainPage(webDriver);
        mainPage.startForFree();
        mainPage.enterRandomEmail();
        mainPage.submitEmail();
    }

    @After
    public void tearDown() {
        WebDriverConfig.shutDownDriver();
    }
}
