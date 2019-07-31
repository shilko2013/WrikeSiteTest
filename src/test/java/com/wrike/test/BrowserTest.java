package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.steps.MainPageSteps;
import com.wrike.test.steps.VerifyEmailPageSteps;
import com.wrike.test.util.ConfigProperties;
import com.wrike.test.util.RandomTextStringGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static com.wrike.test.util.ConfigProperties.*;

public abstract class BrowserTest {

    protected WebDriver webDriver;

    private static String emailTail = getConfigProperties().getProperty("test.emailTail");

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    @DisplayName("Start for free email validation")
    @Description("Trying to validate email. Filling Q&A section.")
    public void testVerifyEmail() {
        testMainPage();
        testVerifyEmailPage();
    }

    private void testMainPage() {
        MainPageSteps mainPageSteps = new MainPageSteps(webDriver);
        mainPageSteps.startForFreeWithEmail(RandomTextStringGenerator.getRandomTextString() + emailTail);
        mainPageSteps.checkSubmit();
    }

    private void testVerifyEmailPage() {
        VerifyEmailPageSteps verifyEmailPageSteps = new VerifyEmailPageSteps(webDriver);
        verifyEmailPageSteps.sendForm();
        verifyEmailPageSteps.checkSubmit();
    }

    @Test
    @DisplayName("Footer twitter button validation")
    @Description("Checking twitter redirect URL. Checking twitter icon.")
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
