package com.wrike.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {

    @FindBy(xpath = "//*[@class='wg-header__desktop']//*[contains(@class,'wg-header__free-trial-button')]")
    private WebElement buttonStartForFree;

    @FindBy(xpath = "//*[contains(@class,'modal-form-trial__input')]")
    private WebElement inputEmailField;

    @FindBy(xpath = "//*[contains(@class,'modal-form-trial__submit')]")
    private WebElement emailSubmitButton;

    @FindBy(xpath = "(//*[@class='wg-footer__social-link'])[1]")
    private WebElement twitterSection;

    @FindBy(xpath = "(//*[@class='wg-footer__social-link'])[1]/*[name()='svg']/*[name()='use']")
    private WebElement twitterIconReference;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void startForFree() {
        buttonStartForFree.click();
    }

    public void enterEmail(String email) {
        inputEmailField.sendKeys(email);
    }

    public void submitEmail() {
        emailSubmitButton.click();
    }

    public String getTwitterReference() {
        return twitterSection.getAttribute("href");
    }

    public WebElement getTwitterIconReference() {
        return twitterIconReference;
    }

}
