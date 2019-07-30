package com.wrike.test.steps;

import com.wrike.test.pages.VerifyEmailPage;
import com.wrike.test.util.ConfigProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyEmailPageSteps {

    private WebDriver webDriver;

    private VerifyEmailPage verifyEmailPage;

    private static long waitTimeSeconds = Long.parseLong(ConfigProperties.getConfigProperties().getProperty("test.waitTimeSeconds"));
    private static String twitterReference = ConfigProperties.getConfigProperties().getProperty("test.twitterReference");
    private static String twitterIconData = ConfigProperties.getConfigProperties().getProperty("test.twitterIcon.data");
    private static String twitterIconColour = ConfigProperties.getConfigProperties().getProperty("test.twitterIcon,colour");
    private static String startURL = ConfigProperties.getConfigProperties().getProperty("test.startURL");

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

    public boolean checkTwitterReference() {
        return verifyEmailPage.getTwitterReference().equals(twitterReference);
    }

    public boolean checkTwitterIcon() {
        String relativePathToIcon = verifyEmailPage.getTwitterIconReference().getAttribute("xlink:href"); //get icon
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
