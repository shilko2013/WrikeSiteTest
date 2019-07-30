package com.wrike.test.steps;

import com.wrike.test.pages.VerifyEmailPage;
import com.wrike.test.util.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyEmailPageSteps {

    private WebDriver webDriver;

    private VerifyEmailPage verifyEmailPage;

    private static long redirectedWaitTimeSeconds = Long.parseLong(ConfigProperties.getConfigProperties().getProperty("test.redirectedWaitTimeSeconds"));

    public VerifyEmailPageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        verifyEmailPage = new VerifyEmailPage(webDriver);
    }

    public void sendForm() {
        verifyEmailPage.fillRandomAnswers();
        verifyEmailPage.submitForm();
    }

    public void checkSubmit() {
        new WebDriverWait(webDriver, redirectedWaitTimeSeconds)
                .withMessage("Answer sending failed")
                .until(webDriver1 -> verifyEmailPage.sendedSuccess());
    }
}
