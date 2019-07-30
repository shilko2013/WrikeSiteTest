package com.wrike.test.config;

import com.wrike.test.util.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverConfig {

    private static WebDriver chromeDriver = null;

    static {
        String pathToDrivers = ConfigProperties.getConfigProperties().getProperty("drivers.path");
        String pathToChromeDriver = pathToDrivers + ConfigProperties.getConfigProperties().getProperty("driver.chrome.filename");
        System.setProperty(ConfigProperties.getConfigProperties().getProperty("driver.chrome.variable"), pathToChromeDriver);
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
