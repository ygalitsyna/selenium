package com.solvd;

import com.solvd.utils.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(AbstractTest.class);
    protected ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private String browserName;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) throws MalformedURLException {
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("selenium_url")), options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("selenium_url")), options);
        } else if (browser.equalsIgnoreCase("safari")) {
            SafariOptions options = new SafariOptions();
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("selenium_url")), options);
        } else {
            throw new IllegalArgumentException("Invalid browser parameter");
        }
        browserName = browser;
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driverThreadLocal.set(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void tearDown2(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshotIfFailed(driverThreadLocal.get(), testResult.getMethod().getMethodName());
        }
    }

    private void takeScreenshotIfFailed(WebDriver driver, String methodName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/" + methodName + "_" + getBrowserName() + "_" + getCurrentDateTime() + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            LOGGER.info("Screenshot captured: {}", screenshotPath);
        } catch (IOException e) {
            LOGGER.warn("Failed to capture screenshot during test fail", e);
        }
    }

    private String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssMs");
        return dateFormat.format(date);
    }

    public String getBrowserName() {
        return browserName;
    }

}
