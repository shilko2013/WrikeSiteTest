package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import org.junit.*;

public class ChromeTest extends BrowserTest {

    @Before
    @Override
    public void setUp() {
        webDriver = WebDriverConfig.getNewChromeDriver();
    }

    @Override
    public void testVerifyEmail() {
        super.testVerifyEmail();
    }

    @Override
    public void testTwitterIcon() {
        super.testTwitterIcon();
    }

    @After
    @Override
    public void tearDown() {
        WebDriverConfig.shutDownChromeDriver();
    }
}
