package com.wrike.test.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverConfig {

    private static WebDriver chromeDriver = null;

    static {
        String pathToDrivers = "src/main/resources/drivers/";
        String pathToChromeDriver = pathToDrivers + "chromedriver_v.41-43";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
    }

    public static WebDriver getDriver() {
        return getNewChromeDriver();
    }

    public static WebDriver getChromeDriver() {
        return chromeDriver;
    }

    public static WebDriver getNewChromeDriver() {
        chromeDriver = new ChromeDriver();
        configDriver(chromeDriver);
        return chromeDriver;
    }

    private static void configDriver(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void shutDownChromeDriver() {
        if (chromeDriver != null)
            chromeDriver.quit();
    }

    public static void shutDownDriver() {
        shutDownChromeDriver();
    }

}
