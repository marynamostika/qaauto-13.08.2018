package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *  LinkedinForgotPasswordPage Page Object class.
 */
public class LinkedinForgotPasswordPage extends LinkedinBasePage {

    @FindBy(xpath = "//header[@class='content__header']")
    private WebElement headerLocator;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//button[@class='form__submit']" )
    private WebElement findAccountButton;

    /**
     *  Constructor for LinkedinForgotPasswordPage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Is page LinkedinForgotPasswordPage loaded.
     *
     * @return current Url, Title, header message is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains("forgot-password")
              &&   getCurrentTitle().contains("Reset Password | LinkedIn")
              && headerLocator.isDisplayed();
    }

    /**
     * Enter email and connect to email for resetPassword link.
     *
     * @param userEmail - String with userEmail.
     * @return LinkedinResetPasswordSubmitPage Page Object class.
     */
    public LinkedinResetPasswordSubmitPage enterEmail(String userEmail) {
        gMailService.connect();
        userNameField.sendKeys(userEmail);
        findAccountButton.click();

        return new LinkedinResetPasswordSubmitPage(driver);
    }
}
