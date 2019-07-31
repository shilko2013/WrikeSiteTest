package com.wrike.test;

import com.wrike.test.util.ConfigProperties;
import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.steps.MainPageSteps;
import com.wrike.test.steps.VerifyEmailPageSteps;
import org.junit.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BrowserTest {

    protected WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    public void testVerifyEmail() {
        testMainPage();
        testVerifyEmailPage();
    }

    private void testMainPage() {
        MainPageSteps mainPageSteps = new MainPageSteps(webDriver);
        mainPageSteps.startForFree();
        mainPageSteps.checkSubmit();
    }

    private void testVerifyEmailPage() {
        VerifyEmailPageSteps verifyEmailPageSteps = new VerifyEmailPageSteps(webDriver);
        verifyEmailPageSteps.sendForm();
        verifyEmailPageSteps.checkSubmit();
    }

    @Test
    public void testTwitterIcon() {
        MainPageSteps mainPageSteps = new MainPageSteps(webDriver);
        Assert.assertTrue(mainPageSteps.checkTwitterReference());
        Assert.assertTrue(mainPageSteps.checkTwitterIcon());
    }

    @After
    public void tearDown() {
        WebDriverConfig.shutDownDriver();
    }
}
