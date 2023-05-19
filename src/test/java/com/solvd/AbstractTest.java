package com.solvd;

import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AbstractTest {
    protected ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

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
        } else if(browser.equalsIgnoreCase("safari")){
            SafariOptions options = new SafariOptions();
            driver = new RemoteWebDriver(new URL(ConfigReader.getProperty("selenium_url")), options);
        }else {
            throw new IllegalArgumentException("Invalid browser parameter");
        }
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
}
