package com.wrike.test;

import com.wrike.test.config.ConfigProperties;
import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.steps.MainPageSteps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BrowserTest {

    protected WebDriver webDriver;
    private MainPageSteps mainPageSteps;
    private static String redirectedTitle = ConfigProperties.getConfigProperties().getProperty("test.redirectedTitle");
    private static long redirectedWaitTimeSeconds = Long.parseLong(ConfigProperties.getConfigProperties().getProperty("test.redirectedWaitTimeSeconds"));

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    public void test() {
        mainPageSteps = new MainPageSteps(webDriver);
        mainPageSteps.startForFree();
        try {
            new WebDriverWait(webDriver, redirectedWaitTimeSeconds)
                    .until(ExpectedConditions.titleIs(redirectedTitle));
        } catch (TimeoutException ignored) {
        } //ignored for next assert
        finally {
            Assert.assertEquals(redirectedTitle, webDriver.getTitle());
        }
    }

    @After
    public void tearDown() {
        WebDriverConfig.shutDownDriver();
    }
}
