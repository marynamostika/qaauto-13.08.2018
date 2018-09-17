package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 *LinkedinBasePage Page object class.
 */
public class LinkedinBasePage {
    protected WebDriver driver;
    protected static GMailService gMailService = new GMailService();
    protected String message;

    /**
     * String get current Url.
     *
     * @return driver get current Url.
     */
    protected String getCurrentUrl() {return driver.getCurrentUrl(); }

    /**
     * String get current Title.
     *
     * @return driver get current Title.
     */
    protected String getCurrentTitle() {return driver.getTitle(); }

    /**
     * Wait until element will be visible.
     *
     * @param webElement webelement for waiting.
     * @param timeOutInSec timeout for wait.
     * @return wait until visibility of webelement.
     */
    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
       return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}
