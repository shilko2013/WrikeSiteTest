package com.wrike.test.pages;

import com.wrike.test.config.ConfigProperties;
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
        Random binaryRandom = new Random();
        String emailHead = new Random()
                .ints('A', 'Z')
                .limit(new Random().nextInt(10) + 5)
                .map(e -> binaryRandom.nextBoolean() ? e : e + 0x20)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        enterEmail(emailHead + emailTail);
    }

    public void submitEmail() {
        emailSubmitButton.click();
    }

}
