package page;

import org.openqa.selenium.TimeoutException;
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
    /**
     * Gmail service.
     *
     * Go to gmail for giving a link.
     */
    protected static GMailService gMailService = new GMailService();

    /**
     * String with message from gmail.
     */
    protected String message;

    /**
     * Get string for current Url.
     *
     * @return driver get current Url.
     */
    protected String getCurrentUrl() {return driver.getCurrentUrl(); }

    /**
     * Get string with current Title.
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
     protected Boolean isUrlContains(String partialUrl, int timeOutInSec) {
         WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);

         try{
             return wait.until(ExpectedConditions.urlContains(partialUrl));
         } catch (TimeoutException e) {
             return false;
         }
     }

     protected void assertElementIsVisible (WebElement webelement, int timeOutInSec, String message) {
         try {
             waitUntilElementVisible(webelement, timeOutInSec);
         } catch (TimeoutException e) {
             throw new AssertionError(message);
         }
     }


}
