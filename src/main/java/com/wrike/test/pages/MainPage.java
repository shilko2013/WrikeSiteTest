package com.wrike.test.pages;

import com.wrike.test.util.ConfigProperties;
import com.wrike.test.util.RandomTextStringGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class MainPage extends Page {

    @FindBy(xpath = "//*[@class='wg-header__desktop']//*[contains(@class,'wg-header__free-trial-button')]")
    private WebElement buttonStartForFree;

    @FindBy(xpath = "//*[contains(@class,'modal-form-trial__input')]")
    private WebElement inputEmailField;

    @FindBy(xpath = "//*[contains(@class,'modal-form-trial__submit')]")
    private WebElement emailSubmitButton;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void startForFree() {
        buttonStartForFree.click();
    }

    public void enterEmail(String email) {
        inputEmailField.sendKeys(email);
    }

    public void enterRandomEmail() {
        String emailTail = ConfigProperties.getConfigProperties().getProperty("test.emailTail");
        String emailHead = RandomTextStringGenerator.getRandomTextString();
        enterEmail(emailHead + emailTail);
    }

    public void submitEmail() {
        emailSubmitButton.click();
    }

}
