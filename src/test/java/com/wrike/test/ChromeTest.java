package com.wrike.test;

import com.wrike.test.config.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChromeTest extends BrowserTest {

    @Before
    @Override
    public void setUp() {
        webDriver = WebDriverConfig.getNewChromeDriver();
    }

    @Test
    @Override
    public void test() {
        super.test();
    }

    @After
    @Override
    public void tearDown() {
        WebDriverConfig.shutDownChromeDriver();
    }
}
