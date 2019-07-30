package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.pages.MainPage;
import com.wrike.test.steps.MainPageSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public abstract class BrowserTest {

    protected WebDriver webDriver;
    private MainPageSteps mainPageSteps;

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    public void test() {
        mainPageSteps = new MainPageSteps(webDriver);
        mainPageSteps.startForFree();
    }

    @After
    public void tearDown() {
        WebDriverConfig.shutDownDriver();
    }
}
