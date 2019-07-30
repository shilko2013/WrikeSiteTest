package com.wrike.test.pages;

import com.wrike.test.util.RandomTextStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class VerifyEmailPage extends Page {

    @FindBy(xpath = "//*[@name='survey-form']//*[@class='survey-question' or @class='radio']")
    private List<WebElement> questions;

    @FindBy(xpath = "//*[@name='survey-form']//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@class='survey-success' or @class='resend-page__cell--success']")
    private WebElement successElement;

    @FindBy(xpath = "(//*[@class='wg-footer__social-link'])[1]")
    private WebElement twitterSection;

    @FindBy(xpath = "(//*[@class='wg-footer__social-link'])[1]/*[name()='svg']/*[name()='use']")
    private WebElement twitterIconReference;

    public VerifyEmailPage(WebDriver driver) {
        super(driver);
    }

    public void fillRandomAnswers() { //is it step?
        questions.forEach(question -> {
            List<WebElement> answers = question.findElements(By.xpath(".//*[contains(@class,'survey-question-radio__button') or contains(@class,'switch__button')]"));
            WebElement clickedAnswer = answers.get(new Random().nextInt(answers.size()));
            clickedAnswer.click();
            try {
                WebElement inputAnswer = clickedAnswer.findElement(By.xpath(".//*[@class='survey-question-radio__other-input' or @class='switch__input']"));
                inputAnswer.sendKeys(RandomTextStringGenerator.getRandomTextString());
            } catch (NoSuchElementException ex) {
                //it is usual radio-button
            }
        });
    }

    public void submitForm() {
        submitButton.click();
    }

    public boolean sentSuccessfully() {
        return successElement.isDisplayed();
    }

    public String getTwitterReference() {
        return twitterSection.getAttribute("href");
    }

    public WebElement getTwitterIconReference() {
        return twitterIconReference;
    }
}
