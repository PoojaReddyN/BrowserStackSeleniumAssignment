package com.browserStack.utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String executionType = ConfigLoader.getProperty("execution.type");
            String browser = ConfigLoader.getProperty("browser.type");

            if (executionType.equalsIgnoreCase("browserstack")) {
                driver.set(createRemoteDriver(browser));
            } else {
                driver.set(createLocalDriver(browser));
            }
        }
        return driver.get();
    }

    private static WebDriver createLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                return new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080");
                return new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("start-maximized");
                return new org.openqa.selenium.edge.EdgeDriver(edgeOptions);

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createRemoteDriver(String browser) {
        try {
            MutableCapabilities capabilities = new MutableCapabilities();

            // Browser capabilities
            capabilities.setCapability("browserName", browser);
            capabilities.setCapability("browserVersion", "latest");

            // bstack options (W3C-compliant)
            MutableCapabilities bstackOptions = new MutableCapabilities();
            bstackOptions.setCapability("os", "Windows");
            bstackOptions.setCapability("osVersion", "11");
            bstackOptions.setCapability("sessionName", "BrowserStack Test");

            bstackOptions.setCapability("userName", ConfigLoader.getProperty("browserstack.user"));
            bstackOptions.setCapability("accessKey", ConfigLoader.getProperty("browserstack.key"));

            capabilities.setCapability("bstack:options", bstackOptions);

            return new RemoteWebDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
        }  catch (Exception e) {
            throw new RuntimeException("Failed to create remote driver: " + e.getMessage());
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

