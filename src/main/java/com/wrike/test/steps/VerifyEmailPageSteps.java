package com.wrike.test.steps;

import com.wrike.test.pages.VerifyEmailPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
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

    @Step("Fill Q&A section with random answers")
    public void fillForm() {
        AnswerInfo(verifyEmailPage.fillRandomAnswers());
    }

    @Attachment
    private String AnswerInfo(List<String> answers) {
        StringBuilder output = new StringBuilder("Selected answers:\n");
        for (int i = 0; i < answers.size(); ++i)
            output.append(i+1).append("->").append(answers.get(i)).append("\n");
        return output.toString();
    }

    @Step("Check success answer sending")
    public void checkSubmit() {
        new WebDriverWait(webDriver, waitTimeSeconds)
                .withMessage("Answer sending failed")
                .until(webDriver1 -> verifyEmailPage.sentSuccessfully());
    }

    public void submitForm() {
        verifyEmailPage.submitForm();
    }
}
