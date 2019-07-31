package com.wrike.test.steps;

import com.wrike.test.pages.VerifyEmailPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        answerInfo(verifyEmailPage.fillRandomAnswers());
    }

    @Attachment
    private String answerInfo(List<String> answers) {
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

    @Step("Submit form")
    public void submitForm() {
        verifyEmailPage.submitForm();
    }
}
