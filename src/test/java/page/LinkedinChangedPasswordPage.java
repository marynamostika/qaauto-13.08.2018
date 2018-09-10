package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinChangedPasswordPage extends LinkedinBasePage {
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    public LinkedinChangedPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("password-reset-submit")
                && getCurrentTitle().contains("successfully reset your password")
                && goToHomepageButton.isDisplayed();
    }

    public LinkedinHomePage backToHomepage() {
        goToHomepageButton.click();
        return new LinkedinHomePage(driver);
    }
}
