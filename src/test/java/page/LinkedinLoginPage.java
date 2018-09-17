package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinLogin Page Object class.
 */
public class LinkedinLoginPage extends LinkedinBasePage{

  @FindBy(id = "login-email")
    private WebElement emailField;

  @FindBy(id = "login-password")
    private WebElement passwordField;

  @FindBy(id = "login-submit")
    private WebElement signInButton;

  @FindBy(xpath = "//a[@class='link-forgot-password']")
  private WebElement forgotPasswordLink;

    /**
     * Constructor for LinkedinLoginPage.
     *
     * @param driver - driver instance from tests.
     */
  public LinkedinLoginPage(WebDriver driver) {
      this.driver = driver;
      PageFactory.initElements(driver, this);
  }

    /**
     * User login by username/password/
     *
     * @param userEmail - String with userEmail.
     * @param userPassword - String with userPassword.
     * @param <T> - generic type to return different PageObjects.
     * @return one of corresponding PageObjects LinkedinLoginPage/LinkedinHomePage/LinkedinLoginSubmitPage.
     */
  public <T> T login(String userEmail, String userPassword) {
      emailField.sendKeys(userEmail);
      passwordField.sendKeys(userPassword);
      signInButton.click();
      if (getCurrentUrl().contains("/feed")) {
         return (T) new LinkedinHomePage(driver);
      }
      if (getCurrentUrl().contains("/login-submit")) {
          return (T) new LinkedinLoginSubmitPage(driver);
      }
      else {
          return (T) this;
      }
  }

    /**
     * Is LinkedinLoginPagepage loaded.
     *
     * @return current URL, Title, displayed signInButton.
     */
  public boolean isPageLoaded() {
      return getCurrentUrl().equals("https://www.linkedin.com/")
              && getCurrentTitle().equals("LinkedIn: Log In or Sign Up")
              && signInButton.isDisplayed();
  }

    /**
     * Forgot password link.
     *
     * @return LinkedinForgotPasswordPage Page Object class.
     */
    public LinkedinForgotPasswordPage forgotPasswordForm() {
      forgotPasswordLink.click();
      return new LinkedinForgotPasswordPage(driver);
    }
}
