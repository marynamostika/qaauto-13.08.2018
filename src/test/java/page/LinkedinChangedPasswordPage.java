package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinChangedPasswordPage Page Object class.
 */
public class LinkedinChangedPasswordPage extends LinkedinBasePage {
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    /**
     * Constructor for LinkedinChangedPasswordPage.
     *
     *  @param driver - driver instance from tests.
     */
    public LinkedinChangedPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Is page LinkedinChangedPasswordPage loaded.
     *
     * @return current Url, Title, goHomepage button is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains("password-reset-submit")
                && getCurrentTitle().contains("successfully reset your password")
                && goToHomepageButton.isDisplayed();
    }

    /**
     * Back to Home page.
     *
     * @return LinkedinHomePage Page Object class.
     */
    public LinkedinHomePage backToHomepage() {
        goToHomepageButton.click();
        return new LinkedinHomePage(driver);
    }
}
