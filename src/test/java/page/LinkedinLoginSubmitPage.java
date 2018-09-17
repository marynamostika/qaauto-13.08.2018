package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinLoginSubmitPage Page Object class.
 */
public class LinkedinLoginSubmitPage extends LinkedinBasePage {
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailAlert;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordAlert;

    /**
     * Constructor for LinkedinLoginSubmitPage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinLoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Is LinkedinLoginSubmitPage loaded.
     *
     * @return current Url, Title, alertMessage is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains("uas/login-submit")
                && getCurrentTitle().contains("Sign In to LinkedIn")
                && alertMessage.isDisplayed();
    }

    /**
     * Alert message get text.
     *
     * @return alert message.
     */
    public String getAlertMessageText() {
        return alertMessage.getText();
    }

    /**
     * User email alert message get text.
     *
     * @return user email alert message.
     */
    public String getUserEmailAlertText() {
        return userEmailAlert.getText();
    }

    /**
     * User password alert message get text.
     *
     * @return user password alert message.
     */
    public String getUserPasswordAlertText() {
        return userPasswordAlert.getText();
    }
    }

