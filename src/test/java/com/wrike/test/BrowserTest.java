package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public abstract class BrowserTest {

    protected WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        webDriver = WebDriverConfig.getDriver();
    }

    @Test
    public void test() {
        webDriver.get("https://www.wrike.com/");
    }

    @After
    public void tearDown() throws Exception {
        WebDriverConfig.shutDownDriver();
    }
}
