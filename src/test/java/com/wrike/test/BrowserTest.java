package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import com.wrike.test.steps.MainPageSteps;
import com.wrike.test.steps.VerifyEmailPageSteps;
import com.wrike.test.util.RandomTextStringGenerator;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import javafx.util.Pair;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static com.wrike.test.util.ConfigProperties.*;

public abstract class BrowserTest {

    protected WebDriver webDriver;

    private static String emailTail = getConfigProperties().getProperty("test.emailTail");
    private static String twitterReference = getConfigProperties().getProperty("test.twitterReference");
    private static String twitterIconData = getConfigProperties().getProperty("test.twitterIcon.data");
    private static String twitterIconColour = getConfigProperties().getProperty("test.twitterIcon.colour");

    @Before
    public void setUp() {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    @DisplayName("Start for free email validation")
    @Description("Trying to validate email. Filling Q&A section.")
    @Epic("Frontend")
    @Features({@Feature("Main page"), @Feature("Verify page")})
    @Story("Start for free with email address")
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
        verifyEmailPageSteps.fillForm();
        verifyEmailPageSteps.submitForm();
        verifyEmailPageSteps.checkSubmit();
    }

    @Test
    @DisplayName("Footer twitter button validation")
    @Description("Checking twitter redirect URL. Checking twitter icon.")
    @Epic("Frontend")
    @Feature("Main page")
    @Story("Twitter icon")
    public void testTwitterIcon() {
        MainPageSteps mainPageSteps = new MainPageSteps(webDriver);
        Assert.assertEquals(mainPageSteps.getTwitterReference(),twitterReference);
        Assert.assertEquals(new Pair<>(twitterIconData,twitterIconColour),mainPageSteps.getTwitterIcon());
    }

    @After
    public void tearDown() {
        WebDriverConfig.shutDownDriver();
    }
}
