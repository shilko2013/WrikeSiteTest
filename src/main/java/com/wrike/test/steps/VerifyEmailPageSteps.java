package com.wrike.test.steps;

import com.wrike.test.pages.VerifyEmailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wrike.test.util.ConfigProperties.*;

public class VerifyEmailPageSteps {

    private WebDriver webDriver;

    private VerifyEmailPage verifyEmailPage;

    private static long waitTimeSeconds = Long.parseLong(getConfigProperties().getProperty("test.waitTimeSeconds"));

    public VerifyEmailPageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        verifyEmailPage = new VerifyEmailPage(webDriver);
    }

    public void sendForm() {
        verifyEmailPage.fillRandomAnswers();
        verifyEmailPage.submitForm();
    }

    public void checkSubmit() {
        new WebDriverWait(webDriver, waitTimeSeconds)
                .withMessage("Answer sending failed")
                .until(webDriver1 -> verifyEmailPage.sentSuccessfully());
    }
}
