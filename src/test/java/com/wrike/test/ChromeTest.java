package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ChromeTest extends BrowserTest {

    @Before
    @Override
    public void setUp() throws Exception {
        webDriver = WebDriverConfig.getNewChromeDriver();
    }

    @Test
    @Override
    public void test() {
        super.test();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        WebDriverConfig.shutDownChromeDriver();
    }
}
