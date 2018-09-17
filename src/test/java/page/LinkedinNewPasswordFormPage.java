package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinNewPasswordFormPage Page Object class.
 */
public class LinkedinNewPasswordFormPage extends LinkedinBasePage{
    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement retypePasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    /**
     * Constructor for LinkedinNewPasswordFormPage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinNewPasswordFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(submitButton, 10);
    }

    /**
     * Is LinkedinNewPasswordFormPage loaded.
     *
     * @return current Url, Title, submitButton is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains("password-reset")
                && getCurrentTitle().contains("Reset Your Password | LinkedIn")
                && submitButton.isDisplayed();
    }

    /**
     * Choose new password.
     *
     * @param userPassword - String with user password.
     * @return LinkedinChangedPasswordPage Page Object class.
     */
    public LinkedinChangedPasswordPage chooseNewPassword(String userPassword) {
        newPasswordField.sendKeys(userPassword);
        retypePasswordField.sendKeys(userPassword);
        submitButton.click();
        return new LinkedinChangedPasswordPage(driver);
    }
}
