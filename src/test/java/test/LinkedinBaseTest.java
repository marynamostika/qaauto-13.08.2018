package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LinkedinLoginPage;

/**
 * LinkedinBaseTest class.
 */
public class LinkedinBaseTest {
    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;


    /**
     * Before method.
     *
     * Open browser.
     * Driver get "https://www.linkedin.com/".
     */
    @Parameters({"browserName", "envUrl"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName,
                             @Optional ("https://linkedin.com/") String envUrl) throws Exception {
        switch (browserName.toLowerCase()) {
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
                throw new Exception("Browser " + browserName + " is not supported");
        }

        driver.get(envUrl);
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
