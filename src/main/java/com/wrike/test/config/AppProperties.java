package com.wrike.test.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static Properties configProperties;
    private static Properties locatorsProperties;

    static {
        try(FileReader reader = new FileReader("src/main/resources/config.properties")) {
            configProperties = new Properties();
            configProperties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileReader reader = new FileReader("src/main/resources/locators.properties")) {
            locatorsProperties = new Properties();
            locatorsProperties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getConfigProperties() {
        return configProperties;
    }

    public static Properties getLocatorsProperties() {
        return locatorsProperties;
    }
}
