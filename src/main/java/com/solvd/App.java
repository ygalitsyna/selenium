package com.solvd;

import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/yanagalitsyna/Desktop/chromedrivers/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
    }

}
