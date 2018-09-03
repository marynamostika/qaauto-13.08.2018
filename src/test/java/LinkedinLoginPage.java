import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage extends LinkedinBasePage{

  @FindBy(id = "login-email")
    private WebElement emailField;

  @FindBy(id = "login-password")
    private WebElement passwordField;

  @FindBy(id = "login-submit")
    private WebElement signInButton;

  public LinkedinLoginPage(WebDriver driver) {
      this.driver = driver;
      PageFactory.initElements(driver, this);
  }

  public <T> T login(String userEmail, String userPassword) {
      emailField.sendKeys(userEmail);
      passwordField.sendKeys(userPassword);
      signInButton.click();
      try {
          sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
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

  public boolean isPageLoaded() {
      return getCurrentUrl().equals("https://www.linkedin.com/")
              && getCurrentTitle().equals("LinkedIn: Log In or Sign Up")
              && signInButton.isDisplayed();
  }
}
