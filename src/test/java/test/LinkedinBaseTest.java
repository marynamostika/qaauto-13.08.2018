package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;

/**
 * LinkedinBaseTest class.
 */
public class LinkedinBaseTest  {
    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;

    /**
     * Before method.
     *
     * Open browser.
     * Driver get "https://www.linkedin.com/".
     */
    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    /**
     * After method.
     *
     * Close browser.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
