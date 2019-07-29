package com.wrike.test.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverConfig {

    private static WebDriver chromeDriver = null;

    static {
        String pathtoDrivers = "src/main/resources/drivers/";
        String pathToChromeDriver = pathtoDrivers + "chromedriver";
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
        return chromeDriver;
    }

    public static void shutDownChromeDriver() {
        if (chromeDriver != null)
            chromeDriver.quit();
    }

    public static void shutDownDriver() {
        shutDownChromeDriver();
    }

}
