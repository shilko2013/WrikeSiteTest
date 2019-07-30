package com.wrike.test;

import com.wrike.test.util.ConfigProperties;
import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.steps.MainPageSteps;
import com.wrike.test.steps.VerifyEmailPageSteps;
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
    private VerifyEmailPageSteps verifyEmailPageSteps;

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    public void test() {
        mainPageSteps = new MainPageSteps(webDriver);
        mainPageSteps.startForFree();
        mainPageSteps.checkSubmit();
        verifyEmailPageSteps = new VerifyEmailPageSteps(webDriver);
        verifyEmailPageSteps.sendForm();
        verifyEmailPageSteps.checkSubmit();
    }

    @After
    public void tearDown() {
        WebDriverConfig.shutDownDriver();
    }
}
