package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;

/**
 * LinkedinBaseTest class.
 */
public class LinkedinBaseTest {
    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;
    String browserName = "chrome";

    /**
     * Before method.
     * <p>
     * Open browser.
     * Driver get "https://www.linkedin.com/".
     */
    @BeforeMethod
    public void beforeMethod() {
        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("browser : " + browserName + " is invalid, Launching Firefox as browser of choice..");
                driver = new FirefoxDriver();
        }
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);

    }

    /**
     * After method.
     * <p>
     * Close browser.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }
}
