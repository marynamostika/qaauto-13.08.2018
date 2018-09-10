package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordPage extends LinkedinBasePage{
    @FindBy(xpath = "//header[text()='We just emailed you a link']")
    private WebElement headerLocator;

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement retypePasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LinkedinResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("password-reset-submit")
                && getCurrentTitle().contains("for reset password link.")
                && headerLocator.isDisplayed();
    }

    public LinkedinChangedPasswordPage chooseNewPassword(String userPassword) {
        try {
            sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newPasswordField.sendKeys(userPassword);
        retypePasswordField.sendKeys(userPassword);
        submitButton.click();
        return new LinkedinChangedPasswordPage(driver);
    }
}
