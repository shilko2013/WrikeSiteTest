package com.wrike.test.steps;

import com.wrike.test.util.ConfigProperties;
import com.wrike.test.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wrike.test.util.ConfigProperties.*;

public class MainPageSteps {

    private WebDriver webDriver;

    private MainPage mainPage;

    private static String startURL = getConfigProperties().getProperty("test.startURL");
    private static String redirectedURLRegex = getConfigProperties().getProperty("test.redirectedURLRegex");
    private static long waitTimeSeconds = Long.parseLong(getConfigProperties().getProperty("test.waitTimeSeconds"));
    private static String twitterReference = getConfigProperties().getProperty("test.twitterReference");
    private static String twitterIconData = getConfigProperties().getProperty("test.twitterIcon.data");
    private static String twitterIconColour = getConfigProperties().getProperty("test.twitterIcon.colour");

    public MainPageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
        mainPage = new MainPage(webDriver);
        webDriver.get(startURL);
    }

    public void startForFree() {
        mainPage.startForFree();
        mainPage.enterRandomEmail();
        mainPage.submitEmail();
    }

    public void checkSubmit() {
        new WebDriverWait(webDriver, waitTimeSeconds)
                .withMessage("Email submit failed")
                .until(ExpectedConditions.urlMatches(redirectedURLRegex));
    }

    public boolean checkTwitterReference() {
        return mainPage.getTwitterReference().equals(twitterReference);
    }

    public boolean checkTwitterIcon() {
        String relativePathToIcon = mainPage.getTwitterIconReference().getAttribute("xlink:href"); //get icon
        WebDriver driverIcon = new ChromeDriver();
        String d = null, fill = null;
        try {
            driverIcon.get(startURL + relativePathToIcon);
            String iconCode = driverIcon.findElement(By.xpath("//*[@id='twitter']")).getAttribute("innerHTML");
            Pattern dataPattern = Pattern.compile("<path.*? d=\"([^\"]*?)\"");
            Matcher dataMatcher = dataPattern.matcher(iconCode);
            dataMatcher.find();
            d = dataMatcher.group(1);
            Pattern colourPattern = Pattern.compile("<path.*? fill=\"([^\"]*?)\"");
            Matcher colourMatcher = colourPattern.matcher(iconCode);
            colourMatcher.find();
            fill = colourMatcher.group(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driverIcon.quit();
        }

        return twitterIconData.equals(d) && twitterIconColour.equals(fill);
    }
}
